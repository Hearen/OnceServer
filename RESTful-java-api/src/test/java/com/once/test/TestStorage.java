package com.once.test;

import java.io.IOException;

import com.once.api.Storage;

public class TestStorage {
	public static void main(String[] args) throws IOException {
//		Storage.createPool("pool0", "/home/lhearen/pool0");
//		Storage.deletePool("73291356-19f1-e48f-ca16-ad98e6977c3d");
		Storage.listPools();
		Storage.createVolume("pool0", "volume1", 200);
		Storage.deleteVolume("pool0", "volume0");
		Storage.listVolumes("pool0");
	}
}
