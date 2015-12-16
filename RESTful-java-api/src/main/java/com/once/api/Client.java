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
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BasicListHeaderIterator;

public class Client {

	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String POST_URL = "http://localhost:9090/SpringMVCExample/home";

	public static void main(String[] args) throws IOException {
		VMConfig config = new VMConfig("vm", 2, 1024, "/home/res/images/test1.qcow2", "/home/res/iso/CentOS-7.1.iso", "ovs0");
		VM.create(config);
		String uuid = "9ae89b00-639f-4b28-a38c-b7bb4910a7b1";
//		VM.start(uuid);
//		VM.shutdown(uuid);
//		VM.reboot(uuid);
//		VM.delete(uuid);
		System.out.println(VM.isTemplate(uuid));
		VM.setTemplate(uuid);
		System.out.println(VM.isTemplate(uuid));
//		URL url = new URL("HTTP", "133.133.135.13", 5100,
//				"/VM/27167fe7-fc9d-47d5-9cd0-717106ef67be");
//		 StringBuffer result = sendGET(url);
//		 System.out.println(result);
//		Map<String, String> headers = new HashMap<String, String>();
//		headers.put("Method", "VM_start");
//		headers.put("Params",
//				"{\"uuid\": \"7504b4c5dd1543d6b469f701a4a3c3a8\", \"flags\": 0}");
//		Map<String, String> data = new HashMap<String, String>();
//		data.put("powerstate", "running");
//		String result = sendPATCH(url, headers, data);
//		System.out.println(result);
//		 sendPOST();
//		 System.out.println("POST DONE");
	}

	private static StringBuffer sendGET(URL url) throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url.toString());
		httpGet.addHeader("User-Agent", USER_AGENT);
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

		System.out.println("GET Response Status: "
				+ httpResponse.getStatusLine().getStatusCode());

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				httpResponse.getEntity().getContent()));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = reader.readLine()) != null) {
			// response.append(inputLine).append(System.getProperty("line.separator"));
			response.append(inputLine);
		}
		reader.close();

		// print result
		// System.out.println(response.toString());
		httpClient.close();
		return response;
	}

	private static String sendPOST(URL url, Map<String, String> headers,
			Map<String, String> data) throws IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url.toString());
		httpPost.addHeader("User-Agent", USER_AGENT);
		for (String key : headers.keySet()) {
			httpPost.addHeader(key, headers.get(key));
		}

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		for (String key : data.keySet()) {
			urlParameters.add(new BasicNameValuePair(key, data.get(key)));
		}
		// urlParameters.add(new BasicNameValuePair("userName",
		// "Pankaj Kumar"));

		HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
		httpPost.setEntity(postParams);

		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

		System.out.println("POST Response Status: "
				+ httpResponse.getStatusLine().getStatusCode());

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				httpResponse.getEntity().getContent()));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = reader.readLine()) != null) {
			response.append(inputLine);
		}
		reader.close();

		httpClient.close();
		return response.toString();
	}

	private static String sendPATCH(URL url, Map<String, String> headers,
			Map<String, String> data) throws IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPatch httpPatch = new HttpPatch(url.toString());
		httpPatch.addHeader("User-Agent", USER_AGENT);
		for (String key : headers.keySet()) {
			httpPatch.addHeader(key, headers.get(key));
		}
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		for (String key : data.keySet()) {
			urlParameters.add(new BasicNameValuePair(key, data.get(key)));
		}
		// urlParameters.add(new BasicNameValuePair("userName",
		// "Pankaj Kumar"));

		HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
		httpPatch.setEntity(postParams);

		CloseableHttpResponse httpResponse = httpClient.execute(httpPatch);

		System.out.println("POST Response Status: "
				+ httpResponse.getStatusLine().getStatusCode());

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				httpResponse.getEntity().getContent()));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = reader.readLine()) != null) {
			response.append(inputLine);
		}
		reader.close();

		httpClient.close();
		return response.toString();

	}

}