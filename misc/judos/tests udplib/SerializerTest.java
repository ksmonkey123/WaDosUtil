package tests;

import java.util.ArrayList;

import junit.framework.TestCase;
import lib.div.JS_Serializer;
import lib.div.JS_SerializerException;

import org.w3c.dom.Document;

/**
 * @created 26.04.2012
 * @author Julian Schelker
 */
public class SerializerTest extends TestCase {
	
	public void testObject() throws JS_SerializerException {
		ArrayList<Integer> x = new ArrayList<Integer>();
		x.add(3);
		x.add(10000);
		x.add(-100);
		byte[] data = JS_Serializer.object2Bytes(x);
		Object o = JS_Serializer.bytes2object(data);
		assertEquals(x, o);
	}
	
	public void testXml() throws JS_SerializerException {
		String xml = "<root><type>someTestMessage</type><clients>"
			+ "<client><ip>127.0.0.1</ip><port>50000</port><name>julian</name>"
			+ "</client></clients></root>";
		Document doc = JS_Serializer.text2Document(xml);
		String xml2 = JS_Serializer.document2Text(doc);
		assertEquals(xml, xml2);
	}
	
	public void testIntegers() {
		int[] i = new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 1000,
			-42 };
		byte[] data = new byte[6];
		for (int test : i) {
			JS_Serializer.int2bytes(data, 1, test);
			int test2 = JS_Serializer.bytes2int(data, 1);
			assertEquals(test, test2);
		}
	}
	
}
