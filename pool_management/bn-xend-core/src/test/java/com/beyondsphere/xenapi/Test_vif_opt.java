package com.beyondsphere.xenapi;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;

import com.beyondsphere.xenapi.Types.BadServerResponse;
import com.beyondsphere.xenapi.Types.SessionAuthenticationFailed;
import com.beyondsphere.xenapi.Types.XenAPIException;

public class Test_vif_opt {
	public static void main(String[] args) throws BadServerResponse, SessionAuthenticationFailed, MalformedURLException, XenAPIException, XmlRpcException {
		URL url = new URL("HTTP", "133.133.135.13", 9363, "/");
		Connection c = new Connection(url);
		Session s = Session.loginWithPassword(c, "root", "onceas", null);
		
		VM vm = Types.toVM("6b38267e-6c2e-4e13-a056-309567ca0d7e");//test1
		
		
		VIF vif = VIF.create4Test(c, "eth1", "ovs0");
		
//		vm.attachVIF4Test(c, "eth1");
//		VIF vif0 = Types.toVIF("cbaaea42-e33c-40c2-b6b5-28eb18e04712");
//		VIF vif0 = VIF.getByUuid(c, "cbaaea42-e33c-40c2-b6b5-28eb18e04712");
//		System.out.println(vif0.toString());
//		System.out.println(vif0.ref);
//		VIF.Record  record = vif0.getRecord(c);
//		System.out.println(record);
//		vm.detachVIF4Test(c, "eth1");
//		
//		VIF.destroy4Test(c, "eth1");
	}

}
