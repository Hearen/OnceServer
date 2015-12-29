package com.once.api;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class VIF {
	private static final String urlString = "http://" + CONST.HOST + ":" + CONST.PORT + "/VM/";
	
    /*******************************************
    Author: Wu Yuewen
    E-mail: wuyuewen@otcaix.iscas.ac.cn
    Time  :	2015-12-24 15:30
    Description: according to the given parameters
    to create a VIF in remote host;
     * @throws UnsupportedEncodingException 
    *******************************************/  
    public static void create(VIFConfig config) throws MalformedURLException, UnsupportedEncodingException
    {
    	/*
    	 * used to post a VM configuration to server;
    	 * curl -X POST -H 'Content-Type: application/json'  http://133.133.135.13:5100/VM -d '{"uuid": "7504b4c5dd1543d6b469f701a4a3c3a8", "isoDir": "/home/res/iso/CentOS-7.1.iso", "diskDir": "/home/res/images/test1.qcow2", "bridgeSrc": "ovs0", "name": "vm", "memory": 1024, "vcpu": 2,"powerstate": "running"}'
    	 */
        Map<String, String> header = new HashMap<String, String>();
        header.put("Module", "VIF");
        header.put("Method", "create");
        URL url = new URL(urlString);
        String response = Connection.sendPost(url, header, config.toMap());
        System.out.println(response);
    }
}
