package com.utils;

import java.io.UnsupportedEncodingException;
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
        Map<String, String> data = new HashMap<String, String>();
        data.put("_id", uuid);
        data.put("Module", moduleName);
        data.put("Method", methodName);
		String response = Connection.sendPost(urlString, data);
		System.out.println(response);
		return true;
	}
}
