package com.beyondsphere.xenapi;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;

import com.beyondsphere.xenapi.Types.BadServerResponse;
import com.beyondsphere.xenapi.Types.SessionAuthenticationFailed;
import com.beyondsphere.xenapi.Types.XenAPIException;

public class Test_vdi_opt {
	public static void main(String[] args) throws BadServerResponse,
	SessionAuthenticationFailed, MalformedURLException,
	XenAPIException, XmlRpcException {
		URL url = new URL("HTTP", "133.133.135.13", 9363, "/");
		Connection c = new Connection(url);
		Session s = Session.loginWithPassword(c, "root", "onceas", null);
		
//		VDI vdi = VDI.create4Test(c, "pool", "vol_test", 10);
		
		VDI.delete4Test(c, "pool_test", "vol_test_ref");
		
//		String vols = VDI.list4Test(c, "pool");
//		System.out.println(vols);
		
//		VDI.cloneByLink4Test(c, "vol_test", "vol_test_ref", "pool_test");
		
//		VDI.cloneByCopy4Test(c, "vol_test",  "pool_test",  "vol_test_ref", "pool_test");
		
		VM vm = Types.toVM("6b38267e-6c2e-4e13-a056-309567ca0d7e");//test1
		vm.attachVolume4Test(c, "pool", "new", "hdd");
		
		vm.detachVolume4Test(c, "hdd");
	}

}
