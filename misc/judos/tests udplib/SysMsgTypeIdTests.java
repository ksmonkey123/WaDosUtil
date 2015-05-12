package tests;

import junit.framework.TestCase;
import lib.div.JS_Introspection;
import model.sysmsg.SysMsg;

/**
 * @created 08.05.2012
 * @author Julian Schelker
 */
public class SysMsgTypeIdTests extends TestCase {
	
	@SuppressWarnings("unchecked")
	public void testSysMsgTypeId() {
		Class<?>[] x = JS_Introspection.getClassesFromPackage("model/sysmsg");
		for (Class<?> c : x) {
			if (!c.equals(SysMsg.class)) {
				try {
					int t = SysMsg.getType((Class<? extends SysMsg>) c);
					assertEquals(c, SysMsg.getClass(t));
				} catch (Exception e) {
					System.err.println("Error with ID of Class: "
						+ c.getCanonicalName()
						+ " (did you add it to SysMsg.list?)");
					fail();
				}
			}
		}
	}
	
	public void testConstructorsAllImplemented() throws NoSuchMethodException,
		SecurityException {
		Class<?>[] x = JS_Introspection.getClassesFromPackage("model/sysmsg");
		for (Class<?> c : x) {
			if (!c.equals(SysMsg.class)) {
				try {
					c.getConstructor((new byte[0]).getClass());
				} catch (Exception e) {
					System.err
						.println(c.getCanonicalName()
							+ " -> has to implement Constructor with one arg of type byte[].");
					fail();
				}
			}
		}
	}
}
