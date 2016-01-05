package com.once.test;

import java.io.IOException;
import com.once.api.VM;
import com.once.api.VMConfig;

public class TestVM {

	public static void main(String[] args) throws IOException {
		VMConfig config = new VMConfig("vm", 2, 1024, "/home/res/images/test1.qcow2", "/home/res/iso/CentOS-7.1.iso", "ovs0");
//		String uuidString = "2a230355-5e06-4d1e-9e57-9cf940571c03";
//		config.setUUID(uuidString);
		VM.create(config);
//		VM.start(uuidString);
//		VM.shutdown(uuidString);
//		VM.reboot(uuidString);
//		VM.delete(uuidString);
//		System.out.println(VM.isTemplate(uuidString));
//		VM.setTemplate(uuidString);
//		System.out.println(VM.isTemplate(uuidString));
	}

}
