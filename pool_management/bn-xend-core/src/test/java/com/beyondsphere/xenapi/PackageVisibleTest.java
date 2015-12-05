package com.beyondsphere.xenapi;

import static org.junit.Assert.*;

import org.junit.Test;

import com.beyondsphere.xenapi.VIF;

public class PackageVisibleTest {
	@Test
	public void test() {
		assertNotNull(new VIF("can i?"));
	}
}
