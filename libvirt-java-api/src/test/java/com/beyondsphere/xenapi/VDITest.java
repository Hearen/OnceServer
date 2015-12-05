package com.beyondsphere.xenapi;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.xmlrpc.XmlRpcException;

import com.beyondsphere.xenapi.Connection;
import com.beyondsphere.xenapi.Host;
import com.beyondsphere.xenapi.Pool;
import com.beyondsphere.xenapi.SR;
import com.beyondsphere.xenapi.Session;
import com.beyondsphere.xenapi.Types;
import com.beyondsphere.xenapi.VBD;
import com.beyondsphere.xenapi.VDI;
import com.beyondsphere.xenapi.VM;
import com.beyondsphere.xenapi.Types.BadServerResponse;
import com.beyondsphere.xenapi.Types.XenAPIException;

public class VDITest {

	public static void main(String[] args) throws BadServerResponse, XenAPIException, XmlRpcException, MalformedURLException {
		Connection c = new Connection("http://133.133.135.8:9363", "root",
				"onceas");
		/*************通过uuid值获取VDI对象********/
		String uuid = "15b986a4-ff5c-436b-a51c-62277e23959f";
		VDI vdi = VDI.getByUuid(c, uuid);
		/*************通过namelabel值获取VDI对象********/
//		String namelabel = "test1647.iso";
//		VDI vdi_by_namelabel = VDI.getByNameLabel(c, namelabel);
//		System.out.println(vdi_by_namelabel);
		/*************通过VM获取VDI对象??????出错？？？？？********/
//		String vm_uuid = "b37b888e-f069-c075-cbe1-4326d08b538d";
//		VM vm = VM.getByUuid(c, vm_uuid);
//		Set<VDI> vdi_set = VDI.getByVM(c, vm);
//		System.out.println(vdi_set);
		/*************返回一个record,它包含给定VDI的当前状态********/
//		VDI.Record vdi_record = vdi.getRecord(c);
//		System.out.println(vdi_record);
		/*************通过record创建一个新的VDI实例？？？？？？太慢********/
//		VDI.Record record = vdi.getRecord(c);
//		VDI vdi_create = VDI.create(c, record);
		/*************创建一个新的VDI实例？？？？怎么传参数********/
//		VDI.Record record = null;
//		Host host = null;
//		VDI vdi_createon = VDI.createOn(c, record, host);
		/*************备份一个VDI实例？？？？怎么传参数？？？？？********/
		String dst_vdi = "";
		String src_sr = "";
		String dst_sr = "";
		vdi.backup(c, dst_vdi, src_sr, dst_sr);
		/*************销毁一个特定的VDI实例********/
//		vdi.destroy(c);
		/*************获取当前VDI实例的UUID值********/
//		String vdi_uuid = vdi.getUuid(c);
//		System.out.println(vdi_uuid);
		/*************获取当前VDI实例的name label值********/
//		String vdi_namelabel = vdi.getNameLabel(c);
//		System.out.println(vdi_namelabel);
		/*************获取当前VDI实例的name label值********/
//		String vdi_nameDesc = vdi.getNameDescription(c);
//		System.out.println(vdi_nameDesc);
		/*************(不支持)获取当前允许的操作********/
//		Set<Types.VdiOperations> allowedOperations = vdi.getAllowedOperations(c);
//		System.out.println(allowedOperations);
		/*************(不支持)获取当前的操作********/
//		Map<String, Types.VdiOperations> currentOperations = vdi.getCurrentOperations(c);
//		System.out.println(currentOperations);
		/*************获取SR对象********/
//		SR sr = vdi.getSR(c);
//		System.out.println(sr);
		/*************获取VBD对象********/
//		Set<VBD> vbd_set = vdi.getVBDs(c);
//		System.out.println(vbd_set);
		/*************(不支持)获取给定VDI的crash_dumps域********/
//		Set<Crashdump> crashdump_set = vdi.getCrashDumps(c);
//		System.out.println(crashdump_set);
		/*************获取给定VDI的virtual_size域********/
//		Long virtual_size = vdi.getVirtualSize(c);
//		System.out.println(virtual_size);
		/*************(不支持)获取给定VDI的physical_utilisation域********/
//		Long physical_utilisation = vdi.getPhysicalUtilisation(c);
//		System.out.println(physical_utilisation);
		/*************获取给定VDI的type域********/
//		Types.VdiType type = vdi.getType(c);
//		System.out.println(type);
		/*************获取给定VDI的sharable域********/
//		Boolean sharable = vdi.getSharable(c);
//		System.out.println(sharable);
		/*************获取给定VDI的read_only域********/
//		Boolean read_only = vdi.getReadOnly(c);
//		System.out.println(read_only);
		/*************获取给定VDI的other_config域********/
//		Map<String, String> other_config = vdi.getOtherConfig(c);
//		System.out.println(other_config);
		/*************(不支持)获取给定VDI的storage_lock域********/
//		Boolean storage_lock = vdi.getStorageLock(c);
//		System.out.println(storage_lock);
		/*************获取给定VDI的location域********/
//		String location = vdi.getLocation(c);
//		System.out.println(location);
		/*************(不支持)获取给定VDI的managed域********/
//		Boolean managed = vdi.getManaged(c);
//		System.out.println(managed);
		/*************(不支持)获取给定VDI的missing域********/
//		Boolean missing = vdi.getMissing(c);
//		System.out.println(missing);
		/*************(不支持)获取给定VDI的parent域********/
//		VDI parent = vdi.getParent(c);
//		System.out.println(parent);
		/*************(不支持)获取给定VDI的xenstore_data域********/
//		Map<String, String> xenstore_data = vdi.getXenstoreData(c);
//		System.out.println(xenstore_data);
		/*************(不支持)获取给定VDI的sm_config域********/
//		Map<String, String> sm_config = vdi.getSmConfig(c);
//		System.out.println(sm_config);
		/*************(不支持)获取给定VDI的is_a_snapshot域********/
//		Boolean is_a_snapshot = vdi.getIsASnapshot(c);
//		System.out.println(is_a_snapshot);
		/*************(不支持)获取给定VDI的snapshot_of域********/
//		VDI snapshot_of = vdi.getSnapshotOf(c);
//		System.out.println(snapshot_of);
		/*************(不支持)获取给定VDI的snapshots域********/
//		Set<VDI> snapshots = vdi.getSnapshots(c);
//		System.out.println(snapshots);
		/*************(不支持)获取给定VDI的snapshot_time域********/
//		Date snapshot_time = vdi.getSnapshotTime(c);
//		System.out.println(snapshot_time);
		/*************(不支持)获取给定VDI的tags域********/
//		Set<String> tags = vdi.getTags(c);
//		System.out.println(tags);
		/*************(不支持)获取给定VDI的allow_caching域********/
//		Boolean allow_caching = vdi.getAllowCaching(c);
//		System.out.println(allow_caching);
		/*************(不支持)获取给定VDI的on_boot域********/
//		Types.OnBoot on_boot = vdi.getOnBoot(c);
//		System.out.println(on_boot);
		/*************(不支持)获取给定VDI的metadata_of_pool域********/
//		Pool metadata_of_pool = vdi.getMetadataOfPool(c);
//		System.out.println(metadata_of_pool);
		/*************(不支持)获取给定VDI的metadata_latest域********/
//		Boolean metadata_latest = vdi.getMetadataLatest(c);
//		System.out.println(metadata_latest);
		/************设置给定VDI的other_config域？？出错？？？？？********/
//		Map<String, String> otherConfig = null;
//		vdi.setOtherConfig(c, otherConfig);
		/************为给定VDI的other_config域增加key,value对？怎么用？********/
//		String key = null;
//		String value = null;
//		vdi.addToOtherConfig(c, key, value);
		/************根据key值从给定VDI的other_config域中删除key,value对？？怎么用？********/
//		String key = null;
//		vdi.removeFromOtherConfig(c, key);
		/************设置给定VDI的xenstore_data域********/
		Map<String, String> xenstoreData = null;
		vdi.setXenstoreData(c, xenstoreData);
		/************为给定VDI的xenstore_data域增加key,value对********/
//		String key = null;
//		String value = null;
//		vdi.addToXenstoreData(c, key, value);
		/************根据key值从给定VDI的xenstore_data域中删除key,value对？？？？********/
//		String key = null;
//		vdi.removeFromXenstoreData(c, key);
		/************设置给定VDI的sm_config域********/
//		Map<String, String> smConfig = null;
//		vdi.setSmConfig(c, smConfig);  
		/************为给定VDI的sm_config域增加key,value对********/
//		String key = null;
//		String value = null;
//		vdi.addToSmConfig(c, key, value);
		/************根据key值从给定VDI的sm_config域中删除key,value对？？？？********/
//		String key = null;
//		vdi.removeFromSmConfig(c, key);
		/************设置给定VDI的tags域********/
//		Set<String> tags = null;
//		vdi.setTags(c, tags); 
		/************为给定VDI的tags域增加一个新的值value********/
//		String value = null;
//		vdi.addTags(c, value); 
		/************从给定VDI的tags域中删除值value********/
//		String value = null;
//		vdi.removeTags(c, value); 
		/************获取VDI的一个只读快照，并返回其指针********/
//		Map<String, String> driverParams = null;
//		VDI s = vdi.snapshot(c, driverParams);
		/************创建当前VDI的一个副本，并返回其在新磁盘上的指针********/
//		Map<String, String> driverParams = null;
//		VDI s = vdi.createClone(c, driverParams);
		/************重新设置VDI的大小********/
//		Long size = null;
//		vdi.resize(c, size);
		/************重新设置VDI的大小，这些VDI可以也可以不连接到运行中的客户********/
//		Long size = null;
//		vdi.resizeOnline(c, size);
		/************只在数据库中创建一个新的VDI记录********/
//		VDI in = VDI.introduce(c, uuid, nameLabel, nameDescription, SR, type, sharable, readOnly, otherConfig, location, xenstoreData, smConfig)
		/************只在数据库中创建一个新的VDI记录********/
//		VDI dbin = VDI.dbIntroduce(c, uuid, nameLabel, nameDescription, SR, type, sharable, readOnly, otherConfig, location, xenstoreData, smConfig)
		/************从数据库中删除一个VDI记录********/
//		vdi.dbForget(c);
		/************请求存储后端刷新VDI对象的域********/
//		vdi.update(c);
		/************在指定SR中制作一个VDI，然后把给定的VDI数据拷贝到新的磁盘********/
//		SR sr = null;
//		VDI copy = vdi.copy(c, sr);
		/************设置VDI的managed域********/
//		Boolean value = false;
//		vdi.setManaged(c, value);
		/************从数据库中删除VDI记录********/
//		vdi.forget(c);
		/************设置VDI的sharable域********/
//		Boolean value = false;
//		vdi.setSharable(c, value);
		/************设置VDI的read_only域********/
//		Boolean value = false;
//		vdi.setReadOnly(c, value);
		/************设置VDI的missing域********/
//		Boolean value = false;
//		vdi.setMissing(c, value);
		/************设置VDI的virtual_size域********/
//		Long value = null;
//		vdi.setVirtualSize(c, value);
		/************设置VDI的physical_utilisation域********/
//		Long value = null;
//		vdi.setPhysicalUtilisation(c, value);
		/************设置VDI是不是一个快照********/
//		Boolean value = null;
//		vdi.setIsASnapshot(c, value);
		/************设置？？？？？？？？？？？？？是不是一个快照********/
//		VDI value = null;
//		vdi.setSnapshotOf(c, value);
		/************设置该VDI的snapshot time********/
//		Date value = null;
//		vdi.setSnapshotTime(c, value);
		/************设置该VDI的snapshot time********/
//		Pool value = null;
//		vdi.setMetadataOfPool(c, value);
		/************设置该VDI的namelabel********/
//		String value = null;
//		vdi.setNameLabel(c, value);
		/************设置该VDI的namedescription********/
//		String value = null;
//		vdi.setNameDescription(c, value);
		/************设置该VDI的on_boot参数的值，只有当该VDI没有和运行的虚拟机连接的时候，值才能改变********/
//		Types.OnBoot value = null;
//		vdi.setOnBoot(c, value);
		/************设置该VDI的allow_caching参数的值，只有当该VDI没有和运行的虚拟机连接的时候，值才能改变********/
//		Boolean value = false;
//		vdi.setAllowCaching(c, value);
		/************加载在提供的VDI中提供的元数据********/
//		Session session = vdi.openDatabase(c);
		/************(不支持)加载在提供的VDI中提供的元数据********/
//		String databasePoolUuid = vdi.readDatabasePoolUuid(c);
//		System.out.println(databasePoolUuid);
		/************获取系统已知的所有VDI列表********/
//		Set<VDI> vdi_set = VDI.getAll(c);
//		System.out.println(vdi_set);
		/************获取系统已知的所有VDI的VDI record的VDI reference的map********/
//		Map<VDI, VDI.Record> map = VDI.getAllRecords(c);
//		System.out.println(map);
	}

}
