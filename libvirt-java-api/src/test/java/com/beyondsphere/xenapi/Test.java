package com.beyondsphere.xenapi;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.xmlrpc.XmlRpcException;

import com.beyondsphere.xenapi.Connection;
import com.beyondsphere.xenapi.Host;
import com.beyondsphere.xenapi.SR;
import com.beyondsphere.xenapi.Types;
import com.beyondsphere.xenapi.VBD;
import com.beyondsphere.xenapi.VDI;
import com.beyondsphere.xenapi.VIF;
import com.beyondsphere.xenapi.VM;
import com.beyondsphere.xenapi.VMCPUQoS;
import com.beyondsphere.xenapi.VMMetrics;
import com.beyondsphere.xenapi.Types.BadServerResponse;
import com.beyondsphere.xenapi.Types.SessionAuthenticationFailed;
import com.beyondsphere.xenapi.Types.XenAPIException;

import org.json.JSONArray;
import org.json.JSONObject;

public class Test {
	public static void main(String[] args) throws BadServerResponse,
			SessionAuthenticationFailed, MalformedURLException,
			XenAPIException, XmlRpcException {
//		Connection c = new Connection("http://133.133.135.37:9363", "root",
//				"onceas");
		URL url = new URL("HTTP", "133.133.135.37", 9363, "/");
		Connection c = new Connection(url);
		Session s = Session.loginWithPassword(c, "root", "onceas", null);
//		System.out.println(s.getRecord(c));
		
		VM vm = Types.toVM("1fb24c6e-cbc3-43fa-ad32-58edd23ca210");//test2
		
		/**开启虚拟机*/
//		vm.start4test(c, 0);		
		/**关闭虚拟机*/
//		vm.shutdown4test(c, 0);		
		/**销毁虚拟机*/
//		vm.destroy4test(c,0);		
		/**重启虚拟机*/
//		vm.reboot4Test(c);		
		/**给虚拟机添加虚拟网卡，参数：网卡名称*/
//		vm.attachVIF4Test(c, "testnet1","virbri2");
		/**从虚拟机上卸载虚拟网卡
		 * mac地址*/
//		vm.detachVIF4Test(c,"testnet1", "virbri1","00:16:3e:60:62:cc");
		/**虚拟机迁移，未测试*/
//		URL durl = new URL("HTTP", "133.133.135.37", 9363, "/");
//		Connection dc = new Connection(durl);
//		Session ds = Session.loginWithPassword(dc, "root", "onceas", null);		
//		vm.migrate4Test(c, ds);
		/**新建虚拟网卡，状态not active
		 * 参数：MAC、IP、VLAN、驱动方式、关联的网桥
		 * IP包括IP地址,子网掩码,DHCP的起始地址和结束地址*/
	    HashMap<String,String> ip = new HashMap<String,String>();
	    ip.put("address", "192.168.124.1");
	    ip.put("netmask", "255.255.255.0");
	    ip.put("start", "192.168.124.2");
	    ip.put("end", "192.168.124.254");
//		VIF vif = VIF.create4Test(c, "testnet1", "52:54:00:d3:09:8b",ip, "0", "nat", "virbri2");
		
		/**删除虚拟网卡,只有not active的网卡才能被删除*/
//		VIF vif = VIF.destroy4Test(c, "testnet1");
		
		/**新建虚拟机*/
/*		String domid = "21";
		String name = "test3";
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
	    vbd.put("uname","tap:aio:/home/test/test3.img");
	    vbd.put("mode", "w");
	    
	    HashMap<String,String> tap2 = new HashMap<String,String>();
	    tap2.put("dev", "hdc:cdrom");
	    tap2.put("uname","tap:aio:/home/iso/Windows_Server_2008_R2_x64.iso");	    
	    tap2.put("mode", "r");
	    
	    HashMap<String,String> vif = new HashMap<String,String>();
	    vif.put("mac", "00:16:3e:d4:81:08");
	    vif.put("bridge", "br0");
	    
	    HashMap<String,String> console = new HashMap<String,String>();
	    console.put("location", "0");
	    
	    HashMap<String,String> vfb = new HashMap<String,String>();
	    vfb.put("location", "0.0.0.0:5900");	  
	    vfb.put("vnclisten", "0.0.0.0");	
	    
		VM vm = VM.create4test(c,domid, name, memory, vcpu, image, tap2, vif, vbd, vfb, console);*/		
		
//		Host host = Types.toHost("866c0d0f-21e4-0474-3e63-54d8ac00be8b");
//		Host host = Types.toHost("f7ab0c4a-f6fa-493b-65f4-95e9b2a6e860");
//		System.out.println(host.enableVxlan(c, "ovs2"));
//		System.out.println(Network.getAllRecords(c, host));
//		Pool.eject(c, host);
//		VM vm = Types.toVM("00000000-0000-0000-0000-000000000012");
//		VM vm = Types.toVM("4289dfdd-dc5b-48ca-9bb1-40039e22a0da");
//		VIF vif = Types.toVIF("ab7c9b1c-4474-da6f-c851-b622bef39fca");
//		System.out.println(vm.getNetworkRecord(c, vif));
//		VDI vdi = Types.toVDI("762deb46-8b7e-4062-813f-d72d9e6ed855");
//		VM.deleteDataVBD(c, "c660d1b3-874a-44dd-a30b-6fe66f38d2f4", "762deb46-8b7e-4062-813f-d72d9e6ed855");
//		VM.createDataVBD(c, "c660d1b3-874a-44dd-a30b-6fe66f38d2f4", "762deb46-8b7e-4062-813f-d72d9e6ed855");
//		System.out.println(vm.getRecord(c));
//		SR sr = SR.getByUuid(c, "a41a7317-5f25-4a8a-a63a-ee572e654dab");
//		SR.Record sr_record = sr.getRecord(c);
//		System.out.println(sr_record.physicalSize);
//		System.out.println(sr_record.physicalUtilisation);
//		VM vm = Types.toVM("d977cb8b-6083-4a27-a3d2-78956430bb50");
//		VIF vif = Types.toVIF("1075a001-a4c9-234d-782b-c05dcd2d914b");
//		vm.setTag(c, vif, "-1", "ovs0");
//		System.out.println(vm.getNetworkRecord(c, vif));
//		System.out.println(vm.getVIFRecord(c, vif));
//		vm.destroy(c, true);
//		vm.setDiskIORateLimit(c, "write", "10", "MBps");
//		System.out.println(vm.getDiskIORateLimit(c, "write", "MBps"));
//		vm.clearIORateLimit(c, "write", "MBps");
//		System.out.println(vm.getActionsAfterCrash(c));
//		vm.start(c, false, true);
//		System.out.println(vm.getRecord(c));
//		Pool pool = Pool.getAll(c).iterator().next();
//		vm.cleanShutdown(c);
		// VDI vdi = Types.toVDI("e0e89fb2-0a46-0644-38e3-7ad3641eecab");
		// System.out.println(Pool.checkDataDiskInUse(c, vdi));
		// System.out.println(pool.getRecord(c).backup.getRecord(c));
		// for (VDI vdi : pool.getAllActiveDataDisk(c)) {
		// System.out.println(vdi.getRecord(c, true));
		// }
		// System.out.println(vm.getRecord(c).metrics.getRecord(c));
		// vm.setNetworkIORate(c, "rate", vif, value)
		// vm.setDiskIORateLimit(c, type, value)
//		Host host = Types.toHost("6c0ff4ad-8767-741c-c1e9-d3139ac8b0fb");
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("cpuNumber", 1);
//		map.put("memoryValue", 1);
//		map.put("newUuid", UUID.randomUUID().toString());
//		map.put("MAC", "00:16:3e:2c:9f:2c");
//		map.put("IP", "");
//		map.put("type", "Windows");
//		map.put("passwd", "Test1234");
//		map.put("origin_passwd", "onceas");
//		VM.Record vmrecord = VM.createOnFromTemplate(c, host, "9d6118c3-b768-277b-bcf4-d93489dfb09c", "Test1",
//				map, false);
//		System.out.println(vmrecord);
		// String license = host.genLicense(c, "300");
		// System.out.println(host.verifyLicense(c, license));
//		 for (SR sr : SR.getAll(c)) {
//		 // System.out.println(sr.getNameDescription(c));
//		 // System.out.println(sr.getNameLabel(c));
//		 if (sr.getType(c).endsWith("iso"))
//			 sr.update(c);
//			 for (VDI vdi : sr.getRecord(c).VDIs) {
//				 System.out.println(vdi.getRecord(c));
//			 }
////		 System.out.println(sr.getRecord(c).otherConfig.get("location") + "/"
////		 + sr.toWireString());
//		 }
//		 VIF.createBindToPhysicalNetwork(c, vm, "ovs2", "00:16:3e:1b:4b:ac");
		// JSONObject jo = new JSONObject();
		// jo.put("requestType", "Agent.SetPublicKey");
		// jo.put("publicKey",
		// "ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEArSg2QUSMrT8cHfN7MWN8WT0gVdPmjRWCj15d1ORSyL40Veb90AjJPJG2kDG1vcrV08QRJUdRYHhIJuYPAHesGP+JjF9B8XXUOKFPC2uwsTZ2xAr0IZ6FOGSe1KD2ynlzrTw7ZDBM0g+Zrqc+4Kb8wRbDzhFsqms2ajZN2Jeyam7GpUpl50ykl5thHwkngI1YMx0CGIpyDSGm8RMCcqAr6WR2K4KGbf40wsj8Rnfm/uLlr+pbDjno1D3BWqO6BZ/XSBp5+W0HBxOGmH0YVdWZ1jiK2D7PbHfDtkSlGjEFpSSHnVfFHalQ3X5hu2sS2xZ5tFMdapVAN0AM98blkj7MSw==");
		// jo.put("mac", "00:16:3E:2C:65:AA");
		// jo.put("ipAddress", "10.10.0.27");
		// jo.put("netmask", "255.255.0.0");
		// jo.put("gateway", "10.10.0.254");
		// jo.put("userName", "root");
		// jo.put("password", "onceas");
		// System.out.println(vm.sendRequestViaSerial(c, jo.toString(), true));
		// JSONObject jo1 = new JSONObject();
		// jo1.put("requestType", "Performance.FreeSpace");
		// jo1.put("mountPoint", "root");
		// jo1.put("mac", "00:16:3E:2C:65:AA");
		// System.out.println(vm.sendRequestViaSerial(c, jo1.toString(), true));
		// Host host = Host.getByNameLabel(c,
		// "133.133.135.36").iterator().next();
		// System.out.println(Host.getHA(c, host));
		// Host.setHA(c, host, false);
		// VM vm = VM.getByNameLabel(c,
		// "win2003_template_on_16_2").iterator().next();
//		 for (Network networks : Network.getAll(c)){
//		 System.out.println(networks.getRecord(c).nameLabel);
//		 }
		// Host.migrateTemplate(c, vm, "bd3cd976-4c79-b466-155e-0319ae0b30a1",
		// "133.133.135.16");
		// VM vm = Types.toVM("bd3cd976-4c79-b466-155e-0319ae0b30a0");
		// vm.setSerial(c);
//		 VIF.createBindToPhysicalNetwork(c, vm, "ovs2", "00:16:3e:1b:4b:a3");
		// VIF vif = VIF.getByUuid(c, "559a2e9f-5c83-77f9-789f-a436fc6f2ea1");
		// VIF vif = Types.toVIF("9b1608d5-2754-98de-8fb4-0611e2d7ad44");
		// System.out.println(vm.getNetworkRecord(c, vif));
		// vif.set_physical_network(c, vm, "ovs0");
		// System.out.println(vif.getRecord(c));
		// vm.destroyVIF(c, vif);
		// vif.destroy(c);
		// VIF vif = VIF.createBindToPhysicalNetwork(c, vm, "ovs0",
		// "00:16:3e:51:d2:c5");
		// vm.setTag(c, vif, "10");
		// VDI vdi =vm.getSystemVDI(c)
		// for (VBD vbd : vm.getVBDs(c)) {
		// System.out.println(vbd.getRecord(c));
		// // if (vbd.ref.equals("475e8329-7bc1-2223-83ce-7f818677d0ec"))
		// // vbd.destroy(c);
		// }
		// String uuid =
		// UUID.fromString("fbef1ce6-0992-4cf9-baa4-41a3353d1234").toString();
//		 VDI vdi = VDI.createDataDisk(c, uuid, 10);
		// VM.createDataVBD(c, "8fde44aa-fadb-f944-180a-795382e54834",
		// "fbef1ce6-0992-4cf9-baa4-41a3353d1234");
//		 for (VM vm : VM.getAll(c)) {
//		 System.out.println(vm.getRecord(c));
//		 }
		// VM vm = VM.getByUuid(c, "a39f98aa-e6c0-7532-2d1b-10d13e7855d0");
		// for (VIF vif1 : vm.getVIFs(c)){
		// System.out.println(vm.getVIFRecord(c, vif1));
		// // if (vif.ref.equals("81c1553e-0faf-461a-bea4-4c3a36efb665"))
		// // vif.destroy(c);
		// // vif.destroy(c);
		// }
		// System.out.println(vm.cleanReboot(c));
		// SR sr = SR.getByUuid(c, "a41a7317-5f25-4a8a-a63a-ee572e654dab");
		// sr.setNameLabel(c, "Test");
		// System.out.println(sr.getNameLabel(c));
		// System.out.println(sr.getRecord(c));
		// for (SR sr : SR.getAll(c)) {
		// // System.out.println(sr.getNameDescription(c));
		// // System.out.println(sr.getNameLabel(c));
		// System.out.println(sr.getRecord(c));
		// }
		// Pool pool = Pool.getByUuid(c,
		// "0e1e4938-bd94-c704-e44c-39ef7505a2d0");
		// pool.setNameDescription(c, "Test");
		// System.out.println(pool.getRecord(c));
		// String uuid =
		// UUID.fromString("fbef1ce6-0992-4cf9-baa4-41a3353d9321").toString();
		// VDI vdi = VM.cloneSystemVDI(c,
		// "f0810c91-cc3b-a9c9-b9af-bd73917ba4d1", uuid);
		// System.out.println(vdi.getRecord(c));
		// VDI vdi = VDI.getByUuid(c, "fbef1ce6-0992-4cf9-baa4-41a3353d9321");
		// vdi.destroy(c);
		// Host host = Host.getByNameLabel(c,
		// "133.133.135.12").iterator().next();
		// System.out.println();
		// VIF vif = VIF.getByUuid(c, "4ed1dd3c-d69b-8761-229f-5de297e098a9");
		// System.out.println(vif.getRecord(c));
		// for (VM.Record vm_rec : VM.getAllRecords(c).values()) {
		// System.out.println(vm_rec);
		// }
		// for (VM.Record vm_rec : VM.getAllRecords(c).values()){
		// System.out.println(vm_rec);
		// }
		// Host.bindIpMac(c, intra_ip, mac)
		// long time1 = System.currentTimeMillis();
		// for (VM.Record vm_rec : VM.getAllRecords(c).values()) {
		// System.out.println(vm_rec);
		// }
		// long time2 = System.currentTimeMillis();
		// System.out.println((time2 -time1) /1000);
		// System.out.println(Host.getAll(c).iterator().next().getRecord(c));
		// System.out.println(Host.bindIpMac(c, "10.10.11.11",
		// "00:16:3e:43:3e:c3"));
		// System.out.println(Host.unbindIpMac(c, "10.10.11.11",
		// "00:16:3e:43:3e:c3"));
		// long time1 = System.currentTimeMillis();
		// int i = 0;
		// for (VDI vdi : VDI.getAll(c)) {
		// i += 1;
		// System.out.println(vdi.getRecord(c));
		// }
		// long time2 = System.currentTimeMillis();
		// System.out.println(i);
		// System.out.println((time2-time1)/1000);
		// VM vm = VM.getByNameLabel(c,
		// "CentOS63_template_on_16_1").iterator().next();
		// System.out.println(vm.getRecord(c).VBDs.iterator().next().getRecord(c));
		// vm.destroy(c, true);
		// for (VBD vbd: vm.getVBDs(c)) {
		// // if
		// (vbd.getRecord(c).uuid.equals("31e3ee1c-6bf1-dff5-b35c-e711a853c2ca"))
		// // vbd.destroy(c);
		// System.out.println(vbd.getRecord(c));
		// }
		// vm.destroy(c, true);
		// VDI vdi = VDI.getByUuid(c, "fbef1ce6-0992-4cf9-baa4-41a3353d9315");
		// System.out.println(vdi.getRecord(c));
		// String uuid =
		// UUID.fromString("fbef1ce6-0992-4cf9-baa4-41a3353d9315").toString();
		// VDI.createDataDisk(c, uuid, (long) 10);
		// String uuid1 =
		// UUID.fromString("fbef1ce6-0992-4cf9-baa4-41a3353d9314").toString();
		// VDI.createDataDisk(c, uuid1, (long) 10);
		// String uuid2 =
		// UUID.fromString("fbef1ce6-0992-4cf9-baa4-41a3353d9313").toString();
		// VDI.createDataDisk(c, uuid2, (long) 10);
		// System.out.println(VDI.deleteDataDisk(c,
		// "fbef1ce6-0992-4cf9-baa4-41a3353d9313"));
		// System.out.println(VDI.deleteDataDisk(c,
		// "fbef1ce6-0992-4cf9-baa4-41a3353d9314"));
		// System.out.println(VDI.deleteDataDisk(c,
		// "fbef1ce6-0992-4cf9-baa4-41a3353d9315"));
		// VDI vdi = VDI.getByUuid(c, "fbef1ce6-0992-4cf9-baa4-41a3353d9315");
		// System.out.println(vdi.getRecord(c));
		// System.out.println(VM.createDataVBD(c,
		// "9ad98ff0-ca46-bd2c-062a-5b3bf80d78ed",
		// "fbef1ce6-0992-4cf9-baa4-41a3353d9315"));
		// System.out.println(VM.createDataVBD(c,
		// "9ad98ff0-ca46-bd2c-062a-5b3bf80d78ed",
		// "fbef1ce6-0992-4cf9-baa4-41a3353d9314"));
		// System.out.println(VM.createDataVBD(c,
		// "9ad98ff0-ca46-bd2c-062a-5b3bf80d78ed",
		// "fbef1ce6-0992-4cf9-baa4-41a3353d9313"));
		// System.out.println(VM.deleteDataVBD(c,
		// "9ad98ff0-ca46-bd2c-062a-5b3bf80d78ed",
		// "fbef1ce6-0992-4cf9-baa4-41a3353d9315"));
		// System.out.println(VM.deleteDataVBD(c,
		// "9ad98ff0-ca46-bd2c-062a-5b3bf80d78ed",
		// "fbef1ce6-0992-4cf9-baa4-41a3353d9314"));
		// System.out.println(VM.deleteDataVBD(c,
		// "9ad98ff0-ca46-bd2c-062a-5b3bf80d78ed",
		// "fbef1ce6-0992-4cf9-baa4-41a3353d9313"));
		// System.out.println(VM.deleteDataVBD(c,
		// "c925e047-6d9f-4d1f-8541-5ad01ab0d1f6",
		// "44a9532a-f290-4845-8266-52103e56f723"));
		// System.out.println(VM.deleteDataVBD(c,
		// "8d016d42-59db-4eba-8590-f5d51ff92b61",
		// "44a9532a-f290-4845-8266-52103e56f723"));
		// System.out.println(vdi.getRecord(c));
		// System.out.println(Host.firewallAllowPing(c, "10.10.1.31"));
		// System.out.println(Host.firewallDenyPing(c, "10.10.1.18"));
		// System.out.println(Host.firewallAddRule(c, "tcp", "10.10.1.31",
		// "22"));
		// System.out.println(Host.firewallDelRule(c, "tcp", "10.10.1.18",
		// "22"));
		// System.out.println(Host.bindOuterIp(c, "10.10.1.31",
		// "124.16.137.242"));
		// System.out.println(Host.unbindOuterIp(c, "10.10.1.31",
		// "124.16.137.242"));
		// System.out.println(Host.limitAddClass(c, "3", "1M"));
		// System.out.println(Host.limitAddIp(c, "10.10.1.30", "3"));
		// System.out.println(Host.limitDelIp(c, "10.10.1.30"));
		// System.out.println(Host.limitDelClass(c, "3"));
		// VDI vdi = VDI.getByUuid(c, "fbef1ce6-0992-4cf9-baa4-41a3353d9312");
		// vdi.destroy(c);
		// System.out.println(vdi.getRecord(c));
		// SR sr = SR.getByNameLabel(c, "mfs_A_C").iterator().next();
		// sr.setIsDefault(c, true);
		// for (Host.Record hostRec : Host.getAllRecords(c).values()) {
		// System.out.println(hostRec);
		// }
		// Host host = Host.getByNameLabel(c,
		// "133.133.135.11").iterator().next();
		// System.out.println(host.getRecord(c));
		// VM vm = VM.getByNameLabel(c, "CentOS63_2").iterator().next();
		// System.out.println(vm.getRecord(c));
		// for (VDI vdi : VDI.getByVM(c, vm)) {
		// System.out.println(vdi.getRecord(c));
		// }
		// VDI vdi = VDI.getByUuid(c, "1b8ee758-a9d8-d7f2-05b1-15e6aafc1a05");
		// System.out.println(vdi.getRecord(c).VBDs.iterator().next().getVM(c).getRecord(c));
		// System.out.println(vm.getRecord(c).residentOn.ref);
		// VIF vif = vm.getVIFs(c).iterator().next();
		// String ip_map = "10.10.1.20@124.16.137.241";
		// vm.setIpMap(c, vif, ip_map);
		// vm.destroyVIF(c, vif);
		// System.out.println(vm.getRecord(c));
		// for (SR sr: SR.getAll(c)) {
		// System.out.println(sr.getRecord(c));
		// }
		System.exit(1);
		// VDI.Record vdiRec1 = new VDI.Record();
		// vdiRec1.otherConfig = new HashMap<String, String>();
		// vdiRec1.otherConfig.put("virtual_machine", "CentOS63_1");
		// vdiRec1.otherConfig.put("vm_uuid", vm.getUuid(c));
		// vdiRec1.virtualSize = (long) 10;
		// vdiRec1.uuid = UUID.randomUUID().toString();
		// vdiRec1.nameLabel = "Test_add1";
		// vdiRec1.type = Types.toVdiType("metadata");
		// vdiRec1.sharable = true;
		// vdiRec1.SR = SR.getByNameLabel(c, "mfs").iterator().next();
		// // vdiRec1.location =
		// //
		// "file:/var/run/sr_mount/"+vdiRec1.SR.getUuid(c)+"/"+vdiRec1.uuid+"/disk.vhd";
		// vdiRec1.location =
		// "tap:aio:"+vdiRec1.SR.getLocation(c)+"/"+vdiRec1.uuid+"/disk.vhd";
		// // System.out.println(vdiRec.SR.getRecord(c));// VDI vdi1 =
		// // VDI.create(c, vdiRec1);
		// VDI vdi1 = VDI.create(c, vdiRec1);
		// VBD.Record vbdRec = new VBD.Record();
		// vbdRec.VM = vm;
		// vbdRec.VDI = vdi1;
		// vbdRec.bootable = false;
		// vbdRec.device = vm.getAvailableVbdDevice(c);
		// vbdRec.mode = Types.toVbdMode("rw");
		// vbdRec.type = Types.toVbdType("Disk");
		// VBD vbd1 = VBD.create(c, vbdRec);
		// Host host = Host.getAll(c).iterator().next();
		// System.out.println(host.getRecord(c));
		// System.out.println(host.getAvailFibers(c));
		// for (PIF pif : host.getRecord(c).PIFs){
		// System.out.println(pif.getRecord(c));
		// }
		// VM vm = VM.getByNameLabel(c, "centos6.3").iterator().next();
		// vm.createClone(c, "TTT");
		// Map<String, String> ip_map = new HashMap<String, String>();
		// ip_map.put("133.133.131.249", "124.16.137.200");
		// vm.setIpMap(c, ip_map);
		// System.out.println(vm.getIpMap(c));
		// for (VBD vbd : vm.getVBDs(c)) {
		// System.out.println(vm.getVBDRecord(c, vbd));
		// // while (true) {
		// // System.out.println(vm.getVBDRecord(c, vbd));
		// // try {
		// // Thread.sleep(1000);
		// // } catch (InterruptedException e) {
		// // e.printStackTrace();
		// // }
		// // }
		// }
		// for (VIF vbd : vm.getVIFs(c)) {
		// while (true) {
		// System.out.println(vm.getVIFRecord(c, vbd).metrics.getRecord(c));
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		// }
		// System.out.println(vm.getRecord(c));
		// VM vm = VM.getByNameLabel(c, "win2003-64_3").iterator().next();
		// for (VIF vif : vm.getVIFs(c)) {
		// System.out.println(vif.getRecord(c));
		// }
		// System.out.println(vm.getAvailableVbdDevice(c));
		// System.out.println(vm.getRecord(c));
		// vm.addToHVMBootParams(c, "boot", "cd");
		// for (VM vm : VM.getByNameLabel(c, "CentOS_BenchMark2")){
		// System.out.println(vm.getRecord(c));
		// }
		// for (VBD vbd : vm.getFibers(c)) {
		// System.out.println(vbd.getRecord(c));
		// // vm.destroyFiber(c, vbd);
		// }
		// for (VDI vdi : VDI.getAll(c)) {
		// // Long size = new Long(vdi.getRecord(c).virtualSize);
		// System.out.println(vdi.getRecord(c));
		// // vdi.setVirtualSize(c, value);
		// }
		// for (VBD vbd : vm.getVBDs(c)) {
		// System.out.println(vbd.getRecord(c));
		// }
		// long time1 = System.currentTimeMillis();
		// for (SR sr : SR.getAll(c)) {
		// // sr.mount(c);
		// System.out.println(sr.getRecord(c));
		// }
		// // SR.mountAll(c);
		// long time2 = System.currentTimeMillis();
		// System.out.println(time2 - time1);
		// vm.mediaChange(c, "CentOS-5.8-i386-bin-DVD.iso");
		System.exit(0);
		// Host host = Host.getByNameLabel(c,
		// "133.133.135.8").iterator().next();
		// VDI.Record vdiRec1 = new VDI.Record();
		// vdiRec1.otherConfig = new HashMap<String, String>();
		// vdiRec1.otherConfig.put("virtual_machine", "win2003-64_2");
		// vdiRec1.otherConfig.put("vm_uuid", vm.getUuid(c));
		// vdiRec1.virtualSize = (long) 20;
		// vdiRec1.uuid = UUID.randomUUID().toString();
		// vdiRec1.nameLabel = "win2003-64_2";
		// vdiRec1.type = Types.toVdiType("user");
		// vdiRec1.sharable = false;
		// vdiRec1.SR = SR.getByNameLabel(c, "local").iterator().next();
		// // vdiRec1.location =
		// //
		// //
		// "file:/var/run/sr_mount/"+vdiRec1.SR.getUuid(c)+"/"+vdiRec1.uuid+"/disk.vhd";
		// vdiRec1.location = "phy:/dev/loop6";
		// // System.out.println(vdiRec.SR.getRecord(c));
		// // VDI vdi1 = VDI.create(c, vdiRec1);
		// VDI vdi1 = VDI.createOn(c, vdiRec1, host);
		//
		//
		// VBD.Record vbdRec = new VBD.Record();
		// vbdRec.VM = vm;
		// vbdRec.VDI = vdi1;
		// vbdRec.bootable = true;
		// vbdRec.device = vm.getAvailableVbdDevice(c);
		// vbdRec.mode = Types.toVbdMode("rw");
		// vbdRec.type = Types.toVbdType("Fiber");
		// // VBD vbd1 = VBD.create(c, vbdRec);
		// VBD vbd1 = VBD.createOn(c, vbdRec, host);
		// vdi1.getRecord(c).VBDs.add(vbd1);
		// VBD vbd = VBD.getByUuid(c, "5813e384-30be-eebe-7559-9ca98b8369f2");
		// for (VBD vbd : vm.getVBDs(c)) {
		// System.out.println(vbd.getRecord(c));
		// System.out.println(vbd.getVDI(c).getRecord(c));
		// }
		// System.out.println(vm.getRecord(c).suspendSR.getRecord(c));
		// vm.destroy(c, true);
		// for (SR sr : SR.getAll(c)) {
		// System.out.println(sr.getRecord(c));
		// }
		//
		// SR sr1 = SR.getByUuid(c, "16a111c7-2726-4c12-8c1e-6ea27b08e42c");
		// for(VDI vdi:sr1.getVDIs(c))
		// {
		// VDI.Record record = vdi.getRecord(c);
		// System.out.println(record.uuid);
		// System.out.println(record.nameDescription);
		// System.out.println(record.virtualSize);
		// }
		// SR sr2 = SR.getByUuid(c, "3bfd62a7-2dd8-4a29-bc14-9930ea12f471");
		// sr2.update(c);
		// sr1.update(c);
		// Iterator<VDI> vdiIt = VDI.getAll(c).iterator();
		// VDI vdi = null;
		// while (vdiIt.hasNext()) {
		// vdi = vdiIt.next();
		// // System.out.println(vdi.getRecord(c).VBDs);
		// // if (vdi.getRecord(c).VBDs.isEmpty())
		// // if (vdi.getRecord(c).uuid.equals(
		// // "15e86a15-21fe-4adf-fe90-917c21401693"))
		// // vdi.destroy(c);
		// System.out.println(vdi.getRecord(c));
		// }
		// VDI vdi3 = VDI.getByUuid(c, "86f97081-7030-6fbd-b46c-bd751243fb2b");
		// vdi3.destroy(c);
		System.exit(0);
		// for (VIF vif : VIF.getAll(c)) {
		// System.out.println(vif.getRecord(c));
		// vm4.destroySnapshot(c, "");
		// System.out.println(VDI.getByNameLabel(c,
		// "VmTools.iso").toWireString().length());
		// vm4.destroy(c, true);
		// Host host = Host.getAll(c).iterator().next();
		// System.out.println(host.getNameLabel(c));
		// vm4.createClone(c, "Test");
		// System.out.println(VDI.getByVM(c,
		// vm4).iterator().next().getRecord(c));
		// vm4.mediaChange(c, "VmTools.iso");
		// VDI vdi2 = VDI.getByNameLabel(c, "VmTools.iso");
		// System.out.println(vdi2.getRecord(c));
		// System.out.println(vdi2.getRecord(c));
		// vm4.getMedia(c).mediaChange(c, vdi2);
		// VDI vdi2 = VDI.getByUuid(c, "b27f4afd-2d7a-446b-8844-e27e86eb937e");
		// System.out.println(vdi2);
		// for (VDI vdi : VDI.getAll(c)) {
		// // System.out.println(vdi.getUuid(c));
		// // System.out.println(vdi.getVirtualSize(c));
		// // System.out.println(vdi.getPhysicalUtilisation(c));
		// System.out.println(vdi.getRecord(c));
		// }
		// SR sr4 = SR.getByUuid(c, "b0231e1e-644b-b62c-5f42-31116858aa21");
		// sr4.getRecord(c);
		// sr4.update(c);
		// for (SR sr : SR.getAll(c)) {
		// // sr.mount(c);
		// System.out.println(sr.getRecord(c));
		// }
		// {
		// Host host = Host.getByNameLabel(c,
		// "133.133.134.54").iterator().next();
		// VM vm2 = VM.getByNameLabel(c, "Test1").iterator().next();
		// vm2.setNameLabel(c, "Test2");
		// System.out.println(VDI.getByVM(c,
		// vm2).iterator().next().getRecord(c));
		// vm2.startOn(c, host, false, false);
		// VM vm3 = vm2.createClone(c, "Cent_clone");
		// System.out.println(vm3.getRecord(c));
		// VDI vdi2 = VDI.getByVM(c, vm2).iterator().next();
		// System.out.println(vdi2.getRecord(c));
		//
		// System.exit(1);
		// }
		// VDI vdi1 = VDI.getByUuid(c, "20edb837-d229-48b6-b0f5-4e4136fb7df1");
		// vdi1.destroy(c);
		//
		// vdi1.backup(c, "d949a1ca-e767-4ffd-9308-655c5ef20141",
		// "0946df8e-28e6-42c3-8d02-cdd2f5b97bb5",
		// "9fafd00b-4028-4703-9af2-4f4fcdca8332");
		// for (VM vm : VM.getAll(c)) {
		// System.out.println(VDI.getByVM(c,
		// vm).iterator().next().getRecord(c));
		// }
		//
		// for (Network nw : Network.getAll(c))
		// System.out.println(nw.getRecord(c));
		// for (Host host : Host.getAll(c)) {
		// // System.out.println(host.getRecord(c));
		// System.out.println(host.getRecord(c));
		// }
		System.exit(1);

		// for (SR sr : SR.getAll(c)) {
		// SR.Record rec = sr.getRecord(c);
		// System.out.println(rec);
		// System.out.println("VDIS:");
		// for (VDI vdi : rec.VDIs) {
		// System.out.println(vdi.getUuid(c));
		// }
		// // System.out.println("VDIS2: ");
		// // for (VDI vdi : sr.getVDIs(c)) {
		// // System.out.println(vdi.getUuid(c));
		// // }
		// }
		//
		// System.exit(1);
		//
		// for (VM vm : VM.getAll(c)) {
		// System.out.println(vm.getNameLabel(c));
		// System.out.println(vm.getPowerState(c));
		// System.out.println(vm.getIsATemplate(c));
		// // System.out.println(vm.getRecord(c));
		// System.out.println(VDI.getByVM(c, vm).iterator().next().getUuid(c));
		// }
		//
		// System.exit(1);
		//
		// VM vm = VM.getByNameLabel(c, "CentOS_1").iterator().next();
		// VDI vdi = VDI.getByVM(c, vm).iterator().next();
		// System.out.println(vdi.getRecord(c));
		// // vm.cleanShutdown(c);
		// // vm.setVCPUsNumberLive(c, new Long(2));
		// // vm.setVCPUsMax(c, new Long(2));
		// // vm.setVCPUsAtStartup(c, new Long(2));
		// // System.out.println(new Long((long)2 * 1024 * 1024 * 1024));
		// // System.exit(1);
		// // vm.setMemoryStaticMax(c, new Long((long)2 * 1024 * 1024 * 1024));
		// // // vm.setMemoryDynamicMaxLive(c, new Long((long)2 * 1024 * 1024 *
		// // 1024));
		// // vm.setMemoryDynamicMax(c, new Long((long)2 * 1024 * 1024 * 1024));
		// // vm.setMemoryDynamicMin(c, new Long((long)2 * 1024 * 1024 * 1024));
		// // vm.cleanReboot(c);
		// // System.out.println(vm.getRecord(c));
		// // Pool.join(c, "133.133.134.54", "root", "onceas");
		// System.exit(1);
		// // SR sr1 = SR.getByUuid(c, "9d86cf86-66da-43b7-b8db-603cca0c2401");
		// // SR sr2 = SR.getByUuid(c, "3bfd62a7-2dd8-4a29-bc14-9930ea12f471");
		// // sr2.destroy(c);
		// // sr2.update(c);
		// // sr1.update(c);
		// // Iterator<VDI> vdiIt = VDI.getAll(c).iterator();
		// // VDI vdi = null;
		// // while (vdiIt.hasNext()) {
		// // vdi = vdiIt.next();
		// // // System.out.println(vdi.getRecord(c).VBDs);
		// // // if (vdi.getRecord(c).VBDs.isEmpty())
		// // // if (vdi.getRecord(c).uuid.equals(
		// // // "15e86a15-21fe-4adf-fe90-917c21401693"))
		// // // vdi.destroy(c);
		// // System.out.println(vdi.getRecord(c));
		// // }
		// // VDI vdi3 = VDI.getByUuid(c,
		// "77afdedc-aa4a-497b-9f78-0f673c552e62");
		// // vdi3.destroy(c);
		// // System.exit(0);
		// // for (VIF vif : VIF.getAll(c)) {
		// // System.out.println(vif.getRecord(c));
		// // }
		// // for (Network n : Network.getAll(c)) {
		// // System.out.println(n.getRecord(c));
		// // }
		// // Network.Record n_rec = new Network.Record();
		// // n_rec.nameLabel = "xenbr0";
		// // n_rec.MTU = new Long(1500);
		// // n_rec.bridge =
		// // VM.getAll(c);
		// // Host host = Host.getByUuid(c,
		// // "ec6c893a-5dd0-56ec-9446-c896a2ff94db");
		// // Host host = Host.getByNameLabel(c,
		// // "133.133.134.52").iterator().next();
		// // System.out.println(host.getMetrics(c).getRecord(c));
		// // host.setNameDescription(c, "133.133.134.95");
		// // for (Host host : Host.getAll(c)){
		// // // System.out.println(host.getRecord(c));
		// // System.out.println(host.getMetrics(c).getRecord(c));
		// // }
		// // for (HostMetrics m : HostMetrics.getAll(c)){
		// // System.out.println(m.getRecord(c));
		// // }
		// // Host host = Host.getByNameLabel(c,
		// // "133.133.134.54").iterator().next();
		// // System.out.println(host.getRecord(c));
		// // Host host = Host.getAll(c).iterator().next();
		// // System.out.println(host.getRecord(c));
		// // Host host = Host.getAll(c).iterator().next();
		// // System.out.println(host.hello(c));
		// // VM vm1 = VM.getByUuid(c, "b83ca091-8957-dd63-e7da-0676da913c6f");
		// // for (VM vm : VM.getAll(c)) {
		// // System.out.println(VDI.getByVM(c, vm).iterator().next()
		// // .getRecord(c));
		// // }
		// SR sr3 = SR.getByUuid(c, "1928747f-5ab5-4820-9cc1-ca74f4e00b21");
		// sr3.destroy(c);
		// // for (SR sr : SR.getAll(c)) {
		// // System.out.println(sr.getRecord(c));
		// // }
		// // sr.setNameDescription(c, "NFS HA Storage Repository.");
		// // System.out.println(sr.getNameDescription(c));
		// VM vm1 = VM.getByNameLabel(c, "centos").iterator().next();
		// System.out.println(vm1.getRecord(c));
		// // VDI vdi1 = VDI.getByVM(c, vm1).iterator().next();
		// // // vdi1.setVirtualSize(c, new Long(22));
		// // Map<String, String> driverParams = new HashMap<String, String>();
		// // VDI vdi2 = vdi1.snapshot(c, driverParams);
		// // // vm1.createClone(c, "centos_t_5");
		// // System.out.println(vdi1.getRecord(c));
		// // System.out.println(vdi2.getRecord(c));
		// System.exit(1);
		// // System.out.println(vm1.getRecord(c));
		// // vm1.setNameLabel(c, "CentOS_t");
		// // System.out.println(vm1.getRecord(c));
		// // vm1.start(c, false, false);
		// // vm1.cleanShutdown(c);
		// // vm1.hardShutdown(c);
		// // vm1.destroy(c, true);
		// // vm1.cleanReboot(c);
		// // System.exit(1);
		// // while (t.getRecord(c).progress != 100.0){
		// // System.out.println(t.getRecord(c));
		// // }
		// // Task t = vm1.cleanRebootAsync(c);
		// // vm1.cleanShutdown(c);
		// // vm1.setVCPUsMax(c, new Long(2));
		// // vm1.setVCPUsNumberLive(c, new Long(2));
		// // System.out.println(vm1.getVCPUsMax(c));
		// // vm1.setVCPUsAffinity(c, new Long(1), "4");
		// // // vm1.addToVCPUsParamsLive(c, "cpumap0", "1");
		// // System.out.println(vm1.getVCPUsAffinity(c));
		// // System.out.println(vm1.getCPUQoS(c).getRecord(c));
		// // System.out.println(vm1.getVCPUsCPU(c));
		// // System.exit(1);
		// // System.out.println(vm1.getCPUQoS(c).getRecord(c));
		// // System.out.println(vm1.getMetrics(c).getRecord(c));
		// // for (Console s :vm1.getConsoles(c)){
		// // System.out.println(s.getRecord(c));
		// // }
		// // System.out.println(vm1.getUuid(c));
		// // vm1.getCPUQoS(c).setVCPUsAffinity(c, "0",
		// // "0,1,2,3,4,5,12,13,14,15,16,17");
		// // System.out.println(vm1.getCPUQoS(c).getRecord(c));
		// // VIF vif = vm1.getVIFs(c).iterator().next();
		// // vif.getRecord(c).network = Network.getByNameLabel(c,
		// // "eth0").iterator()
		// // .next();
		// // System.out.println(vif.getRecord(c).network.getRecord(c));
		// // System.out.println(vm1.getRecord(c));
		// // VM vm1 = VM.getByUuid(c, "af27e1d6-d0f5-b450-4d33-8ef6128e0939");
		// // String description = "CentOS_template";
		// // vm1.setNameDescription(c, description);
		// // vm1.setNameLabel(c, "CentOS_t");
		// // vm1.destroy(c, true);
		// // vm1.setIsATemplate(c, true);
		// // System.out.println(vm1.getRecord(c).suspendVDI.getRecord(c));
		// // System.out.println(vm1.getIsATemplate(c));
		// // vm1.startOn(c, host, false, false);
		// // Task t = vm1.startAsync(c, false, false);
		// // Task t = vm1.cleanShutdownAsync(c);
		// // System.out.println(t.getRecord(c));
		// // while (t.getRecord(c).progress != 100.0){
		// // System.out.println(t.getRecord(c));
		// // }
		// // VM vm2 = vm1.createClone(c, "CentOS_t_2");
		// // vm2.setIsATemplate(c, false);
		// // vm1.startOn(c, host, false, false);
		// // VDI vdi1 = VDI.getByUuid(c,
		// "643dfe79-c34e-46ef-85eb-ce6a31aacc18");
		// // vdi1.destroy(c);
		// // VDI vdi1 = VDI.getByVM(c, vm1).iterator().next();
		// // System.out.println(vdi1.getRecord(c));
		// // vdi1.setVirtualSize(c, (long) 40);
		// // vdi1.destroy(c, true);
		// // System.out.println(vdi1.getRecord(c));
		//
		// // for (VM vm : VM.getAll(c)) {
		// // System.out.println(vm.ref);
		// // System.out.println(vm.getRecord(c));
		// // }
		// // System.out.println(vm.ref);
		// // System.out.println(vm.getRecord(c));
		// // vm.cleanShutdown(c);
		// // SR sr1 = SR.getByUuid(c, "9d86cf86-66da-43b7-b8db-603cca0c2401");
		// // SR sr2 = SR.getByUuid(c, "3bfd62a7-2dd8-4a29-bc14-9930ea12f471");
		// // sr2.update(c);
		// // sr1.update(c);
		// // System.out.println(sr1.getRecord(c));
		// // sr1.setNameDescription(c, "NFS VHD Storage Repository");
		// // SR sr = SR.getByUuid(c, "5006885a-bbd1-40c2-81b3-21c172025bea");
		// // System.out.println(sr.getPhysicalSize(c));
		// // System.exit(1);
		// // System.out.println(sr.getPhysicalUtilisation(c));
		// Iterator<SR> srIt = SR.getAll(c).iterator();
		// SR sr;
		// while (srIt.hasNext()) {
		// sr = srIt.next();
		// // if
		// // (sr.getRecord(c).otherConfig.get("location").equals("/home/os"))
		// // sr.destroy(c);
		// System.out.println(sr.getRecord(c));
		// }
		// Map<String, String> deviceConfig = new HashMap<String, String>();
		// deviceConfig.put("server", "133.133.134.52");
		// deviceConfig.put("username", "root");
		// deviceConfig.put("pwd", "onceas");
		// deviceConfig.put("location", "/home/share");
		// SR sr = SR.create(c, host, deviceConfig,
		// (long) 50 * 1024 * 1024 * 1024, "nfs_vhd",
		// "NFS VHD Storage Repo", "nfs_vhd", "nfs_vhd", false, null);
		//
		// Map<String, String> deviceConfig1 = new HashMap<String, String>();
		// deviceConfig1.put("server", "133.133.134.55");
		// deviceConfig1.put("username", "root");
		// deviceConfig1.put("pwd", "onceas");
		// deviceConfig1.put("location", "/home/ha");
		// deviceConfig1.put("auto-scan", "true");
		// String sr_uuid1 = UUID.randomUUID().toString();
		// deviceConfig1.put("uuid", sr_uuid1);
		// Map<String, String> smConfig1 = new HashMap<String, String>();
		// //smConfig1.put("iso_type", "nfs_iso");
		// SR.create(c, host, deviceConfig1, (long) 50 * 1024 * 1024 * 1024,
		// "nfs_ha", "NFS HA Storage Repo", "nfs_ha", "sxp", true,
		// smConfig1);

		// VDI.Record vdiRec2 = new VDI.Record();
		// vdiRec2.otherConfig = new HashMap<String, String>();
		// vdiRec2.type = Types.toVdiType("iso");
		// vdiRec2.otherConfig.put("location",
		// "tap:aio:/opt/iso/CentOS-5.4-i386-bin-DVD.iso");
		// vdiRec2.SR = SR.getByNameLabel(c, "iso-sr").iterator().next();
		// // System.out.println(vdiRec.SR.getRecord(c));
		// VDI vdi2 = VDI.create(c, vdiRec2);
		// sr.destroy(c);
		// SR sr = SR.getByNameLabel(c, "nfs_vhd").iterator().next();
		// sr.destroy(c);
		// sr.setNameLabel(c, "Iso");
		// System.out.println(sr.getNameLabel(c));
		// Iterator<Host> hostIt = Host.getAll(c).iterator();
		// Host host = null;
		// while (hostIt.hasNext()) {
		// host = hostIt.next();
		// System.out.println(host.getRecord(c));
		// }
		// VM vm = VM.getByUuid(c, "b4c04c3e-2dd6-d8b3-19a9-aa03910273e3");
		// VM vm = VM.getByNameLabel(c, "centos3").iterator().next();
		// System.out.println(vm.getRecord(c));
		// Iterator<VBD> vbdIt = vm.getVBDs(c).iterator();
		// while (vbdIt.hasNext()) {
		// System.out.println(vbdIt.next().getRecord(c));
		// }
		// VDI vdi1 = VDI.getByVM(c, vm1).iterator().next();
		// vdi1.destroy(c, true);
		// System.out.println(vdi1.getNameLabel(c));
		// Iterator<VDI> vdiIt = VDI.getAll(c).iterator();
		// VDI vdi = null;
		// while (vdiIt.hasNext()) {
		// vdi = vdiIt.next();
		// // System.out.println(vdi.getRecord(c).VBDs);
		// // if (vdi.getRecord(c).VBDs.isEmpty())
		// // if (vdi.getRecord(c).uuid.equals(
		// // "15e86a15-21fe-4adf-fe90-917c21401693"))
		// // vdi.destroy(c);
		// System.out.println(vdi.getRecord(c));
		// }
		// Iterator<Event.Record> eveIt = Event.next(c).iterator();
		// while (eveIt.hasNext()) {
		// System.out.println(eveIt.next());
		// }
		// vm.startAsync(c, false, false);
		// vm.cleanShutdown(c);
		// Iterator<String> s =
		// Session.getAllSubjectIdentifiers(c).iterator();
		// while (s.hasNext()) {
		// System.out.println(s.next());
		// }
		// Session s = Session.getByUuid(c,
		// c.getSessionReference().split(":")[1]);
		// System.out.println(s.getRecord(c));
		// VM template = VM.getByUuid(c,
		// "d63d7803-0741-39e0-a4db-6cf9d89ca4a9");
		// VBD vbd = template.getVBDs(c).iterator().next();
		// VDI vdi = vbd.getVDI(c);
		// Iterator<VDI> vdiIt = VDI.getAll(c).iterator();
		// while (vdiIt.hasNext()) {
		// System.out.println(vdiIt.next().getRecord(c));
		// }
		// VDI vdiNew = vdi.getSnapshots(c).iterator().next();
		// VBD.Record r = vbd.getRecord(c);
		// System.out.println(r);
		// r.VDI = vdiNew;
		// r.VDI = vdi.getSnapshots(c).iterator().next();
		// VBD.create(c, r);
		// System.out.println(vdi.getRecord(c));
		// System.out.println(vbd.getRecord(c));
		// System.out.println(template.getRecord(c));
	}
}
