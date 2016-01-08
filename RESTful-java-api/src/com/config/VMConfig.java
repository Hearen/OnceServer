package com.config;

import java.util.HashMap;
import java.util.Map;

/*******************************************
Author      : LHearen
E-mail      : LHearen@126.com
Time        : 2015-12-16 16 : 29
Description : Used to record the basic configuration
        information of VM, used as paramter for clarity;
*******************************************/
public class VMConfig {
	//a default uuid should be provided to support Eve validation
	//if no uuid will be provided by the clients, the serer will 
	//replace it with the newly created uuid of the vm;
	private String uuid;
	
	private String name;
	private int memory;
	private int vcpu;
	private String diskDir;
	private String isoDir;
	private String mac;
	private String bridgeSrc;
	
    /*******************************************
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-16 16 : 32
    Description : The very basic parameters should always be provided;
    *******************************************/
	public VMConfig(int vcpu0, int memory0, String diskDir0, String isoDir0, String bridgeSrc0)
	{
		vcpu = vcpu0;
		memory = memory0;
		diskDir = diskDir0;
		isoDir = isoDir0;
		bridgeSrc = bridgeSrc0;
	}
	
    /*******************************************
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-16 16 : 31
    Description : default will be used, if this method is not invoked;
    *******************************************/
	public void setUUID(String uuid0)
	{
		uuid = uuid0;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
    /*******************************************
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-16 16 : 32
    Description : default will be used, if this method is not invoked;
    *******************************************/
	public void setMac(String mac0)
	{
		mac = mac0;
	}
	
    /*******************************************
    Author      : LHearen
    E-mail      : LHearen@126.com
    Time        : 2015-12-16 16 : 31
    Description : Used as data to be sent to the server;
    *******************************************/
	public Map<String, String> toMap()
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("_id", uuid);
		map.put("name", name);
		map.put("memory", "" + memory);
		map.put("vcpu", "" + vcpu);
		map.put("mac", mac);
		map.put("diskDir", diskDir);
		map.put("isoDir", isoDir);
		map.put("bridgeSrc", bridgeSrc);
		return map;
	}
}
