package com.beyondsphere.xenapi;

import java.net.MalformedURLException;
import java.util.Map;
import java.util.Set;

import org.apache.xmlrpc.XmlRpcException;

import com.beyondsphere.xenapi.Connection;
import com.beyondsphere.xenapi.Pool;
import com.beyondsphere.xenapi.Types.BadServerResponse;
import com.beyondsphere.xenapi.Types.SessionAuthenticationFailed;
import com.beyondsphere.xenapi.Types.XenAPIException;

public class PoolTest {

	/**
	 * @param args
	 * @throws XmlRpcException 
	 * @throws XenAPIException 
	 * @throws MalformedURLException 
	 * @throws SessionAuthenticationFailed 
	 * @throws BadServerResponse 
	 */
	public static void main(String[] args) throws BadServerResponse, SessionAuthenticationFailed, MalformedURLException, XenAPIException, XmlRpcException {
		Connection c = new Connection("http://133.133.135.8:9363", "root",
				"onceas");
		/*************通过uuid值获取Pool对象********/
		String uuid = "f8ffa75f-f62a-e225-db0e-fd584da06cbe";
		Pool pool = Pool.getByUuid(c, uuid);
		/*************获取Pool对象的record********/
//		Pool.Record record = pool.getRecord(c);
//		System.out.println(record);
		/*************获取Pool对象的status(出错)********/
		Pool.Status status = Pool.getStatus(c);
		System.out.println(status);
		/*************获取Pool对象的uuid********/
//		String uuid_temp = pool.getUuid(c);
//		System.out.println(uuid_temp);
		/*************获取Pool对象的NameLabel********/
//		String nameLabel = pool.getNameLabel(c);
//		System.out.println(nameLabel);
		/*************获取Pool对象的nameDescription********/
//		String nameDescription = pool.getNameDescription(c);
//		System.out.println(nameDescription);
		/*************获取Pool对象的master域********/
//		Host master = pool.getMaster(c);
//		System.out.println(master); 
		/*************(不支持)获取Pool对象的default_SR域********/
//		SR sr = pool.getDefaultSR(c);
//		System.out.println(sr); 
		/*************(不支持)获取Pool对象的suspend_image_SR域********/
//		SR sr = pool.getSuspendImageSR(c);
//		System.out.println(sr); 
		/*************(不支持)获取Pool对象的crash_dump_SR域********/
//		SR sr = pool.getCrashDumpSR(c);
//		System.out.println(sr);
		/*************(不支持)获取Pool对象的other_config域********/
//		Map<String, String> other_config = pool.getOtherConfig(c);
//		System.out.println(other_config);
		/*************(不支持)获取Pool对象的ha_enabled域********/
//		Boolean ha_enabled = pool.getHaEnabled(c);
//		System.out.println(ha_enabled);
		/*************(不支持)获取Pool对象的ha_configuration域********/
//		Map<String, String> ha_configuration = pool.getHaConfiguration(c);
//		System.out.println(ha_configuration); 
		/*************(不支持)获取Pool对象的ha_statefiles域********/
//		Set<String> ha_statefiles = pool.getHaStatefiles(c);
//		System.out.println(ha_statefiles);  
		/*************(不支持)获取Pool对象的ha_host_failures_to_tolerate域********/
//		Long ha_host_failures_to_tolerate = pool.getHaHostFailuresToTolerate(c);
//		System.out.println(ha_host_failures_to_tolerate); 
		/*************(不支持)获取Pool对象的ha_plan_exists_for域********/
//		Long ha_plan_exists_for = pool.getHaPlanExistsFor(c);
//		System.out.println(ha_plan_exists_for);  
		/*************(不支持)获取Pool对象的ha_allow_overcommit域********/
//		Boolean ha_allow_overcommit = pool.getHaAllowOvercommit(c);
//		System.out.println(ha_allow_overcommit);
		/*************(不支持)获取Pool对象的ha_overcommitted域********/
//		Boolean ha_overcommitted = pool.getHaOvercommitted(c);
//		System.out.println(ha_overcommitted); 
		/*************(不支持)获取Pool对象的blobs域********/
//		Map<String, Blob> blobs = pool.getBlobs(c);
//		System.out.println(blobs);
		/*************(不支持)获取Pool对象的tags域********/
//		Set<String> tags = pool.getTags(c);
//		System.out.println(tags);
		/*************(不支持)获取Pool对象的gui_config域********/
//		Map<String, String> gui_config = pool.getGuiConfig(c);
//		System.out.println(gui_config); 
		/*************(不支持)获取Pool对象的gui_config域********/
//		String gui_config = pool.getWlbUrl(c);
//		System.out.println(gui_config);
		/*************(不支持)获取Pool对象的wlb_username域********/
//		String wlb_username = pool.getWlbUsername(c);
//		System.out.println(wlb_username); 
		/*************(不支持)获取Pool对象的wlb_enabled域********/
//		Boolean wlb_enabled = pool.getWlbEnabled(c);
//		System.out.println(wlb_enabled); 
		/*************(不支持)获取Pool对象的wlb_verify_cert域********/
//		Boolean wlb_verify_cert = pool.getWlbVerifyCert(c);
//		System.out.println(wlb_verify_cert); 
		/*************(不支持)获取Pool对象的redo_log_enabled域********/
//		Boolean redo_log_enabled = pool.getRedoLogEnabled(c);
//		System.out.println(redo_log_enabled); 
		/*************(不支持)获取Pool对象的redo_log_vdi域********/
//		VDI redo_log_vdi = pool.getRedoLogVdi(c);
//		System.out.println(redo_log_vdi);
		/*************(不支持)获取Pool对象的vswitch_controller域********/
//		String vswitch_controller = pool.getVswitchController(c);
//		System.out.println(vswitch_controller);
		/*************(不支持)获取Pool对象的restrictions域********/
//		Map<String, String> restrictions = pool.getRestrictions(c);
//		System.out.println(restrictions);
		/*************(不支持)获取Pool对象的metadata_VDIs域********/
//		Set<VDI> metadata_VDIs = pool.getMetadataVDIs(c);
//		System.out.println(metadata_VDIs);
		/*************设置给定Pool对象的name_label域********/
//		String namelabel = "8_1";
//		pool.setNameLabel(c, namelabel);
		/*************(不支持)设置给定Pool对象的name_description域********/
//		String name_description = "8_1";
//		pool.setNameDescription(c, name_description);
		/*************(不支持)设置给定Pool对象的default_SR域********/
//		SR defaultSR = SR.getByUuid(c, "9f0de800-629a-428e-bd10-b9e3e039921a");
//		pool.setDefaultSR(c, defaultSR);
		/*************(不支持)设置给定Pool对象的suspend_image_SR域********/
//		SR suspend_image_SR = SR.getByUuid(c, "9f0de800-629a-428e-bd10-b9e3e039921a");
//		pool.setSuspendImageSR(c, suspend_image_SR);
		/*************(不支持)设置给定Pool对象的crash_dump_SR域********/
//		SR crash_dump_SR = SR.getByUuid(c, "9f0de800-629a-428e-bd10-b9e3e039921a");
//		pool.setCrashDumpSR(c, crash_dump_SR);
		/*************(不支持)设置给定Pool对象的other_config域********/
//		Map<String, String> other_config = null;
//		pool.setOtherConfig(c, other_config);
		/*************(不支持)为给定Pool对象的other_config域增加key,value对********/
//		String key = "test"; 
//		String value = "test_value";
//		pool.addToOtherConfig(c, key, value);
		/*************(不支持)根据key值从给定Pool对象的other_config域中删除key,value对********/
//		String key = "test"; 
//		pool.removeFromOtherConfig(c, key);
		/*************(不支持)设置给定Pool对象的ha_allow_overcommit域********/
//		Boolean haAllowOvercommit = false;
//		pool.setHaAllowOvercommit(c, haAllowOvercommit);
		/*************(不支持)设置给定Pool对象的tags域********/
//		Set<String> tags = pool.getTags(c);
//		pool.setTags(c, tags);
		/*************(不支持)为给定Pool对象的tags域增加值value********/
//		String value = "test";
//		pool.addTags(c, value);
		/*************(不支持)从给定Pool对象的tags域删除值value********/
//		String value = "test";
//		pool.removeTags(c, value);
		/*************(不支持)设置给定Pool对象的gui_config域********/
//		Map<String, String> guiConfig = null;
//		pool.setGuiConfig(c, guiConfig);
		/*************(不支持)为给定Pool对象的gui_config域增加key,value对********/
//		String key = "test"; 
//		String value = "test_value";
//		pool.addToGuiConfig(c, key, value);
		/*************(不支持)根据key值从给定Pool对象的gui_config域中删除key,value对********/
//		String key = "test"; 
//		pool.removeFromGuiConfig(c, key);
		/*************(不支持)设置给定Pool对象的wlb_enabled域********/
//		Boolean wlb_enabled = false;
//		pool.setWlbEnabled(c, wlb_enabled);
		/*************(不支持)设置给定Pool对象的wlb_verify_cert域********/
//		Boolean wlb_verify_cert = false;
//		pool.setWlbVerifyCert(c, wlb_verify_cert);
		/*************(出错)将一个主机加入新的池********/
//		String masterAddress = "CentOS_4.5_32-bit_1";
//		String masterUsername = "root";
//		String masterPassword = "onceas";
//		Pool.join(c, masterAddress, masterUsername, masterPassword);
		/*************(出错)将一个主机加入新的池********/
//		String masterAddress = "CentOS_4.5_32-bit_1";
//		String masterUsername = "root";
//		String masterPassword = "onceas";
//		Pool.joinForce(c, masterAddress, masterUsername, masterPassword);
		/*************将一个主机从池中删除********/
//		String host_uuid = "b37b888e-f069-c075-cbe1-4326d08b538d";
//		Host host = Host.getByUuid(c, host_uuid);
//		Pool.eject(c, host);
		/*************(不支持)将一个当前是slave的节点变成master********/
//		Pool.emergencyTransitionToMaster(c);
		/*************(不支持)引导一个池中的slave节点，该池的主节点已经更换********/
//		String masterAddress = "CentOS_4.5_32-bit_1";
//		Pool.emergencyResetMaster(c, masterAddress);
		/*************(不支持)？？？？？？？？？********/
//		Pool.recoverSlaves(c);
		/*************(弃用)创建PIF,把一个网络映射到一个主机上的同一个物理接口********/
//		String device = "";
//		Network network = "";
//		Long VLAN = "";
//		Set<PIF> pif_set = Pool.createVLAN(c, device, network, VLAN);
		/*************(不支持)Create a pool-wide VLAN by taking the PIF.********/
//		PIF pif = null;
//		Network network = null;
//		Long VLAN = null;
//		Set<PIF> pif_set = Pool.createVLANFromPIF(c, pif, network, VLAN);
//		System.out.println(pif_set);
		/*************(不支持)启用高可用模式********/
//		Set<SR> heartbeatSrs = null; 
//		Map<String, String> configuration = null;
//		Pool.enableHa(c, heartbeatSrs, configuration);
		/*************(不支持)禁用高可用模式********/
//		Pool.disableHa(c);
		/*************(不支持)立即强制同步数据库********/
//		Pool.syncDatabase(c);
		/*************(不支持)执行一个参考主机的主节点角色的顺序移交********/
//		String host_uuid = "b37b888e-f069-c075-cbe1-4326d08b538d";
//		Host host = Host.getByUuid(c, host_uuid);
//		Pool.designateNewMaster(c, host);
		/*************(不支持)？？？？？？？？********/
//		Long seconds = null;
//		Pool.haPreventRestartsFor(c, seconds);
		/*************(不支持)？？？？？？？？********/
//		Long seconds = null;
//		Boolean x = Pool.haFailoverPlanExists(c, seconds);
//		System.out.println(x); 
		/*************(不支持)？？？？？？？？********/
//		Pool.haComputeMaxHostFailuresToTolerate(c);
		/*************(不支持)？？？？？？？？********/
//		Map<VM, String> configuration = null;
//		Pool.haComputeHypotheticalMaxHostFailuresToTolerate(c, configuration);
		/*************(不支持)？？？？？？？？********/
//		Set<Host> failedHosts = null;
//		Set<VM> failedVms = null;
//		Map<VM, Map<String, String>> map = Pool.haComputeVmFailoverPlan(c, failedHosts, failedVms);
		/*************(不支持)？？？？？？？？********/
//		Long value = null;
//		pool.setHaHostFailuresToTolerate(c, value);
		/*************(不支持)？？？？？？？？********/
//		String name = "";
//		String mimeType = "";
//		Blob blob = pool.createNewBlob(c, name, mimeType);
		/*************(不支持)？？？？？？？？********/
//		Map<String, String> config = null; 
//		String serviceName = "";
//		String authType = "";
//		pool.enableExternalAuth(c, config, serviceName, authType);
		/*************(不支持)？？？？？？？？********/
//		Map<String, String> config = null; 
//		pool.disableExternalAuth(c, config);
		/*************(不支持)？？？？？？？？********/
//		pool.detectNonhomogeneousExternalAuth(c);
		/*************(不支持)？？？？？？？？********/
//		String wlbUrl = null;
//		String wlbUsername = null;
//		String wlbPassword = null; 
//		String xenserverUsername = null; 
//		String xenserverPassword = null;
//		Pool.initializeWlb(c, wlbUrl, wlbUsername, wlbPassword, xenserverUsername, xenserverPassword);
		/*************(不支持)？？？？？？？？********/
//		Pool.deconfigureWlb(c);
		/*************(不支持)？？？？？？？？********/
//		Map<String, String> config = null;
//		Pool.sendWlbConfiguration(c, config);
		/*************(不支持)？？？？？？？？********/
//		Map<String, String> map = Pool.retrieveWlbConfiguration(c);
		/*************(不支持)？？？？？？？？********/
//		Map<VM, Set<String>> map = Pool.retrieveWlbRecommendations(c);
		/*************(不支持)？？？？？？？？********/
//		String host = null; 
//		Long port = null;
//		String body = null;
//		String x = Pool.sendTestPost(c, host, port, body);
//		System.out.println(x);
		/*************(不支持)？？？？？？？？********/
//		String name = null;
//		String cert = null;
//		Pool.certificateInstall(c, name, cert);
		/*************(不支持)？？？？？？？？********/
//		String name = null;
//		Pool.certificateUninstall(c, name);
		/*************(不支持)？？？？？？？？********/
//		Set<String> name = Pool.certificateList(c);
		/*************(不支持)？？？？？？？？********/
//		String name = "";
//		String cert = "";
//		Pool.crlInstall(c, name, cert);
		/*************(不支持)？？？？？？？？********/
//		String name = "";
//		Pool.crlUninstall(c, name);
		/*************(不支持)？？？？？？？？********/
//		Set<String> CRL_set = Pool.crlList(c);
		/*************(不支持)？？？？？？？？********/
//		SR sr = null;
//		Pool.enableRedoLog(c, sr);
		/*************(不支持)？？？？？？？？********/
//		Pool.disableRedoLog(c);
		/*************(不支持)？？？？？？？？********/
//		String address = null;
//		Pool.setVswitchController(c, address);
		/*************(不支持)？？？？？？？？********/
//		Map<String, String> config = null;
//		String x = pool.testArchiveTarget(c, config);
		/*************(不支持)？？？？？？？？********/
//		pool.enableLocalStorageCaching(c);
		/*************(不支持)？？？？？？？？********/
//		pool.disableLocalStorageCaching(c);
		/*************返回系统已知的所有pool********/
//		Set<Pool> pool_set = Pool.getAll(c);
//		System.out.println(pool_set);
		/*************返回系统已知的所有pool的record********/
//		Map<Pool, Pool.Record> map = Pool.getAllRecords(c);
//		System.out.println(map);
		/*************返回系统已知的所有pool的record********/
//		Host host = Host.getByUuid(c, "b37b888e-f069-c075-cbe1-4326d08b538d");
//		Boolean x = pool.isOn(c, host);
//		System.out.println(x);
	}
}
