package main.java.com.once.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class VM {
	private String uuid = null;
	
	
	public static void create(String uuid, String name, int memory, int vcpu, String diskDir, String isoDir, String bridgeSrc) throws MalformedURLException
    {
		create0(uuid, name, memory, vcpu, diskDir, isoDir, bridgeSrc);
    }
	
	public static void create(String name, int memory, int vcpu, String diskDir, String isoDir, String bridgeSrc) throws MalformedURLException
    {
		create0("27167fe7-fc9d-47d5-9cd0-717106ef67be", name, memory, vcpu, diskDir, isoDir, bridgeSrc);
    }
	

    /*******************************************
    Author: LHearen
    E-mail: LHearen@126.com
    Time  :	2015-12-03 16:19
    Description: according to the given parameters
    to create a VM in remote host;
    *******************************************/         
    public static void create0(String uuid, String name, int memory, int vcpu, String diskDir, String isoDir, String bridgeSrc) throws MalformedURLException
    {
    	/*
    	 * used to post a VM configuration to server;
    	 * curl -X POST -H 'Content-Type: application/json'  http://133.133.135.13:5100/VM -d '{"uuid": "7504b4c5dd1543d6b469f701a4a3c3a8", "isoDir": "/home/res/iso/CentOS-7.1.iso", "diskDir": "/home/res/images/test1.qcow2", "bridgeSrc": "ovs0", "name": "vm", "memory": 1024, "vcpu": 2,"powerstate": "running"}'
    	 */
        Map<String, String> header = new HashMap<String, String>();
        header.put("Module", "VM");
        header.put("Method", "create");
//        String parameters = "{'uuid':" + "'vm'," + "'name':'" + name + "','memory':'" + Integer.toString(memory) + "','vcpu':'" + Integer.toString(vcpu) +
//        		"','diskDir':'" + diskDir + "','isoDir':'" + isoDir + "','bridgeSrc':'" + bridgeSrc + "'}";
//        header.put("Params", parameters);
        URL url = new URL("HTTP", "133.133.135.13", 5100, "/VM/");
        Map<String, String> data = new HashMap<String, String>();
//        data.put("uuid", "74697a47-9eed-471e-9fd2-f925cf852612");
        data.put("_id", uuid);
        data.put("name", name);
        data.put("memory", Integer.toString(memory));
        data.put("vcpu", Integer.toString(vcpu));
        data.put("diskDir", diskDir);
        data.put("isoDir",  isoDir);
        data.put("bridgeSrc", bridgeSrc);
        System.out.println(data);
        String response = Connection.sendPost(url, header, data);
        System.out.println(response);
    }
        
	

}
