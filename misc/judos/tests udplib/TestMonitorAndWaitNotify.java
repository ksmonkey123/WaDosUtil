package tests;

import junit.framework.TestCase;

/**
 * @created 08.05.2012
 * @author Julian Schelker
 */
public class TestMonitorAndWaitNotify extends TestCase {
	
	public void testWaitWithoutNotifying() {
		long ns = System.nanoTime();
		try {
			synchronized (this) {
				this.wait(10);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ns = System.nanoTime() - ns;
		assertTrue(ns / Math.pow(10, 6) < 50);
	}
	
}
