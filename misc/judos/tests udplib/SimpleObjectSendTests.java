package tests;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import javares.network.udp.layer1.UdpLib;


import junit.framework.TestCase;
import model.exceptions.CouldNotSendException;
import model.interfaces.Udp;
import model.interfaces.UdpListener;

/**
 * @created 30.04.2012
 * @author Julian Schelker
 */
public class SimpleObjectSendTests extends TestCase implements UdpListener {
	
	private Udp udp;
	private Object received;
	private int receivedPackges = 0;
	private int sendPackages = 100;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		this.udp = UdpLib.createDefault();
	}
	
	public void testSendAndReceive() throws CouldNotSendException,
		InterruptedException {
		int port = this.udp.getLocalPort();
		String sent = "Hallo Welt!";
		udp.addListener(sent.getClass(), this);
		for (int i = 0; i < sendPackages; i++) {
			udp.sendObject(sent, new InetSocketAddress("127.0.0.1", port),
				false);
			Thread.sleep(20);
		}
		assertEquals(received, sent);
		
		udp.removeListener(this);
		Thread.sleep(20);
		System.out.println("Packages lost: "
			+ (this.sendPackages - this.receivedPackges));
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
		this.receivedPackges++;
	}
}
