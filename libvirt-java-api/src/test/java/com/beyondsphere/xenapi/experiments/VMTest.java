package com.beyondsphere.xenapi.experiments;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.apache.xmlrpc.XmlRpcException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.beyondsphere.xenapi.Types;
import com.beyondsphere.xenapi.VM;
import com.beyondsphere.xenapi.Types.XenAPIException;
import com.beyondsphere.xenapi.VM.Record;
import com.beyondsphere.xenapi.base.TestBase;
import com.beyondsphere.xenapi.utils.ByteUnitUtil;

@RunWith(Parameterized.class)
public class VMTest extends TestBase {
	private Record input;
	private Record expectedResult;

	@Parameters
	public static Collection<Object[]> inputParameters() {
		return Arrays.asList(new Object[][] {
				// case 1: the input is null
				{ null, null },
				// case 2: the normal input and expected
				{
						createRecordWithMinParams(
								ByteUnitUtil.MigaBytes2Bytes(512L),
								ByteUnitUtil.MigaBytes2Bytes(512L),
								ByteUnitUtil.MigaBytes2Bytes(512L),
								ByteUnitUtil.MigaBytes2Bytes(512L)),
						createVm(512L, 512L, 512L, 512L) } });
	}

	private static VM createVm(long memoryStaticMax, long memoryStaticMin,
			long memoryDynamicMax, long memoryDynamicMin) {
		try {
			VM result = VM.class.newInstance();
			result.getClass().getField("memoryStaticMax")
					.setLong(result, memoryStaticMax);
			result.getClass().getField("memoryStaticMin")
					.setLong(result, memoryStaticMin);
			result.getClass().getField("memoryDynamicMax")
					.setLong(result, memoryDynamicMax);
			result.getClass().getField("memoryDynamicMin")
					.setLong(result, memoryDynamicMin);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static VM.Record createRecordWithMinParams(long memoryStaticMax,
			long memoryStaticMin, long memoryDynamicMax, long memoryDynamicMin) {
		VM.Record record = new VM.Record();
		record.memoryStaticMax = (long) memoryStaticMax;
		record.memoryStaticMin = (long) memoryStaticMin;
		record.memoryDynamicMax = (long) memoryDynamicMax;
		record.memoryDynamicMin = (long) memoryDynamicMin;
		record.actionsAfterCrash = Types.toOnCrashBehaviour("restart");
		record.actionsAfterReboot = Types.toOnNormalExit("restart");
		record.actionsAfterShutdown = Types.toOnNormalExit("destroy");
		return record;
	}

	public VMTest(Record input, Record expectedResult) {
		this.input = input;
		this.expectedResult = expectedResult;
	}

	@Test
	public void testCreateOn() throws XenAPIException, XmlRpcException {
		Record actualResult = null;
		try {
			actualResult = VM.createOn(this.getConnection(), this.input,
					this.getHost()).getRecord(this.getConnection());
			assertWithoutException(this.expectedResult, actualResult);
		} catch (Exception e) {
			assertWithException(e);
		}
	}

	private void assertWithException(Exception e) {
		if (this.input == null || this.expectedResult == null) {
			assertEquals(NullPointerException.class, e.getClass());
		} else {
			assertEquals(Types.InternalError.class, e.getClass());
		}
	}

	private void assertWithoutException(Record expected, Record actual) {
		assertEquals(expected.memoryDynamicMax, actual.memoryDynamicMax);
		assertEquals(expected.memoryDynamicMin, actual.memoryDynamicMin);
		assertEquals(expected.memoryStaticMax, actual.memoryStaticMax);
		assertEquals(expected.memoryStaticMin, actual.memoryStaticMin);
	}

	public interface TestGeneration {
		public Collection<Object[]> generatorCases();

		public void assertWithoutException();

		public void assertWithException();
	}
}
