package ch.judos.generic.network.udp;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import junit.framework.TestCase;
import ch.judos.generic.network.udp.interfaces.Layer2Listener;

/**
 * @since 04.07.2013
 * @author Julian Schelker
 */
public class Udp2Tests extends TestCase {

	int				PORT;
	boolean			success;
	private Udp2	u;

	void assertArrayEquals(byte[] senddata, byte[] data) {
		for (int index = 0; index < data.length; index++)
			assertEquals(senddata[index], data[index]);
	}

	private void emptyData() {
		this.success = false;
		Layer2Listener listener = new Layer2Listener() {

			@Override
			public void receivedMsg(int type, byte[] data, InetSocketAddress from) {
				assertEquals(1, type);
				assertEquals(0, data.length);
				assertEquals(Udp2Tests.this.PORT, from.getPort());
				synchronized (this) {
					Udp2Tests.this.success = true;
					notifyAll();
				}
			}
		};
		this.u.addListener(listener);

		try {
			this.u.sendDataTo(1, new byte[0], false,
				new InetSocketAddress("localhost", this.PORT));
		}
		catch (IOException e) {
			fail();
		}
		try {
			synchronized (listener) {
				listener.wait(1000);
			}
		}
		catch (InterruptedException e) {
			fail();
		}
		assertTrue(this.success);
		this.u.removeListener(listener);
	}

	public void excessiveData() {
		final byte[] senddata = new byte[UdpConfig.PACKET_SIZE_BYTES * 2];
		try {
			this.u.sendDataTo(1, senddata, false, new InetSocketAddress("localhost", this.PORT));
			fail();
		}
		catch (IOException e) {
			// expected to throw this exception
		}
	}

	@Override
	protected void setUp() throws Exception {
		@SuppressWarnings("resource")
		DatagramSocket s = new DatagramSocket();
		this.u = new Udp2(new Udp1(new Udp0Reader(s)));
		this.PORT = this.u.getLocalPort();
	}

	@Override
	protected void tearDown() throws Exception {
		this.u.dispose();
	}

	private void someConfirmedData() {
		this.success = false;
		final byte[] senddata = new byte[]{0, 5, 100, -100, 127};
		Layer2Listener listener = new Layer2Listener() {

			@Override
			public void receivedMsg(int type, byte[] data, InetSocketAddress from) {
				assertEquals(5, type);
				assertEquals(senddata.length, data.length);
				assertArrayEquals(senddata, data);
				synchronized (this) {
					Udp2Tests.this.success = true;
					notifyAll();
				}
			}
		};
		this.u.addListener(listener);

		try {
			this.u.sendDataTo(5, senddata, true, new InetSocketAddress("localhost", this.PORT));
		}
		catch (IOException e) {
			fail();
		}
		try {
			synchronized (listener) {
				listener.wait(1000);
			}
		}
		catch (InterruptedException e) {
			fail();
		}
		assertTrue(this.success);
		this.u.removeListener(listener);
	}

	private void someData() {
		this.success = false;
		final byte[] senddata = new byte[]{0, 5, 100, -100, 127};
		Layer2Listener listener = new Layer2Listener() {

			@Override
			public void receivedMsg(int type, byte[] data, InetSocketAddress from) {
				assertEquals(5, type);
				assertEquals(senddata.length, data.length);
				assertArrayEquals(senddata, data);
				synchronized (this) {
					Udp2Tests.this.success = true;
					notifyAll();
				}
			}
		};
		this.u.addListener(listener);

		try {
			this.u.sendDataTo(5, senddata, false, new InetSocketAddress("localhost", this.PORT));
		}
		catch (IOException e) {
			fail();
		}
		try {
			synchronized (listener) {
				listener.wait(1000);
			}
		}
		catch (InterruptedException e) {
			fail();
		}
		assertTrue(this.success);
		this.u.removeListener(listener);
	}

	public void testAll() {
		emptyData();
		someData();
		someConfirmedData();
		excessiveData();
	}
}
