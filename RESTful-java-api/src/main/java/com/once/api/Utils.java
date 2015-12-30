package com.once.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/*******************************************
Author: LHearen
E-mail: LHearen@126.com
Time  :	2015-12-03 16:21
Description: Used to assist other classes to 
archive certain goals;
*******************************************/
public class Utils {
	public static boolean sendMethod(String urlString, String moduleName, String methodName, String uuid)
	{
		Map<String, String> header = new HashMap<String, String>();
        header.put("Module", moduleName);
        header.put("Method", methodName);
        Map<String, String> data = new HashMap<String, String>();
        data.put("_id", uuid);
        URL url = null;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		}
		String response = Connection.sendPatch(url, header, data);
		System.out.println(response);
		return true;
	}
}
