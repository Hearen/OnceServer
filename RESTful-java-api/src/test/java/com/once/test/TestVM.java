package com.once.test;

import java.io.IOException;
import com.once.api.VM;
import com.once.api.VMConfig;

public class TestVM {

	public static void main(String[] args) throws IOException {
		VMConfig config = new VMConfig("vm", 2, 1024, "/home/res/images/vm.qcow2", "/home/res/iso/CentOS-7.1.iso", "ovs0");
		String uuidString = "57f28539-489f-0a5f-0bf4-9abe45c62763";
		config.setUUID(uuidString);
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
