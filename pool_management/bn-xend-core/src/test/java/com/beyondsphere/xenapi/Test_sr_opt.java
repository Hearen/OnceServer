package com.beyondsphere.xenapi;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;

import com.beyondsphere.xenapi.Types.BadServerResponse;
import com.beyondsphere.xenapi.Types.SessionAuthenticationFailed;
import com.beyondsphere.xenapi.Types.XenAPIException;

public class Test_sr_opt {
	public static void main(String[] args) throws BadServerResponse,
	SessionAuthenticationFailed, MalformedURLException,
	XenAPIException, XmlRpcException {
		URL url = new URL("HTTP", "133.133.135.13", 9363, "/");
		Connection c = new Connection(url);
		Session s = Session.loginWithPassword(c, "root", "onceas", null);
		
//		SR sr = SR.create4Test(c, "pool", "/home/test");
		SR.delete4Test(c, "pool");
//		String poolNames = SR.list4Test(c);
//		System.out.println(poolNames);
	}

}
