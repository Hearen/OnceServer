package main.java.com.once.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class VM {
	private String uuid = null;

    /*******************************************
    Author: LHearen
    E-mail: LHearen@126.com
    Time  :	2015-12-03 16:19
    Description: according to the given parameters
    to create a VM in remote host;
    *******************************************/         
    public static void create(String name, int memory, int vcpus, String diskDir, String isoType, String bridgeSrc) throws MalformedURLException
    {
        Map<String, String> header = new HashMap<String, String>();
        header.put("Module", "VM");
        header.put("Method", "create");
        String parameters = "{'name':'" + name + "','memory':'" + Integer.toString(memory) + "','vcpus':'" + Integer.toString(vcpus) +
        		"','diskDir':'" + diskDir + "','isoType':'" + isoType + "','bridgeSrc':'" + bridgeSrc + "'}";
        header.put("Params", parameters);
        URL url = new URL("HTTP", "133.133.135.13", 5100, "/VM/7504b4c5dd1543d6b469f701a4a3c3a8");
        Connection.sendPost(url, header);
    }
        
	

}
