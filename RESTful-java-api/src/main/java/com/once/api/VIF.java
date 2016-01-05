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
    	/*
    	 * used to post a VM configuration to server;
    	 * curl -X POST -H 'Content-Type: application/json'  http://133.133.135.13:5100/VM -d '{"uuid": "7504b4c5dd1543d6b469f701a4a3c3a8", "isoDir": "/home/res/iso/CentOS-7.1.iso", "diskDir": "/home/res/images/test1.qcow2", "bridgeSrc": "ovs0", "name": "vm", "memory": 1024, "vcpu": 2,"powerstate": "running"}'
    	 */
    	Map<String, String> data = config.toMap();
    	data.put("Module", "VIF");
    	data.put("Method", "create");
    	System.out.println(data.toString());
        String response = Connection.sendPost(urlString,data);
        System.out.println(response);
    }
    
    public static void delete(String uuidString) 
    {
    	/*
    	 * used to post a VM configuration to server;
    	 * curl -X POST -H 'Content-Type: application/json'  http://133.133.135.13:5100/VM -d '{"uuid": "7504b4c5dd1543d6b469f701a4a3c3a8", "isoDir": "/home/res/iso/CentOS-7.1.iso", "diskDir": "/home/res/images/test1.qcow2", "bridgeSrc": "ovs0", "name": "vm", "memory": 1024, "vcpu": 2,"powerstate": "running"}'
    	 */
    	Map<String, String> data = new HashMap<String, String>();
    	data.put("_id", uuidString);
    	data.put("Module", "VIF");
    	data.put("Method", "delete");
        String response = Connection.sendPost(urlString, data);
        System.out.println(response);
    }
}
