package ch.judos.generic.network.udp;

import java.util.PriorityQueue;

import ch.judos.generic.network.udp.model.Packet2ResendConfirmed;
import junit.framework.TestCase;

/**
 * @since 05.07.2013
 * @author Julian Schelker
 */
public class Packet2Tests extends TestCase {
	public void testOrder() {
		Packet2ResendConfirmed p1 = new Packet2ResendConfirmed(0, new byte[0], null, 0);
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}
		Packet2ResendConfirmed p2 = new Packet2ResendConfirmed(1, new byte[0], null, 0);

		assertTrue(p1.compareTo(p2) < 0);

		PriorityQueue<Packet2ResendConfirmed> queue = new PriorityQueue<Packet2ResendConfirmed>();
		queue.add(p1);
		queue.add(p2);

		assertEquals(p1, queue.poll());
	}

}
