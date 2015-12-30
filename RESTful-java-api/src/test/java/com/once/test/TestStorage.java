package com.once.test;

import java.io.IOException;

import com.once.api.Storage;

public class TestStorage {
	public static void main(String[] args) throws IOException {
//		Storage.createPool("pool0", "/home/lhearen/pool0");
//		Storage.deletePool("27167fe7-fc9d-47d5-9cd0-717106ef67be");
		Storage.listPools();
//		Storage.createVolume("pool0", "volume0", 200);
//		Storage.deleteVolume("pool0", "volume0");
//		Storage.listVolumes("pool0");
	}
}
