package com.beyondsphere.xenapi;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;

import com.beyondsphere.xenapi.Types.BadServerResponse;
import com.beyondsphere.xenapi.Types.SessionAuthenticationFailed;
import com.beyondsphere.xenapi.Types.XenAPIException;

public class Test_vm_opt {
	public static void main(String[] args) throws BadServerResponse,
	SessionAuthenticationFailed, MalformedURLException,
	XenAPIException, XmlRpcException {
		URL url = new URL("HTTP", "133.133.135.13", 9363, "/");
		Connection c = new Connection(url);
		Session s = Session.loginWithPassword(c, "root", "onceas", null);
		VM vm = Types.toVM("3679bcd8-0003-4878-af50-f50136a88523");//test1
		
//		vm.test4Test(c);
//		VM vm = VM.createByXml4Test(c, "/root/libvirt/test0.xml");
//		VM.Record record = vm.getRecord(c);
//		System.out.println(record);
		
		
		
		vm.start4test(c, 0);	
		
		vm.shutdown4test(c, 0);		
//		vm.delete4test(c,0);
		
		Boolean isTemplate = vm.getIsATemplate(c);
		System.out.println(vm.getUuid(c) + " is a template? ");
		System.out.println(isTemplate);
		
		vm.setIsATemplate(c, true);
		
		isTemplate = vm.getIsATemplate(c);
		System.out.println(vm.getUuid(c) + " is a template? ");
		System.out.println(isTemplate);
		/**重启虚拟机*/
//		vm.reboot4Test(c);	
		
//		String id = VM.createByXml4Test(c, "/root/libvirt/test3.xml");
//		System.out.println(id);
//		vm.migrate4Test(c, s);
//		
	}

}
