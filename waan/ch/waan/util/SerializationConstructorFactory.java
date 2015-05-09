/*
 * Copyright (C) 2014 Andreas Wälchli (andreas.waelchli@me.com)
 * 
 * This file is part of AwaeUtil.
 * 
 * AwaeUtil is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or any later version.
 * 
 * AwaeUtil is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * AwaeUtil. If not, see <http://www.gnu.org/licenses/>.
 * 
 * Note: This Library is only compatible with Java 8 or newer. (developed under
 * Java 1.8.0_25)
 */
package ch.waan.util;

import java.lang.reflect.Constructor;

import sun.reflect.ReflectionFactory;

/**
 * This factory allows for the generation of empty constructors.
 *
 * <b>This feature should be used with care!</b>
 *
 * @author Andreas Waelchli (andreas.waelchli@me.com)
 * @version 1.1, 2014-11-21
 * @since Util 1.0
 */
public class SerializationConstructorFactory {

	/**
	 * Creates a serialization constructor for a class. This constructor will
	 * create a blank instance without calling any constructor or initialization
	 * block.
	 *
	 * @param clazz
	 *            the class to create a constructor for
	 * @return the created serialization constructor
	 * @throws SecurityException
	 *             if reflection access to the object is denied by the VM
	 */
	public static Constructor<? extends Object> getSerialisationConstructor(
			Class<? extends Object> clazz) {
		try {
			return ReflectionFactory.getReflectionFactory()
					.newConstructorForSerialization(clazz,
							Object.class.getDeclaredConstructor());
		} catch (NoSuchMethodException e) {
			return null;
		}
	}

}
