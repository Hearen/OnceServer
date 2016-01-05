package com.once.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BasicListHeaderIterator;

public class Connection {
	/*******************************************
	 * Author: LHearen E-mail: LHearen@126.com Time : 2015-12-03 16:20
	 * Description: encapulate all parameters in data and post it to a certain url;
	 *******************************************/
	public static String sendPost(String urlString, Map<String, String> data)
	{
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(urlString);
		post.addHeader("User-Agent", CONST.USER_AGENT);

		// JSONArray arry = new JSONArray();
		// JSONObject j = new JSONObject(data);
		// arry.put(j);

		// post.setEntity(new StringEntity(j.toString(), "utf-8"));

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		for (String key : data.keySet()) {
			urlParameters.add(new BasicNameValuePair(key, data.get(key)));
		}
		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		CloseableHttpResponse response = null;
		try {
			response = client.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		BufferedReader reader = null;
		StringBuffer contentStringBuffer = new StringBuffer();
		try {
			reader = new BufferedReader(new InputStreamReader(response
					.getEntity().getContent()));
			String line;
			while ((line = reader.readLine()) != null) {
				contentStringBuffer.append(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return contentStringBuffer.toString();
	}
}
