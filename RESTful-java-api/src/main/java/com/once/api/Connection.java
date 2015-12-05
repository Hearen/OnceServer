package main.java.com.once.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BasicListHeaderIterator;

public class Connection
{
    /*******************************************
    Author: LHearen
    E-mail: LHearen@126.com
    Time  :	2015-12-03 16:20
    Description: encapulate all parameters in 
    headers and post to a certain url;
    *******************************************/ 
    public static String sendPost(URL url, Map<String, String> headers)
    {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url.toString());
        headers.put("User-Agent", CONST.USER_AGENT);
        for(String key: headers.keySet())
        {
            post.addHeader(key, headers.get(key));
        }
        CloseableHttpResponse response = null;
        try
        {
        	response = client.execute(post);
	        int statusCode = response.getStatusLine().getStatusCode();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	return null;
        }
        BufferedReader reader = null;
        StringBuffer contentStringBuffer = new StringBuffer();
		try {
			reader = new BufferedReader(new 
			        InputStreamReader(response.getEntity().getContent()));
			String line;
	        while ((line = reader.readLine()) != null) 
			{
			    contentStringBuffer.append(line);
			}
	        reader.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}
        

        return contentStringBuffer.toString();
    }

    /*******************************************
    Author: LHearen
    E-mail: LHearen@126.com
    Time  :	2015-12-03 16:20
    Description: used to retrieve resources from eve;
    *******************************************/ 
    public static String sendGet(URL url)
    {
    	CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url.toString());
		get.addHeader("User-Agent", CONST.USER_AGENT);
		CloseableHttpResponse response;
		try {
			response = client.execute(get);
			int statusCode = response.getStatusLine().getStatusCode();
		}
    	catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		
		StringBuffer contentStringBuffer = new StringBuffer();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line;
			
			while ((line = reader.readLine()) != null) {
				contentStringBuffer.append(line);
			}
			reader.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		return contentStringBuffer.toString();
    }
}
