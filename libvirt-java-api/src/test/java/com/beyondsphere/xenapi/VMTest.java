package com.beyondsphere.xenapi;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.Assert.*;

import org.apache.xmlrpc.XmlRpcException;
import org.junit.Test;

import com.beyondsphere.xenapi.Types;
import com.beyondsphere.xenapi.Types.OperationNotAllowed;
import com.beyondsphere.xenapi.Types.OtherOperationInProgress;
import com.beyondsphere.xenapi.Types.VmBadPowerState;
import com.beyondsphere.xenapi.Types.VmIsTemplate;
import com.beyondsphere.xenapi.VM;
import com.beyondsphere.xenapi.Types.BadServerResponse;
import com.beyondsphere.xenapi.Types.XenAPIException;
import com.beyondsphere.xenapi.VM.Record;
import com.beyondsphere.xenapi.base.TestBase;
import com.beyondsphere.xenapi.utils.ByteUnitUtil;

public class VMTest extends TestBase {

	/**
	 * @throws Exception
	 * @section 1: This part is about the VM.createOn method's null or blank
	 *          boundary
	 * @start
	 */
	@Test(expected = NullPointerException.class)
	public void testCreateWithNullRecord() throws Exception {
		VM.createOn(this.getConnection(), null, this.getHost());
	}

	@Test(expected = Types.InternalError.class)
	public void testCreateWithNullHost() throws XenAPIException,
			XmlRpcException {
		VM.createOn(this.getConnection(), new Record(), null);
	}

	@Test(expected = NullPointerException.class)
	public void testCreateWithNullConnection() throws Exception {
		VM.createOn(null, new Record(), this.getHost());
	}

	@Test(expected = XmlRpcException.class)
	public void testCreate_WithWrongConnection() throws Exception {
		VM.createOn(new Connection(new URL("HTTP", "127.0.0.1", 9999, "")),
				new Record(), this.getHost());
	}

	@Test(expected = Types.InternalError.class)
	public void testCreateWithBlankRecord() throws Exception {
		VM.createOn(this.getConnection(), new Record(), this.getHost());
	}

	/**
	 * @section 1:
	 * @end
	 */
	/**
	 * 
	 * @section 2: this part is for createOnMethod about VM's mini-core api
	 *          called by the VMUtils
	 * @start
	 */

	// The required parameters include(if don't have the create will
	// fail):memoryStaticMax,memoryDynamicMax,memoryDynamicMin,actionsAfterCrash,actionAfterReboot,actionAfterShutdown,
	@Test
	public void testCreateOnWithMinimalParameterSet() throws Exception {
		Long memoryInBytes = ByteUnitUtil.MigaBytes2Bytes(512L), dynamicMin = ByteUnitUtil
				.MigaBytes2Bytes(512L);
		VM.Record record = createRecordWithMinParams("00:0C:29:01:98:27",
				memoryInBytes, memoryInBytes, memoryInBytes, dynamicMin);
		Long memoryFreeBeforeOperation = this.getHost().getRecord(
				this.getConnection()).memoryFree;
		VM newVM = VM.createOn(this.getConnection(), record,
				this.getErrorHost());
		assertNotNull(newVM);
		assertEquals(Long.valueOf(memoryInBytes),
				newVM.getMemoryStaticMin(this.getConnection()));
		assertEquals(Long.valueOf(memoryInBytes),
				newVM.getMemoryDynamicMax(this.getConnection()));
		assertEquals(Long.valueOf(dynamicMin),
				newVM.getMemoryDynamicMin(this.getConnection()));
		// This is the invariant of the VM.createOn, because it has not start
		// up;
		assertEquals(memoryFreeBeforeOperation,
				this.getHost().getRecord(this.getConnection()).memoryFree);
		newVM.destroy(this.getConnection(), true);
	}

	private VM.Record createRecordWithMinParams(String mac,
			long memoryStaticMax, long memoryStaticMin, long memoryDynamicMax,
			long memoryDynamicMin) {
		VM.Record record = new VM.Record();
		record.MAC = new HashSet<String>();
		record.MAC.add(mac);
		record.memoryStaticMax = (long) memoryStaticMax;
		record.memoryStaticMin = (long) memoryStaticMin;
		record.memoryDynamicMax = (long) memoryDynamicMax;
		record.memoryDynamicMin = (long) memoryDynamicMin;
		record.actionsAfterCrash = Types.toOnCrashBehaviour("restart");
		record.actionsAfterReboot = Types.toOnNormalExit("restart");
		record.actionsAfterShutdown = Types.toOnNormalExit("destroy");
		return record;
	}

	@Test
	public void testCreateOnWithDynamicMaxLessThanDynamicMin() throws Exception {
		Long memoryStaticMaxInBytes = ByteUnitUtil.MigaBytes2Bytes(512L), memoryStaticMinInBytes = ByteUnitUtil
				.MigaBytes2Bytes(512L), memoryDynamicMax = ByteUnitUtil
				.MigaBytes2Bytes(256L), memoryDynamicMin = ByteUnitUtil
				.MigaBytes2Bytes(512L);
		VM.Record record = createRecordWithMinParams("00:0C:29:01:98:27",
				memoryStaticMaxInBytes, memoryStaticMinInBytes,
				memoryDynamicMax, memoryDynamicMin);
		VM newVM = VM.createOn(this.getConnection(), record, this.getHost());
		assertNotNull(newVM);
		assertEquals(memoryDynamicMax,
				newVM.getMemoryDynamicMax(this.getConnection()));
		assertEquals(memoryDynamicMin,
				newVM.getMemoryDynamicMin(this.getConnection()));
		newVM.destroy(this.getConnection(), true);
	}

	@Test(expected = Types.InternalError.class)
	public void testCreateOnWithStaticMaxLessThanStaticMin() throws Exception {
		Long memoryStaticMaxInBytes = ByteUnitUtil.MigaBytes2Bytes(256L), memoryStaticMinInBytes = ByteUnitUtil
				.MigaBytes2Bytes(512L), memoryDynamicInBytes = ByteUnitUtil
				.MigaBytes2Bytes(128L);
		VM newVM = VM.createOn(
				this.getConnection(),
				createRecordWithMinParams("00:0C:29:01:98:27",
						memoryStaticMaxInBytes, memoryStaticMinInBytes,
						memoryDynamicInBytes, memoryDynamicInBytes), this
						.getHost());
		newVM.destroy(this.getConnection(), true);
	}

	/**
	 * @section 2:
	 * @end
	 */

	/**
	 * 
	 * Section 3: do get record's detail field when createOn a host in pool( not
	 * the master), and get the record's field from Master of the pool.
	 */
	@Test
	public void getIsATemplate_InPool_WithCreateOnAnotherHost_GetResultWithMasterHost()
			throws XenAPIException, XmlRpcException, Exception {
		long val = ByteUnitUtil.MigaBytes2Bytes(512l);
		VM target = VM.createOn(this.getConnection(),
				this.createRecordWithMinParams("", val, val, val, val),
				this.getErrorHost());
		assertNotNull(target.getIsATemplate(this.getConnection()));
		target.destroy(this.getConnection(), true);
	}

	@Test
	public void getMemoryStaticMax_InPool_WithCreateOnAnotherHost_GetResultWithMasterHost()
			throws XenAPIException, XmlRpcException, Exception {
		long val = ByteUnitUtil.MigaBytes2Bytes(512l);
		VM target = VM.createOn(this.getConnection(),
				this.createRecordWithMinParams("", val, val, val, val),
				getErrorHost());
		assertEquals(val, target.getMemoryStaticMax(this.getConnection())
				.longValue());
		target.destroy(this.getConnection(), true);
	}

	@Test
	public void getMemoryDynamicMax_InPool_WithCreateOnAnotherHost_GetResultWithMasterHost()
			throws XenAPIException, XmlRpcException, Exception {
		long val = ByteUnitUtil.MigaBytes2Bytes(512l);
		VM target = VM.createOn(this.getConnection(),
				this.createRecordWithMinParams("", val, val, val, val),
				getErrorHost());
		assertEquals(val, target.getMemoryDynamicMax(this.getConnection())
				.longValue());
		target.destroy(this.getConnection(), true);
	}

	@Test
	public void getMemoryDynamicMin_InPool_WithCreateOnAnotherHost_GetResultWithMasterHost()
			throws XenAPIException, XmlRpcException, Exception {
		long val = ByteUnitUtil.MigaBytes2Bytes(512l);
		VM target = VM.createOn(this.getConnection(),
				this.createRecordWithMinParams("", val, val, val, val),
				getErrorHost());
		assertEquals(val, target.getMemoryDynamicMin(this.getConnection())
				.longValue());
		target.destroy(this.getConnection(), true);
	}

	@Test
	public void getVCPUsMax_InPool_WithCreateOnAnotherHost_GetResultWithMasterHost()
			throws XenAPIException, XmlRpcException, Exception {
		long val = ByteUnitUtil.MigaBytes2Bytes(512l);
		VM target = VM.createOn(this.getConnection(),
				this.createRecordWithMinParams("", val, val, val, val),
				getErrorHost());
		assertNotNull(target.getVCPUsMax(this.getConnection()).longValue());
		target.destroy(this.getConnection(), true);
	}

	@Test
	public void createImage_InPool_WithCreateOnAnotherHost_CreateImageOnTheMaster()
			throws XenAPIException, XmlRpcException, Exception {
		long val = ByteUnitUtil.MigaBytes2Bytes(256l);
		VM target = VM.createOn(this.getConnection(),
				this.createRecordWithMinParams("", val, val, val, val),
				this.getErrorHost());
		assertTrue(target.createImage(getConnection(), UUID.randomUUID()
				.toString()));
		target.destroy(this.getConnection(), true);
	}

	/**
	 * Section 3: End
	 */

	/**
	 * 
	 * Section 4 begin: The right way of calling XenAPI, other ways I'll just
	 * think it's wrong.
	 * 
	 */

	@Test
	public void createImage_A$ProperUUID_A$ProperConnection()
			throws XenAPIException, XmlRpcException, Exception {
		long val = ByteUnitUtil.MigaBytes2Bytes(256l);
		VM target = VM.createOn(this.getConnection(),
				this.createRecordWithMinParams("", val, val, val, val),
				this.getHost());
		assertTrue(target.createImage(this.getConnection(), UUID.randomUUID()
				.toString()));
		target.destroy(this.getConnection(), true);
	}

	/**
	 * 
	 * Section 4 end.
	 */

	/**
	 * 
	 * Section 5 start: For the illegal formatted UUID
	 * 
	 */
	@Test
	public void createImage_A$ProperConnection_A$IllegalFormatedUUID_Longer_Than_8()
			throws XenAPIException, XmlRpcException, Exception {
		long val = ByteUnitUtil.MigaBytes2Bytes(256l);
		VM target = VM.createOn(getConnection(),
				this.createRecordWithMinParams("", val, val, val, val),
				this.getHost());
		assertTrue(target.createImage(this.getConnection(),
				"erqtwpasdfasdfasdf"));
		target.destroy(getConnection(), true);
	}

	@Test
	public void createImage_A$ProperConnection_A$IllegalFormatedUUID_Shorter_Than_8()
			throws XenAPIException, XmlRpcException, Exception {
		long val = ByteUnitUtil.MigaBytes2Bytes(256l);
		VM target = VM.createOn(getConnection(),
				this.createRecordWithMinParams("", val, val, val, val),
				getHost());
		assertTrue(target.createImage(getConnection(), "werq"));
		target.destroy(getConnection(), true);
	}

	/**
	 * 
	 * Section 5 end
	 */

	/**
	 * Section 6 start : Test for ConflictUUID
	 */
	@Test
	public void createImage_A$ProperConnection_A$ConflictUUID()
			throws XenAPIException, XmlRpcException, Exception {
		long val = ByteUnitUtil.MigaBytes2Bytes(256l);
		VM target = VM.createOn(getConnection(),
				this.createRecordWithMinParams("", val, val, val, val),
				getHost());
		UUID imageUUID = UUID.randomUUID();
		assertTrue(target.createImage(getConnection(), imageUUID.toString()));
		assertTrue(target.createImage(getConnection(), imageUUID.toString()));
		target.destroy(getConnection(), true);
	}

	/**
	 * Section 6 end
	 */

	@Test(expected = XmlRpcException.class)
	public void cleanShutdown_AObject_WrongConnection()
			throws BadServerResponse, VmBadPowerState,
			OtherOperationInProgress, OperationNotAllowed, VmIsTemplate,
			XenAPIException, MalformedURLException, XmlRpcException {
		VM target = new VM("ref");
		target.cleanShutdown(new Connection(new URL(TestBase.PROTOCAL,
				TestBase.Master_HOST_NAME, TestBase.PORT, "")));
	}

	@Test
	public void cleanShutdown_The_Right_Case() throws Exception {
		long memoryStaticMax = ByteUnitUtil.MigaBytes2Bytes(512l);
		Record record = this.createRecordWithMinParams("00:0C:29:01:98:27",
				memoryStaticMax, memoryStaticMax, memoryStaticMax,
				memoryStaticMax);
		VM target = VM.createOn(this.getConnection(), record, this.getHost());
		System.out.print(target.ref);
		target.startOn(getConnection(), getHost(), false, true);
		assertTrue(target.cleanShutdown(this.getConnection()));
		target.destroy(this.getConnection(), true);
	}
}
