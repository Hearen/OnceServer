package com.once.test;

import java.io.IOException;

import com.once.api.Storage;

public class TestStorage {
	public static void main(String[] args) throws IOException {
		String pool_id = "cb19a228-5450-0501-3df3-9c03c7ea107d";
		String vol_id = "27167fe7-fc9d-47d5-9cd0-717106ef67be";
		String target = "hdb";
//		Storage.createPool("pool0", "/home/lhearen/pool0");
//		Storage.deletePool("cb19a228-5450-0501-3df3-9c03c7ea107d");
//		Storage.listPools();
//		Storage.createVolume("pool0", "volume0", 200);
//		Storage.deleteVolume("pool0", "volume1");
		Storage.listVolumes("pool0");
		String vm_id = "57f28539-489f-0a5f-0bf4-9abe45c62763";
//		Storage.attach(vm_id, vol_id, target);
		Storage.detach(vm_id, vol_id);
	}
}
