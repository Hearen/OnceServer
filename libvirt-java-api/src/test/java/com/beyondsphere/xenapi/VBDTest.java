package com.beyondsphere.xenapi;

import java.net.MalformedURLException;
import java.util.Map;
import java.util.Set;

import org.apache.xmlrpc.XmlRpcException;

import com.beyondsphere.xenapi.Connection;
import com.beyondsphere.xenapi.VBD;
import com.beyondsphere.xenapi.VM;
import com.beyondsphere.xenapi.Types.BadServerResponse;
import com.beyondsphere.xenapi.Types.SessionAuthenticationFailed;
import com.beyondsphere.xenapi.Types.XenAPIException;

public class VBDTest {

	/**
	 * @param args
	 * @throws XmlRpcException 
	 * @throws XenAPIException 
	 * @throws MalformedURLException 
	 * @throws SessionAuthenticationFailed 
	 * @throws BadServerResponse 
	 */
	public static void main(String[] args) throws BadServerResponse, SessionAuthenticationFailed, MalformedURLException, XenAPIException, XmlRpcException {
		Connection c = new Connection("http://133.133.135.37:9363", "root",
				"onceas");
		/*************获取VBD对象********/

//?		VM vm = VM.getByNameLabel(c, "CentOS_4.5_32-bit_1").iterator().next();

//		String ref = "a2e7c0af-e218-485b-b5af-e9e4145d003d";
		
		//		for (VBD vbd : vm.getVBDs(c)) {
//			System.out.println(vm.getVBDRecord(c, vbd));
//		}
//		VBD vbd = vm.getVBDs(c).iterator().next();
		
//		VBD vbd = VBD.detach4Test(c, ref, null);

/*		VBD vbd = new VBD(null);
		vbd.detach4Test(c,ref,null);*/
		
//		VBD vbd = VBD.create4Test(c, null, 10, "/home/test/images/test0.img", "test0.img", 0);
		
//		String pool_ref = "";
//		int contain = 10;
//		String file = "/home/test/test11.img";
//		String name = "test11.img";
//		int derive = 0;
//		VBD vbd = VBD.create4Test(c,pool_ref, contain, file, name, derive);
		
		/*************获取VBD对象的record值********/
//		VBD.Record record = vbd.getRecord(c);
//		System.out.println(record);
		/*************通过uuid值获取VBD对象********/
//		VBD getByUuid
//		String uuid = "";
//		VBD vbd = VBD.getByUuid(c, uuid);
		/*************通过record值创建VBD对象？？？？？？？？？？********/
//		VBD.Record record = vbd.getRecord(c);
//		VBD vbd_create = VBD.create(c, record);
		/*************通过record和host值创建VBD对象？？？？？？？？？？********/
//		VBD.Record record = null;
//		Host host = Host.getByUuid(c, "b37b888e-f069-c075-cbe1-4326d08b538d");
//		VBD vbd_create = VBD.createOn(c, record, host);
		/*************销毁当前VBD对象？？？？？？？？？？********/
//		vbd.destroy(c);
		/*************销毁特定主机上的VBD对象？？？？？？？？？？********/
//		Host host = null;
//		vbd.destroyOn(c, host);
		/*************更改VM的cd-rom********/
//		VDI new_vdi = VDI.getByUuid(c, "15b986a4-ff5c-436b-a51c-62277e23959f");
//		vbd.mediaChange(c, new_vdi);
		/*************获取VBD对象的uuid值********/
//		String uuid = vbd.getUuid(c);
//		System.out.println(uuid);
		/*************(不支持)获取VBD对象允许的操作值********/
//		Set<Types.VbdOperations> oper = vbd.getAllowedOperations(c);
//		System.out.println(oper);
		/*************获取VBD对象当前的操作值********/
//		Map<String, Types.VbdOperations> oper = vbd.getCurrentOperations(c);
//		System.out.println(oper);
		/*************获取VBD的VM域********/
//		VM oper = vbd.getVM(c);
//		System.out.println(oper);
		/*************获取VBD对象的VDI域********/
//		VDI oper = vbd.getVDI(c);
//		System.out.println(oper);
		/*************获取VBD对象的Device域********/
//		String device = vbd.getDevice(c);
//		System.out.println(device);
		/*************获取VBD对象的userdevice域********/
//		String oper = vbd.getUserdevice(c);
//		System.out.println(oper);
		/*************(不支持)获取VBD对象的bootable域********/
//		Boolean bootable = vbd.getBootable(c);
//		System.out.println(bootable);
		/*************获取VBD对象的mode域********/ 
//		Types.VbdMode mode = vbd.getMode(c);
//		System.out.println(mode);
		/*************获取VBD对象的type域********/ 
//		Types.VbdType type = vbd.getType(c);
//		System.out.println(type);
		/*************(不支持)获取VBD对象的unpluggable域********/ 
//		Boolean unpluggable = vbd.getUnpluggable(c);
//		System.out.println(unpluggable);
		/*************(不支持)获取VBD对象的unpluggable域********/ 
//		Boolean unpluggable = vbd.getStorageLock(c);
//		System.out.println(unpluggable); 
		/*************(不支持)获取VBD对象的empty域********/ 
//		Boolean empty = vbd.getEmpty(c);
//		System.out.println(empty); 
		/*************(不支持)获取VBD对象的other_config域********/ 
//		Map<String, String> other_config = vbd.getOtherConfig(c);
//		System.out.println(other_config); 
		/*************(不支持)获取VBD对象的currently_attached域********/ 
//		Boolean currently_attached = vbd.getCurrentlyAttached(c);
//		System.out.println(currently_attached); 
		/*************(不支持)获取VBD对象的status_code域********/ 
//		Long status_code = vbd.getStatusCode(c);
//		System.out.println(status_code);
		/*************(不支持)获取VBD对象的status_detail域********/ 
//		String status_detail = vbd.getStatusDetail(c);
//		System.out.println(status_detail);
		/*************获取VBD对象的runtime_properties域********/ 
//		Map<String, String> runtime_properties = vbd.getRuntimeProperties(c);
//		System.out.println(runtime_properties); 
		/*************(不支持)获取VBD对象的algorithm_type域********/ 
//		String algorithm_type = vbd.getQosAlgorithmType(c);
//		System.out.println(algorithm_type);
		/*************(不支持)获取VBD对象的runtime_properties域********/ 
//		Map<String, String> runtime_properties = vbd.getQosAlgorithmParams(c);
//		System.out.println(runtime_properties);
		/*************(不支持)获取VBD对象的supported_algorithms域********/ 
//		Set<String> supported_algorithms = vbd.getQosSupportedAlgorithms(c);
//		System.out.println(supported_algorithms);
		/*************获取VBD对象的metrics域********/ 
//		VBDMetrics metrics = vbd.getMetrics(c);
//		System.out.println(metrics);
		/*************(不支持)设置VBD对象的userdevice域********/ 
//		String userdevice = null;
//		vbd.setUserdevice(c, userdevice);
		/*************设置VBD对象的bootable域********/ 
//		Boolean bootable = false;
//		vbd.setBootable(c, bootable);
		/*************设置VBD对象的mode域********/ 
//		Types.VbdMode mode = null;
//		vbd.setMode(c, mode);
		/*************(不支持)设置VBD对象的type域********/ 
//		Types.VbdType type = null;
//		vbd.setType(c, type);
		/*************(不支持)设置VBD对象的unpluggable域********/ 
//		Boolean unpluggable = false;
//		vbd.setUnpluggable(c, unpluggable);
		/*************(不支持)设置VBD对象的other_config域********/ 
//		Map<String, String> other_config = null;
//		vbd.setOtherConfig(c, other_config);
		/*************(不支持)为指定VBD对象的other_config域增加key,value对********/ 
//		String key = "";
//		String value = "";
//		vbd.addToOtherConfig(c, key, value);
		/*************(不支持)从指定VBD对象的other_config域中删除key,value对********/ 
//		String key = "";
//		vbd.removeFromOtherConfig(c, key);
		/*************(不支持)设置指定VBD对象的algorithm_type域********/ 
//		String algorithmType = "";
//		vbd.setQosAlgorithmType(c, algorithmType);
		/*************(不支持)设置指定VBD对象的algorithm_params域********/ 
//		Map<String, String> algorithmParams = null;
//		vbd.setQosAlgorithmParams(c, algorithmParams);
		/*************(不支持)为指定VBD对象的algorithm_params域增加key,value对********/ 
//		String key = null;
//		String value = null;
//		vbd.addToQosAlgorithmParams(c, key, value);
		/*************(不支持)从指定VBD对象的algorithm_params域中删除key,value对********/ 
//		String key = null;
//		vbd.removeFromQosAlgorithmParams(c, key);
		/*************(不支持)从设备里移除媒体********/ 
//		vbd.eject(c);
		/*************(不支持)将新媒体插入设备********/
//		VDI vdi = null;
//		vbd.insert(c, vdi);
		/*************(不支持)********/
//		vbd.plug(c);
		/*************(不支持)********/
//		vbd.unplug(c);
		/*************(不支持)********/
//		vbd.unplugForce(c);
		/*************(不支持)********/
//		vbd.assertAttachable(c);
		/*************获取当前所有的VBD集合********/
//		Set<VBD> vbd_set = VBD.getAll(c);
//		System.out.println(vbd_set);
		/*************获取当前所有的VBD集合********/
//?		Map<VBD, VBD.Record> map = VBD.getAllRecords(c);
//?		System.out.println(map);
		 
	}

}
