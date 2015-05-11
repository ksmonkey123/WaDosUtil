package tests;

import java.net.InetSocketAddress;

import javares.network.udp.layer1.UdpLib;


import junit.framework.TestCase;
import model.exceptions.CouldNotSendException;
import model.interfaces.Udp;

/**
 * @created 04.05.2012
 * @author Julian Schelker
 */
public class TestConfirmationMessages extends TestCase {
	
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
	
	public void testConfirmation() {
		Object message = new String("Some really important message!");
		// synchronous method call, if it doesn't return there must be a problem
		try {
			this.udp.sendObject(message, this.target, true);
		} catch (CouldNotSendException e) {
			fail();
		}
		
		try {
			this.udp.sendObject(message, this.wrongTarget, true);
		} catch (CouldNotSendException e) {
			// ok because target is not running UdpLib
		}
	}
	
}
