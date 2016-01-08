package com.test;

import java.io.IOException;

import com.base.Storage;
import com.config.StoragePoolConfig;
import com.config.VolumeConfig;

public class TestStorage {
	public static void main(String[] args) throws IOException {
		String poolName = "pool0";
		String poolUuidString = "b8a2c665-2735-0f10-f42c-28907e09e613";
		String volName = "volume0";
		String volUuidString = "97176fe7-fc9d-47d5-9cd0-717106ef67be";
		String target = "hdb";
		StoragePoolConfig poolConfig = new StoragePoolConfig("/home/lhearen/pool0");
		poolConfig.setUuid(poolUuidString);
		poolConfig.setName(poolName);
//		Storage.createPool(poolConfig);
//		Storage.deletePool(poolUuidString);
//		Storage.listPools();
		VolumeConfig volConfig = new VolumeConfig("pool0", 20);
		volConfig.setUuid(volUuidString);
		volConfig.setVolName(volName);
//		Storage.createVolume(volConfig);
//		Storage.deleteVolume(volUuidString);
		Storage.listVolumes(poolUuidString);
//		String vm_id = "57f28539-489f-0a5f-0bf4-9abe45c62763";
//		Storage.attach(vm_id, vol_id, target);
//		Storage.detach(vm_id, vol_id);
	}
}
