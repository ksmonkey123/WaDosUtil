package tests;

import junit.framework.TestCase;
import lib.data.JS_Converter;

/**
 * @created 27.04.2012
 * @author Julian Schelker
 */
public class ByteShiftTests extends TestCase {

	public void testByteShifting() {
		int[] test = new int[] { 127, 128 };
		boolean[] testB = new boolean[] { false, true };

		for (int i = 0; i < test.length; i++) {
			boolean flag = (test[i] >>> 7) == 1;
			assertEquals(flag, testB[i]);
		}

		for (int i = 0; i < 256; i += 32) {
			int x = JS_Converter.unsignedByte2Int(JS_Converter.int2UnsignedByte(i));
			assertEquals(i, x);
		}

		for (int i = 0; i < 256; i += 32) {
			boolean flag1 = (i >= 128);
			boolean flagB = (i >> 7) == 1;
			assertEquals(flag1, flagB);
		}
	}
}
