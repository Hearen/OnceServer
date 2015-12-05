package com.beyondsphere.xenapi;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.apache.xmlrpc.XmlRpcException;

import com.beyondsphere.xenapi.Types.BadServerResponse;
import com.beyondsphere.xenapi.Types.SessionAuthenticationFailed;
import com.beyondsphere.xenapi.Types.XenAPIException;

public class Test_vm_create {
	public static void main(String[] args) throws BadServerResponse,
	SessionAuthenticationFailed, MalformedURLException,
	XenAPIException, XmlRpcException {
		URL url = new URL("HTTP", "133.133.135.13", 9363, "/");
		Connection c = new Connection(url);
		Session s = Session.loginWithPassword(c, "root", "onceas", null);	
		
		String domid = "21";
		String name = "test";
	    String memory = "1024";
	    String vcpu = "2";
	    HashMap<String,String> hvm = new HashMap<String,String>();
	    hvm.put("loader", "/usr/lib/xen/boot/hvmloader");
	    hvm.put("boot", "cdrom");
	    hvm.put("device_model", "/usr/lib64/xen/bin/qemu-dm");	    
	    HashMap<String,HashMap<String,String>> image = new HashMap<String,HashMap<String,String>>();
	    image.put("hvm", hvm);
	    
	    HashMap<String,String> vbd = new HashMap<String,String>();
	    vbd.put("dev", "hda:disk");
	    vbd.put("uname","tap:aio:/home/test/test.img");
	    vbd.put("mode", "w");
	    
	    HashMap<String,String> tap2 = new HashMap<String,String>();
	    tap2.put("dev", "hdc:cdrom");
	    tap2.put("uname","tap:aio:/home/iso/CentOS-6.3-x86_64-bin-DVD1.iso");	    
	    tap2.put("mode", "r");
	    
	    HashMap<String,String> vif = new HashMap<String,String>();
	    vif.put("mac", "00:16:3e:d4:81:07");
	    vif.put("bridge", "ovs0");
	    
	    HashMap<String,String> console = new HashMap<String,String>();
	    console.put("location", "0");
	    
	    HashMap<String,String> vfb = new HashMap<String,String>();
	    vfb.put("location", "0.0.0.0:5900");	  
	    vfb.put("vnclisten", "0.0.0.0");	
	    
		VM vm = VM.create4test(c,domid, name, memory, vcpu, image, tap2, vif, vbd, vfb, console);			
	}
}
