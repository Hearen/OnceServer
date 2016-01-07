package com.once.test;

import java.io.IOException;

import com.once.api.Storage;

public class TestStorage {
	public static void main(String[] args) throws IOException {
		String poolName = "pool0";
		String volName = "volume0";
		String target = "hda";
//		Storage.createPool("pool0", "/home/lhearen/pool0");
//		Storage.deletePool("cb19a228-5450-0501-3df3-9c03c7ea107d");
//		Storage.listPools();
//		Storage.createVolume("pool0", "volume0", 200);
//		Storage.deleteVolume("pool0", "volume1");
		Storage.listVolumes("pool0");
		String vmUuidString = "57f28539-489f-0a5f-0bf4-9abe45c62763";
//		Storage.attach(vmUuidString, poolName, volName, target);
		Storage.detach(vmUuidString, target);
	}
}
