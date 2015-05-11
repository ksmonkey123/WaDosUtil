package tests;

import junit.framework.TestCase;
import lib.div.JS_Serializer;
import lib.div.JS_SerializerException;

import org.w3c.dom.Document;

/**
 * @created 26.04.2012
 * @author Julian Schelker
 */
public class CheckXMLSerializedSize extends TestCase {
	
	public void testSize() throws JS_SerializerException {
		String xml = "<root><type>someTestMessage</type><clients>"
			+ "<client><ip>127.0.0.1</ip><port>50000</port><name>julian</name>"
			+ "</client></clients></root>";
		Document d = JS_Serializer.text2Document(xml);
		// System.out.println("xml2bytes: " + xml.length());
		// System.out.println("obj2bytes: " +
		// Serializer.object2Bytes(d).length);
		assertTrue(xml.length() < JS_Serializer.object2Bytes(d).length);
	}
}
