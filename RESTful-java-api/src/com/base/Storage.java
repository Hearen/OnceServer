package com.base;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import com.utils.CONST;
import com.utils.Connection;

public class Storage {
	private static final String urlString = "http://" + CONST.HOST + ":" + CONST.PORT;
	
	public static void createPool(String poolName, String target) throws UnsupportedEncodingException, MalformedURLException
	{
        Map<String, String> data = new HashMap<String, String>();
        data.put("Module", "VBD");
        data.put("Method", "createPool");
//        data.put("_id", "27167fe7-fc9d-47d5-9cd0-717106ef67be");
        data.put("_id", "");
        data.put("name", poolName);
        data.put("target", target);
        String response = Connection.sendPost(urlString+"/StoragePools/", data);
        System.out.println(response);
	}
	
	public static void deletePool(String uuidString) throws UnsupportedEncodingException
	{
		Map<String, String> data = new HashMap<String, String>();
        data.put("Module", "VBD");
        data.put("Method", "deletePool");
        data.put("_id", uuidString);
        String response = Connection.sendPost(urlString+"/StoragePools/", data);
        System.out.println(response);
	}
	
	public static String listPools() throws UnsupportedEncodingException
	{
		Map<String, String> data = new HashMap<String, String>();
        data.put("Module", "VBD");
        data.put("Method", "listPools");
        String response = Connection.sendPost(urlString+"/StoragePools/", data);
        System.out.println(response);
		return "";
	}
	
	public static void createVolume(String poolName, String volName, int volSize) throws MalformedURLException, UnsupportedEncodingException
	{
        Map<String, String> data = new HashMap<String, String>();
//        data.put("_id", "27167fe7-fc9d-47d5-9cd0-717106ef67be");
        data.put("Module", "VBD");
        data.put("Method", "createVolume");
        data.put("_id", "27167fe7-fc9d-47d5-9cd0-717106ef67be");
        data.put("poolName", poolName);
        data.put("volName", volName);
        data.put("volSize", ""+volSize);
        String response = Connection.sendPost(urlString+"/Volumes/", data);
        System.out.println(response);
	}
	
	public static void deleteVolume(String poolName, String volName) throws MalformedURLException, UnsupportedEncodingException
	{
        Map<String, String> data = new HashMap<String, String>();
        data.put("Module", "VBD");
        data.put("Method", "deleteVolume");
        data.put("_id", "27167fe7-fc9d-47d5-9cd0-717106ef67be");
        data.put("poolName", poolName);
        data.put("volName", volName);
        String response = Connection.sendPost(urlString+"/Volumes/", data);
        System.out.println(response);
	}
	
	public static void listVolumes(String poolName) throws MalformedURLException, UnsupportedEncodingException
	{
        Map<String, String> data = new HashMap<String, String>();
//        data.put("_id", "27167fe7-fc9d-47d5-9cd0-717106ef67be");
        data.put("Module", "VBD");
        data.put("Method", "listVolumes");
        data.put("poolName", poolName);
        String response = Connection.sendPost(urlString+"/Volumes/", data);
        System.out.println(response);
	}
	
	public static void attach(String vm_id, String vol_id, String target)
	{
		Map<String, String> data = new HashMap<String, String>();
//      data.put("_id", "27167fe7-fc9d-47d5-9cd0-717106ef67be");
      data.put("Module", "VBD");
      data.put("Method", "attachVolume");
      data.put("vm_id", vm_id);
      data.put("vol_id" , vol_id);
      data.put("target", target);
      String response = Connection.sendPost(urlString+"/Volumes/", data);
      System.out.println(response);
	}
	
	public static void detach(String vm_id, String vol_id)
	{
		Map<String, String> data = new HashMap<String, String>();
//      data.put("_id", "27167fe7-fc9d-47d5-9cd0-717106ef67be");
      data.put("Module", "VBD");
      data.put("Method", "detachVolume");
      data.put("vm_id", vm_id);
      data.put("vol_id", vol_id);
      String response = Connection.sendPost(urlString+"/Volumes/", data);
      System.out.println(response);
	}
}
