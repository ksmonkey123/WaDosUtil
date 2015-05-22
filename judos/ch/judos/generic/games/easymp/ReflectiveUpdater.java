package ch.judos.generic.games.easymp;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @since 23.05.2015
 * @author Julian Schelker
 */
public class ReflectiveUpdater {

	/**
	 * preserve references by updating the content of old with the changes in the
	 * new object
	 * 
	 * @param old
	 * @param change
	 */
	public static void updateObject(Object old, Object change) {
		if (old.getClass() != change.getClass())
			throw new RuntimeException("Can't update - objects are not of equal class");
		try {
			updateObjectCheckless(old, change);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void updateObjectCheckless(Object old, Object change)
		throws IllegalArgumentException, IllegalAccessException {
		for (Field f : old.getClass().getFields()) {
			// skip fields
			if (Modifier.isTransient(f.getModifiers()))
				continue;
			if (Modifier.isStatic(f.getModifiers()))
				continue;

			// update content of primitive fields
			Class<?> c = f.getType();
			if (c.isPrimitive() || c == String.class) {
				f.set(old, f.get(change));
				continue;
			}

		}
	}

}
