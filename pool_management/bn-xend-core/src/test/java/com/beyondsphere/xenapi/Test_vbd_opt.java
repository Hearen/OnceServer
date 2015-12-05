package com.beyondsphere.xenapi;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;

import com.beyondsphere.xenapi.Types.BadServerResponse;
import com.beyondsphere.xenapi.Types.SessionAuthenticationFailed;
import com.beyondsphere.xenapi.Types.XenAPIException;

public class Test_vbd_opt {
	public static void main(String[] args) throws BadServerResponse, SessionAuthenticationFailed, MalformedURLException, XenAPIException, XmlRpcException {
		URL url = new URL("HTTP", "133.133.135.13", 9363, "/");
		Connection c = new Connection(url);
		Session s = Session.loginWithPassword(c, "root", "onceas", null);
		
		VBD vbd = VBD.create4Test(c, 10, "/home/test/images/test1.img", "hdb");
//		Map<String, VBD.Record> records = VBD.getAllRecords(c);
//		System.out.println(records);
//		VM vm = Types.toVM("416cfcb0-f27b-4a24-a3ae-cec471a10a66");
//		vm.attachVBD4Test(c, "/home/test/images/test1.xml");
//		vm.detachVBD4Test(c, "/home/test/images/test0.xml");
		
//		VBD.delete4Test(c, "/home/test/images/test1.img");
		vbd.backup4Test(c, "/home/test/images/test1.img", "/home/test/images/test2.img");
	}

}
