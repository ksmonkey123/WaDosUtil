package tests;

import java.net.InetSocketAddress;

import javares.network.udp.layer1.UdpLib;


import junit.framework.TestCase;
import model.interfaces.Udp;

/**
 * @created 04.05.2012
 * @author Julian Schelker
 */
public class CheckAvailabilityTests extends TestCase {
	
	private Udp udp;
	private InetSocketAddress target;
	private InetSocketAddress wrongTarget;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		this.udp = UdpLib.createDefault();
		this.target = new InetSocketAddress("127.0.0.1", udp.getLocalPort());
		this.wrongTarget = new InetSocketAddress("10.0.0.1", 1000);
	}
	
	public void test() {
		long ns = System.nanoTime();
		boolean available = this.udp.isAvailable(this.target, 1000);
		ns = System.nanoTime() - ns;
		System.out.println("Ping: " + ns / 1000 + " qs");
		assertTrue(available);
		available = this.udp.isAvailable(this.wrongTarget, 1000);
		assertFalse(available);
	}
	
}
