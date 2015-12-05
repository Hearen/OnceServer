package com.beyondsphere.xenapi.base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.apache.xmlrpc.XmlRpcException;

import com.beyondsphere.xenapi.Connection;
import com.beyondsphere.xenapi.Host;
import com.beyondsphere.xenapi.Session;
import com.beyondsphere.xenapi.Types.BadServerResponse;
import com.beyondsphere.xenapi.Types.SessionAuthenticationFailed;
import com.beyondsphere.xenapi.Types.XenAPIException;

public abstract class TestBase {
	private static final String USER_NAME = "root";
	private static final String PWD = "onceas";
	protected static final String PROTOCAL = "HTTP";
	protected static final String Master_HOST_NAME = "133.133.135.12";
	protected static final String Slave_HOST_NAME = "133.133.135.16";
	protected static final int PORT = 9363;
	private URL url;
	private Connection connection = new Connection(url);

	protected TestBase() {
		try {
			this.url = new URL(PROTOCAL, Master_HOST_NAME, PORT, "/");
			this.connection = new Connection(url);
			Session.loginWithPassword(this.connection, USER_NAME, PWD, null);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (BadServerResponse e) {
			e.printStackTrace();
		} catch (SessionAuthenticationFailed e) {
			e.printStackTrace();
		} catch (XenAPIException e) {
			e.printStackTrace();
		} catch (XmlRpcException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return this.connection;
	}

	public Host getErrorHost() throws Exception {
		return this.getHostByIp(Slave_HOST_NAME);
	}

	public Host getHost() throws Exception {
		return getHostByIp(Master_HOST_NAME);
	}

	private Host getHostByIp(String hostNameByIp) throws Exception {
		try {
			Set<Host> beforeOperate = Host.getAll(this.getConnection());
			for (Host host : beforeOperate) {
				if (host.getAddress(this.getConnection()).equals(hostNameByIp)) {
					return host;
				}
			}
			throw new RuntimeException();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
