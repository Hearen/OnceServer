package com.test;

import java.io.IOException;

import com.base.VM;
import com.config.VMConfig;

public class TestVM {

	public static void main(String[] args) throws IOException {
		VMConfig config = new VMConfig(2, 1024, "/home/res/images/vm.qcow2", "/home/res/iso/CentOS-7.1.iso", "ovs0");
		String uuidString = "57f28539-489f-0a5f-0bf4-9abe45c62763";
		String vmName = "vm";
		config.setUUID(uuidString);
		config.setName(vmName);
		VM.create(config);
//		VM.start(uuidString);
//		VM.shutdown(uuidString);
//		VM.reboot(uuidString);
//		VM.delete(uuidString);
//		VM.isTemplate(uuidString);
//		VM.setTemplate(uuidString);
//		VM.isTemplate(uuidString);
//		
//		VM.unsetTemplate(uuidString);
//		VM.isTemplate(uuidString);
	}

}
