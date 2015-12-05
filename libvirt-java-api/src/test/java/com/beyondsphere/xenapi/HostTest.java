package com.beyondsphere.xenapi;

import java.net.MalformedURLException;
import java.util.Map;
import java.util.Set;

import org.apache.xmlrpc.XmlRpcException;

import com.beyondsphere.xenapi.Connection;
import com.beyondsphere.xenapi.Host;
import com.beyondsphere.xenapi.Types.BadServerResponse;
import com.beyondsphere.xenapi.Types.XenAPIException;
import com.beyondsphere.xenapi.base.TestBase;

import org.junit.Test;

public class HostTest extends TestBase {
	@Test
	public void testHost() {
		try {
			Set<Host> beforeOperate = Host.getAll(this.getConnection());
			for (Host host : beforeOperate) {
				host.getUuid(this.getConnection());
			}
		} catch (BadServerResponse e) {
			e.printStackTrace();
		} catch (XenAPIException e) {
			e.printStackTrace();
		} catch (XmlRpcException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws BadServerResponse,
			XenAPIException, XmlRpcException, MalformedURLException {
		Connection c = new Connection("http://133.133.135.8:9363", "root",
				"onceas");
		/************* 通过UUID值获取Host对象实例 ********/
		Host host = Host.getByUuid(c, "b37b888e-f069-c075-cbe1-4326d08b538d");
		/************* 返回一个Record对象，该对象包含给定主机的当前状态(出错) ********/
		// Host.Record record = host.getRecord(c);
		/************* 根据虚拟机名称获取Host类型的集合 ********/
		// String label = "CentOS_4.5_32-bit_1";
		// Set<Host> set = Host.getByNameLabel(c, label);
		// System.out.println(set.size());
		/************* 获取物理机的uuid ********/
		// String uuid = host.getUuid(c);
		// System.out.println(uuid);
		/************* 获取物理机的NameLabel ********/
		// String nameLabel = host.getNameLabel(c);
		// System.out.println(nameLabel);
		/************* 获取物理机的NameDescription ********/
		// String nameDescription = host.getNameDescription(c);
		// System.out.println(nameDescription);
		/************* 获取物理机的所有光纤信息的集合 ********/
		// Set<String> fiber_set = host.getAllFibers(c);
		// System.out.println(fiber_set);
		/************* 获取物理机的所有可用光纤信息的集合 ********/
		// Set<String> fiber_set = host.getAvailFibers(c);
		// System.out.println(fiber_set);
		/************* 获取物理机的API版本号或者major域 ********/
		// Long version = host.getAPIVersionMajor(c);
		// System.out.println(version);
		/************* 获取物理机的API版本号或者minor域 ********/
		// Long version = host.getAPIVersionMinor(c);
		// System.out.println(version);
		/************* 获取物理机的API版本号或者vendor域 ********/
		// String version = host.getAPIVersionVendor(c);
		// System.out.println(version);
		/************* 获取物理机的API版本号或者vendor_implementation域 ********/
		// Map<String, String> version =
		// host.getAPIVersionVendorImplementation(c);
		// System.out.println(version);
		/************* 获取物理机的enabled域 ********/
		// Boolean version = host.getEnabled(c);
		// System.out.println(version);
		/************* 获取物理机的software_version域 ********/
		// Map<String, String> version = host.getSoftwareVersion(c);
		// System.out.println(version);
		/************* 获取物理机的other_config域 ********/
		// Map<String, String> other_config = host.getOtherConfig(c);
		// System.out.println(other_config);
		/************* 获取物理机的capabilities域 ********/
		// Set<String> capabilities = host.getCapabilities(c);
		// System.out.println(capabilities);
		/************* 获取物理机的cpu_configuration域 ********/
		// Map<String, String> cpu_configuration = host.getCpuConfiguration(c);
		// System.out.println(cpu_configuration);
		/************* 获取物理机的sched_policy域 ********/
		// String sched_policy = host.getSchedPolicy(c);
		// System.out.println(sched_policy);
		/************* 获取物理机支持的Bootloader ********/
		// Set<String> loader_set = host.getSupportedBootloaders(c);
		// System.out.println(loader_set);
		/************* 获取物理机的ResidentVM集合 ********/
		// Set<VM> residentVM_set = host.getResidentVMs(c);
		// System.out.println(residentVM_set);
		/************* 获取物理机的logging域(出错) ********/
		Map<String, String> logging = host.getLogging(c);
		System.out.println(logging);
		/************* 获取物理机的PIFs域 ********/
		// Set<PIF> logging = host.getPIFs(c);
		// System.out.println(logging);
		/************* 获取物理机的PBD域 ********/
		// Set<PBD> pbd = host.getPBDs(c);
		// System.out.println(pbd);
		/************* 获取物理机的host_CPUs域 ********/
		// Set<HostCpu> host_CPUs = host.getHostCPUs(c);
		// System.out.println(host_CPUs);
		/************* 获取物理机的address域 ********/
		// String address = host.getAddress(c);
		// System.out.println(address);
		/************* 获取物理机的metrics域 ********/
		// HostMetrics metrics = host.getMetrics(c);
		// System.out.println(metrics);
		/************* 设置物理机的name/label域 ********/
		// String label = "";
		// host.setNameLabel(c, label);
		/************* 设置物理机的description域 ********/
		// String description = "";
		// host.setNameDescription(c, description);
		/************* 设置物理机的otherConfig域 ********/
		// Map<String, String> otherConfig = null;
		// host.setOtherConfig(c, otherConfig);
		/************* 为指定物理机增加otherConfig域 ********/
		// String key = null;
		// String value = null;
		// host.addToOtherConfig(c, key, value);
		/************* 为指定物理机删除otherConfig域 ********/
		// String key = null;
		// host.removeFromOtherConfig(c, key);
		/************* 为指定物理机设置logging域 ********/
		// Map<String, String> logging = null;
		// host.setLogging(c, logging);
		/************* 设置指定物理机上的虚拟机不能被启动，当前处于活跃状态的虚拟机仍然可以继续执行 ********/
		// host.disable(c);
		/************* 设置指定物理机的状态为其上的虚拟机能被启动 ********/
		// host.enable(c);
		/************* 关闭指定的物理机 ********/
		// host.shutdown(c);
		// 该方法只有在当前物理机上没有正在运行的虚拟机并且物理机处于disable的状态时才可以调用
		/************* 重启指定的物理机 ********/
		// host.reboot(c);
		// 该方法只有在当前物理机上没有正在运行的虚拟机并且物理机处于disable的状态时才可以调用
		/************* 获取指定物理机的硬件信息dmesg ********/
		// String dmesg = host.dmesg(c);
		// System.out.println(dmesg);
		/************* 获取指定物理机的硬件信息dmesg,然后清除缓存 ********/
		// String dmesg = host.dmesgClear(c);
		// System.out.println(dmesg);
		/************* 获取指定物理机的日志 ********/
		// String log = host.getLog(c);
		// System.out.println(log);
		/************* 将给定的字符串作为debugging key注入xen ********/
		// String keys = "";
		// host.sendDebugKeys(c, keys);
		/************* 列举所有支持的方法(出错) ********/
		// Set<String> method_set = Host.listMethods(c);
		// System.out.println(method_set);
		/************* 列举所有系统已知的物理机 ********/
		// Set<Host> host_set = Host.getAll(c);
		// System.out.println(host_set);
		/************* 列举所有Record ********/
		// Map<Host, Host.Record> host_set = Host.getAllRecords(c);
		// System.out.println(host_set);

	}

}
