package com.once.test;

import java.io.IOException;

import com.once.api.Storage;

public class TestStorage {
	public static void main(String[] args) throws IOException {
//		Storage.createPool("pool0", "/home/lhearen/pool0");
//		Storage.deletePool("cb19a228-5450-0501-3df3-9c03c7ea107d");
		Storage.listPools();
//		Storage.createVolume("pool0", "volume1", 200);
		Storage.deleteVolume("pool0", "volume1");
		Storage.listVolumes("pool0");
	}
}
