package com.test;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import com.base.VIF;
import com.config.VIFConfig;

public class TestVIF {
	public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException {
		VIFConfig config = new VIFConfig("5d50e6a2-c524-4646-9edb-61a3ce5880bb", "vif0", "ovs0", "00:16:3e:7a:48:f8");
//		VIF.create(config);
		String vifUuidString = "5d50e6a2-c524-4646-9edb-61a3ce5880bb";
		String vmUuidString = "57f28539-489f-0a5f-0bf4-9abe45c62763";
//		VIF.delete(vifUuidString);
		VIF.attach(vmUuidString, vifUuidString);
//		VIF.detach(vmUuidString, vifUuidString);
	}
}
