package com.config;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/*******************************************
Author: Wu Yuewen
E-mail: wuyuewen@otcaix.iscas.ac.cn
Time  :	2015-12-24 15:30
Description : Used to record the basic configuration
        information of VIF, used as paramter for clarity;
 *******************************************/  
public class VIFConfig {

	private String uuidString;
	private String name;
	private String macString;
	private String source;
	
	public VIFConfig(String uuidString, String name, String source, String macString) {
		super();
		this.uuidString = uuidString;
		this.name = name;
		this.macString = macString;
		this.source = source;
	}

	public Map<String, String> toMap()
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("_id", uuidString);
		map.put("name", name);
		map.put("macString", macString);
		map.put("source", source);
		return map;
	}	
}
