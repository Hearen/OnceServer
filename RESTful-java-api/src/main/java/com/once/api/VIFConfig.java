package com.once.api;

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

	private String uuid;
	private String vmUuid;
	private String netType;
	private String mac;
	private String source;
	
	public VIFConfig(String uuid, String vmUuid, String netType, String mac,
			String source) {
		super();
		this.uuid = uuid;
		this.vmUuid = vmUuid;
		this.netType = netType;
		this.mac = mac;
		this.source = source;
	}

	public Map<String, String> toMap()
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("_id", uuid);
		map.put("vm_id", vmUuid);
		map.put("net_type", netType);
		map.put("mac", mac);
		map.put("source", source);
		return map;
	}

	public String toString()
	{
		StringWriter writer = new StringWriter();
		PrintWriter print = new PrintWriter(writer);
		print.printf("%1$20s: %2$s\n", "_id", this.uuid);
		print.printf("%1$20s: %2$s\n", "vm_id", this.vmUuid);
		print.printf("%1$20s: %2$s\n", "net_type", this.netType);
		print.printf("%1$20s: %2$s\n", "mac", this.mac);
		print.printf("%1$20s: %2$s\n", "source", this.source);
		return writer.toString();
	}
	
}
