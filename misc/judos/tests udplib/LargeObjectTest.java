package tests;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import javares.network.udp.layer1.UdpLib;


import junit.framework.TestCase;
import model.exceptions.CouldNotSendException;
import model.interfaces.Udp;
import model.interfaces.UdpListener;

/**
 * @created 01.05.2012
 * @author Julian Schelker
 */
public class LargeObjectTest extends TestCase implements UdpListener {
	
	private Udp udp;
	private int port;
	private Object received;
	private InetSocketAddress target;
	private int receivedObjects = 0;
	private int sendObjects = 10;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		this.udp = UdpLib.createDefault();
		this.port = udp.getLocalPort();
		this.target = new InetSocketAddress("127.0.0.1", this.port);
	}
	
	public void testLargeString() throws CouldNotSendException,
		InterruptedException {
		String send = genLargeString();
		this.udp.addListener(send.getClass(), this);
		Thread.sleep(20);
		for (int i = 0; i < this.sendObjects; i++) {
			this.udp.sendObject(send, this.target, false);
			Thread.sleep(20);
		}
		assertEquals(send, received);
		System.out.println("Lost (big) Packges: "
			+ (this.sendObjects - this.receivedObjects) + " / "
			+ this.sendObjects);
	}
	
	public String genLargeString() {
		StringBuffer b = new StringBuffer();
		for (int i = 0; i < 5000; i++) {
			b.append("123456789 ");
		}
		return b.toString();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.UdpListener#receiveUdpMessage(java.lang.Object,
	 * java.net.SocketAddress)
	 */
	@Override
	public void receiveUdpMessage(Object obj, SocketAddress sender) {
		this.received = obj;
		this.receivedObjects++;
	}
	
}
