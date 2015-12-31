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

import com.once.api.OnceHttpDelete;

public class Connection {
	/*******************************************
	 * Author: LHearen E-mail: LHearen@126.com Time : 2015-12-03 16:20
	 * Description: encapulate all parameters in headers and post to a certain
	 * url;
	 *******************************************/
	public static String sendPost(URL url, Map<String, String> headers,
			Map<String, String> data) throws UnsupportedEncodingException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url.toString());
		headers.put("User-Agent", CONST.USER_AGENT);
//		headers.put("Content-Type", CONST.CONTENT_TYPE);
		for (String key : headers.keySet()) {
			post.addHeader(key, headers.get(key));
		}

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

	/*******************************************
	 * Author : LHearen E-mail : LHearen@126.com Time : 2015-12-16 16 : 26
	 * Description : Just send patch to server updating DB;
	 *******************************************/
	public static String sendPatch(URL url, Map<String, String> data) {
		return sendPatch(url, new HashMap<String, String>(), data);
	}

	/*******************************************
	 * Author : LHearen E-mail : LHearen@126.com Time : 2015-12-16 16 : 27
	 * Description : Trying to send patch to the server updating DB and
	 * executing some operations;
	 *******************************************/
	public static String sendPatch(URL url, Map<String, String> headers,
			Map<String, String> data) {
		return sendPatch0(url, headers, data);
	}

	/*******************************************
	 * Author : LHearen E-mail : LHearen@126.com Time : 2015-12-16 16 : 27
	 * Description : Used as base method for sendPatch overlapping;
	 *******************************************/
	private static String sendPatch0(URL url, Map<String, String> headers,
			Map<String, String> data) {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPatch httpPatch = new HttpPatch(url.toString());
		httpPatch.addHeader("User-Agent", CONST.USER_AGENT);
//		httpPatch.addHeader("Content-Type", CONST.CONTENT_TYPE);
		for (String key : headers.keySet()) {
			httpPatch.addHeader(key, headers.get(key));
		}

		// JSONArray arry = new JSONArray();
		// JSONObject j = new JSONObject(data);
		// arry.put(j);

		// httpPatch.setEntity(new StringEntity(j.toString(), "utf-8"));

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		for (String key : data.keySet()) {
			urlParameters.add(new BasicNameValuePair(key, data.get(key)));
		}

		HttpEntity params = null;
		try {
			params = new UrlEncodedFormEntity(urlParameters);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpPatch.setEntity(params);

		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpPatch);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(httpResponse
					.getEntity().getContent()));
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String line;
		StringBuffer contentStringBuffer = new StringBuffer();

		try {
			while ((line = reader.readLine()) != null) {
				contentStringBuffer.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contentStringBuffer.toString();
	}

	/*******************************************
	 * Author: LHearen E-mail: LHearen@126.com Time : 2015-12-03 16:20
	 * Description: used to retrieve resources from eve;
	 *******************************************/
	public static String sendGet(URL url) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url.toString());
		get.addHeader("User-Agent", CONST.USER_AGENT);
		CloseableHttpResponse response;
		try {
			response = client.execute(get);
			int statusCode = response.getStatusLine().getStatusCode();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		StringBuffer contentStringBuffer = new StringBuffer();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(response
					.getEntity().getContent()));
			String line;

			while ((line = reader.readLine()) != null) {
				contentStringBuffer.append(line);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return contentStringBuffer.toString();
	}

	/*******************************************
	 * Author : LHearen E-mail : LHearen@126.com Time : 2015-12-16 16 : 28
	 * Description : Deleting resources in DB and execute some operations in
	 * server;
	 *******************************************/
	public static String sendDelete(URL url,
			Map<String, String> headers, Map<String, String> data) {
		CloseableHttpClient client = HttpClients.createDefault();
		OnceHttpDelete httpDelete = new OnceHttpDelete(url.toString());
		httpDelete.addHeader("User-Agent", CONST.USER_AGENT);
		for (String key : headers.keySet()) {
			httpDelete.addHeader(key, headers.get(key));
		}
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		for (String key : data.keySet()) {
			urlParameters.add(new BasicNameValuePair(key, data.get(key)));
		}

		try {
			httpDelete.setEntity(new UrlEncodedFormEntity(urlParameters, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		CloseableHttpResponse response;
		try {
			response = client.execute(httpDelete);
			int statusCode = response.getStatusLine().getStatusCode();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		StringBuffer contentStringBuffer = new StringBuffer();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(response
					.getEntity().getContent()));
			String line;

			while ((line = reader.readLine()) != null) {
				contentStringBuffer.append(line);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return contentStringBuffer.toString();
	}
}
