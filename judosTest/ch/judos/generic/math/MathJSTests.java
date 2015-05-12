package ch.judos.generic.math;

import java.util.ArrayList;

import ch.judos.generic.math.MathJS;
import junit.framework.TestCase;

/**
 * @since 20.05.2014
 * @author Julian Schelker
 */
public class MathJSTests extends TestCase {

	public void testStdv() {

		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		list.add(6);

		double stdv = MathJS.stdv(list);

		assertTrue(stdv > 0);

	}

}
