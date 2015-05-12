package tests;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import javares.network.udp.layer1.UdpLib;

import junit.framework.TestCase;
import lib.div.JS_Serializer;
import lib.div.JS_SerializerException;
import model.exceptions.CouldNotSendException;
import model.interfaces.Udp;
import model.interfaces.UdpListener;

import org.w3c.dom.Document;


/**
 * @created 30.04.2012
 * @author Julian Schelker
 */
public class ShortDocumentSendTest extends TestCase implements UdpListener {
	
	private Udp udp;
	private Object received;
	private int sendPackages = 100;
	private int receivedPackages = 0;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		this.udp = UdpLib.createDefault();
	}
	
	public void testSendAndReceive() throws InterruptedException,
		JS_SerializerException, CouldNotSendException {
		int port = this.udp.getLocalPort();
		String xml = "<root><type>helloWorld</type><data><text>Hallo Welt!</text></data></root>";
		Document sent = JS_Serializer.text2Document(xml);
		udp.addListener(sent.getClass(), this);
		InetSocketAddress target = new InetSocketAddress("127.0.0.1", port);
		for (int i = 0; i < sendPackages; i++) {
			udp.sendObject(sent, target, false);
			Thread.sleep(20);
		}
		
		String xml1 = JS_Serializer.document2Text(sent);
		String xml2 = JS_Serializer.document2Text((Document) received);
		assertEquals(xml1, xml2);
		Thread.sleep(20);
		System.out.println("Packages lost: "
			+ (sendPackages - receivedPackages));
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
		this.receivedPackages++;
	}
}
