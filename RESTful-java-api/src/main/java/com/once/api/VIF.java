package com.once.api;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class VIF {
	private static final String urlString = "http://" + CONST.HOST + ":" + CONST.PORT + "/VIFs/";
	 
    public static void create(VIFConfig config)
    {
    	Map<String, String> data = config.toMap();
    	data.put("Module", "VIF");
    	data.put("Method", "create");
        String response = Connection.sendPost(urlString,data);
        System.out.println(response);
    }
    
    public static void delete(String uuidString) 
    {
    	Map<String, String> data = new HashMap<String, String>();
    	data.put("_id", uuidString);
    	data.put("Module", "VIF");
    	data.put("Method", "delete");
        String response = Connection.sendPost(urlString, data);
        System.out.println(response);
    }
    
    public static void attach(String vmUuidString, String vifUuidString)
    {
    	Map<String, String> data = new HashMap<String, String>();
    	data.put("vm_id", vmUuidString);
    	data.put("vif_id", vifUuidString);
    	data.put("Module", "VIF");
    	data.put("Method", "attach");
        String response = Connection.sendPost(urlString, data);
        System.out.println(response);
    }
    
    public static void detach(String vmUuidString, String vifUuidString)
    {
    	Map<String, String> data = new HashMap<String, String>();
    	data.put("vm_id", vmUuidString);
    	data.put("vif_id", vifUuidString);
    	data.put("Module", "VIF");
    	data.put("Method", "detach");
        String response = Connection.sendPost(urlString, data);
        System.out.println(response);
    }
}
