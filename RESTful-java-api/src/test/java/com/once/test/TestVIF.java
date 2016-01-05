package com.once.test;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import com.once.api.VIFConfig;
import com.once.api.VIF;

public class TestVIF {
	public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException {
		VIFConfig config = new VIFConfig("5d50e6a2-c524-4646-9edb-61a3ce5880bb", 
				"f255c752-c3d1-4b5b-8dab-106dba5bc400", "bridge", "00:16:3e:7a:48:f8", "ovs0");
		VIF.create(config);
//		VIFConfig config1 = new VIFConfig("5d50e6a2-c524-4646-9edb-61a3ce5880bb", 
//				"f255c752-c3d1-4b5b-8dab-106dba5bc400", null, "00:16:3e:7a:48:f8", null);
		VIF.delete(config);
	}
}
