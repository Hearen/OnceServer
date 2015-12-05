package com.beyondsphere.xenapi;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.xmlrpc.XmlRpcException;

import com.beyondsphere.xenapi.Connection;
import com.beyondsphere.xenapi.Host;
import com.beyondsphere.xenapi.SR;
import com.beyondsphere.xenapi.Types;
import com.beyondsphere.xenapi.VDI;
import com.beyondsphere.xenapi.Types.BadServerResponse;
import com.beyondsphere.xenapi.Types.SessionAuthenticationFailed;
import com.beyondsphere.xenapi.Types.XenAPIException;

public class SRTest {

	public static void main(String[] args) throws BadServerResponse, SessionAuthenticationFailed, MalformedURLException, XenAPIException, XmlRpcException {
		Connection c = new Connection("http://133.133.135.8:9363", "root",
				"onceas");
		/*************通过UUID值获取SR对象********/
		SR sr = SR.getByUuid(c, "9f0de800-629a-428e-bd10-b9e3e039921a");
		/*************获取SR中的内嵌Record类********/
//		SR.Record record = sr.getRecord(c);
//		System.out.println(record);
		/*************通过namelabel值获取SR对象********/
//		Set<SR> sr_set = SR.getByNameLabel(c, "ha");
//		System.out.println(sr_set);
		/*************通过type值获取SR对象        type类型有哪些?????********/
//		Set<SR> sr_set = SR.getByType(c, "nfs_ha");
//		System.out.println(sr_set);
		/*************获取SR的uuid值********/
//		String uuid = sr.getUuid(c);
//		System.out.println(uuid);
		/*************获取SR的namelabel值********/
//		String namelabel = sr.getNameLabel(c);
//		System.out.println(namelabel);
		/*************获取SR的NsameDescription值********/
//		String nameDescription = sr.getNameDescription(c);
//		System.out.println(nameDescription);
		/*************(不支持)获取SR中允许的操作********/
//		Set<Types.StorageOperations> allowedOperations = sr.getAllowedOperations(c);
//		System.out.println(allowedOperations);
		/*************(不支持)获取SR中当前的操作********/
//		Map<String, Types.StorageOperations> currentOperations = sr.getCurrentOperations(c);
//		System.out.println(currentOperations);
		/*************获取指定SR的VDI域********/
//		Set<VDI> vdi_set = sr.getVDIs(c);
//		System.out.println(vdi_set);
		/*************获取指定SR的PBD域********/
//		Set<PBD> pbd_set = sr.getPBDs(c);
//		System.out.println(pbd_set);
		/*************(出错)获取指定SR的virtual_allocation域********/
//		Long  virtualAllocation = sr.getVirtualAllocation(c);
//		System.out.println(virtualAllocation);
		/*************获取指定SR的physical_utilisation域********/
//		Long physicalUtilisation = sr.getPhysicalUtilisation(c);
//		System.out.println(physicalUtilisation);
		/*************获取指定SR的physical_size域********/
//		Long physical_size = sr.getPhysicalSize(c);
//		System.out.println(physical_size);
		/*************获取指定SR的type域********/
//		String type = sr.getType(c);
//		System.out.println(type);
		/*************获取指定SR的content_type域********/
//		String content_type = sr.getContentType(c);
//		System.out.println(content_type);
		/*************(不支持)获取指定SR的other_config域********/
//		Map<String, String> other_config = sr.getOtherConfig(c);
//		System.out.println(other_config);
		/*************(不支持)获取指定SR的tags域********/
//		Set<String> other_config = sr.getTags(c);
//		System.out.println(other_config);
		/*************(不支持)获取指定SR的sm_config域********/
//		Map<String, String> sm_config = sr.getSmConfig(c);
//		System.out.println(sm_config);
		/*************(不支持)获取指定SR的blobs域********/
//		Map<String, Blob> blobs = sr.getBlobs(c);
//		System.out.println(blobs);
		/*************(不支持)获取指定SR的local_cache_enabled域********/
//		Boolean blobs = sr.getLocalCacheEnabled(c);
//		System.out.println(blobs);
		/*************(不支持)获取指定SR的introduced_by域********/
//		DRTask DRTask = sr.getIntroducedBy(c);
//		System.out.println(DRTask);
		/*************(不支持)设置指定SR的other_config域********/
//		Map<String, String> otherConfig = sr.getOtherConfig(c);
//		sr.setOtherConfig(c, otherConfig);
		/*************(不支持)为指定SR的other_config域增加key,value值*******/
//		String key = "";
//		String value = "";
//		sr.addToOtherConfig(c, key, value);
		/*************(不支持)根据key值从指定SR的other_config域中删除value值********/
//		String key = "";
//		sr.removeFromOtherConfig(c, key);
		/*************(不支持)设置指定SR的tags域********/
//		Set<String> tags = null;
//		sr.setTags(c, tags);
		/*************(不支持)从指定SR中增加tags域********/
//		String value = "";
//		sr.addTags(c, value);//如果值已经在集合里，就什么都不做
		/*************(不支持)从指定SR中删除tags域********/
//		String value = "";
//		sr.removeTags(c, value);//如果值不在集合里，就什么都不做
		/*************为指定SR设置sm_config域********/
//		Map<String, String> sm_config = null;
//		sr.setSmConfig(c, sm_config);
		/*************为指定SR的sm_config域增加给定的key,value对********/
//		String key = null;
//		String value = null;
//		sr.addToSmConfig(c, key, value);
		/*************从指定SR的sm_config域中删除给定的key,value对********/
//		String key = null;
//		sr.removeFromSmConfig(c, key);
		/*************创建一个新的存储仓库，把它引入到托管系统，创建SR record和PBD record，然后把它依附到到当前物理机********/
//		Map<String, String> deviceConfig = new HashMap<String, String>();
//		deviceConfig.put("server", "133.133.135.8");
//		deviceConfig.put("username", "root");
//		deviceConfig.put("password", "onceas");
//		deviceConfig.put("location", "/home/iso");
//		deviceConfig.put("auto-scan", "true");
//		String sr_uuid2 = UUID.randomUUID().toString();
//		deviceConfig.put("uuid", sr_uuid2);
//		Map<String, String> smConfig2 = new HashMap<String, String>();
//		smConfig2.put("iso_type", "nfs_iso");
//		Host host = Host.getByUuid(c, "b37b888e-f069-c075-cbe1-4326d08b538d");
//		SR.create(c, host, deviceConfig, (long) 50 * 1024 * 1024 * 1024,
//				"nfs_iso", "NFS ISO Storage Repo", "nfs_iso", "iso", true,
//				smConfig2);
		/*************引入一个新的存储仓库到托管系统??????????********/
//		SR sr_introduce = SR.introduce(c, uuid, nameLabel, nameDescription, type, contentType, shared, smConfig);
		/*************删除特定的SR，从数据库中删除SR-record，从磁盘中删除SR。为了使这个操作生效，需要从当前物理机的特定SR的PBD上读取合适的device_config值********/
//		sr.destroy(c);
		/*************(不支持)只从数据库中删除SR-record，不从磁盘中删除SR********/
//		sr.forget(c);
		/*************刷新SR对象的域值********/
//		sr.update(c);
		/*************(不支持)获取系统支持的SR类型集合********/
//		Set<String> type_set = SR.getSupportedTypes(c);
//		System.out.println(type_set);
		/*************(不支持)刷新和一个SR关联的VDI列表********/
//		sr.scan(c);
		/*************挂载与卸载SR********/
//		sr.mount(c);
//		SR.mountAll(c);
//		sr.umount(c);
		/*************(不支持)执行一个特定于后端的遍历********/
//		Host host = Host.getByUuid(c, "b37b888e-f069-c075-cbe1-4326d08b538d");
//		Map<String, String> deviceConfig = new HashMap<String, String>();
//		deviceConfig.put("server", "133.133.135.8");
//		deviceConfig.put("username", "root");
//		deviceConfig.put("password", "onceas");
//		deviceConfig.put("location", "/home/iso");
//		deviceConfig.put("auto-scan", "true");
//		Map<String, String> smConfig = new HashMap<String, String>();
//		smConfig.put("iso_type", "nfs_iso");
//		String probe = SR.probe(c, host, deviceConfig, "nfs_iso", smConfig);
//		System.out.println(probe);
		/*************(不支持)设置SR的shared标签********/
//		Boolean value = false;
//		sr.setShared(c, value);
		/*************设置SR的name label???没有起作用???********/
//		String value = "new";
//		sr.setNameLabel(c, value);
		/*************设置SR的name Description???没有起作用???********/
//		String value = "new";
//		sr.setNameDescription(c, value);
		/*************(不支持)为指定SR创建一个NewBlob********/
//		String name = "";
//		String mimeType = "";
//		sr.createNewBlob(c, name, mimeType);
		/*************(不支持)设置SR的PhysicalSize********/
//		Long value = null;
//		sr.setPhysicalSize(c, value);
		/*************(不支持)设置SR的VirtualAllocation********/
//		Long value = null;
//		sr.setVirtualAllocation(c, value);
		/*************(不支持)设置SR的PhysicalUtilisation********/
//		Long value = null;
//		sr.setPhysicalUtilisation(c, value);
		/*************(不支持)判断给定的SR是不是可以host一个高可用状态文件，可以则成功返回，不可以则解释原因？？？？？？********/
//		sr.assertCanHostHaStatefile(c);
		/*************(不支持)判断给定的SR是不是支持数据库复制，可以则成功返回，不可以解释原因********/
//		sr.assertSupportsDatabaseReplication(c);
		/*************(不支持)启用数据库复制功能********/
//		sr.enableDatabaseReplication(c);
		/*************(不支持)禁用数据库复制功能********/
//		sr.disableDatabaseReplication(c);
		/*************返回系统已知的所有SR列表********/
//		Set<SR> sr_set = SR.getAll(c);
//		System.out.println(sr_set);
		/*************返回一个系统已知的所有SR的SR record的SR参照的Map********/
//		Map<SR, SR.Record> sr_map = SR.getAllRecords(c);
//		System.out.println(sr_map);
	}

}
