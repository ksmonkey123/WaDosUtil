package tests;

import junit.framework.TestCase;
import lib.data.JS_Cloner;
import lib.data.JS_TupleR;

/**
 * @created 28.04.2012
 * @author Julian Schelker
 */
public class CloneTester extends TestCase {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testCloneTuples() throws CloneNotSupportedException {
		
		JS_TupleR t = new JS_TupleR(2.4f, "asdf");
		JS_TupleR t2 = (JS_TupleR) t.clone();
		assertEquals(t, t2);
		
		t = new JS_TupleR(2, 4.2);
		t2 = (JS_TupleR) t.clone();
		assertEquals(t, t2);
	}
	
	public void testCloneArrays() throws CloneNotSupportedException,
		NoSuchMethodException, SecurityException {
		int[][] x = new int[2][2];
		x[0][0] = 42;
		
		int[][] y = (int[][]) JS_Cloner.tryClone(x);
		assertEquals(x[0][0], y[0][0]);
	}
}
