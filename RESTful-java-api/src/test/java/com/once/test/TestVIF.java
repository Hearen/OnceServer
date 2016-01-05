package com.once.test;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import com.once.api.VIFConfig;
import com.once.api.VIF;

public class TestVIF {
	public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException {
		VIFConfig config = new VIFConfig("5d50e6a2-c524-4646-9edb-61a3ce5880bb", "vif0", "00:16:3e:7a:48:f8", "ovs0");
//		VIF.create(config);
		String uuidString = "5d50e6a2-c524-4646-9edb-61a3ce5880bb";
		VIF.delete(uuidString);
	}
}
