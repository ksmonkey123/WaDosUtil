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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * This class serves as a wrapper for the Java-side Shutdown handlers with the
 * added option to define the shutdown order.
 *
 * @author Andreas Waelchli (andreas.waelchli@me.com)
 * @version 1.2, 2014-11-17
 * @since Util 1.0
 */
public class Shutdown {

	/**
	 * The default priority.
	 *
	 * @see #add(Runnable)
	 * @see #add(Runnable, int)
	 * @since Util 1.0
	 */
	public static final int			DEFAULT			= 0;

	/**
	 * The default priority for operations that should be performed very early
	 * in the sequence.
	 *
	 * @see #add(Runnable, int)
	 * @since Util 1.0
	 */
	public static final int			EARLIER			= 10000;

	/**
	 * The default priority for operations that should be performed rather early
	 * in the sequence.
	 *
	 * @see #add(Runnable, int)
	 * @since Util 1.0
	 */
	public static final int			EARLY			= 1000;

	/**
	 * The highest possible priority. This should only be used if an operation
	 * has to be performed strictly first.
	 *
	 * @see #add(Runnable, int)
	 * @since Util 1.0
	 */
	public static final int			FIRST			= Integer.MAX_VALUE;

	/**
	 * The lowest possible priority. This should only be used if an operation
	 * has to be performed strictly last.
	 *
	 * @see #add(Runnable, int)
	 * @since Util 1.0
	 */
	public static final int			LAST			= Integer.MIN_VALUE;

	/**
	 * The default priority for operations that should be performed rather late
	 * in the sequence.
	 *
	 * @see #add(Runnable, int)
	 * @since Util 1.0
	 */
	public static final int			LATE			= -1000;

	/**
	 * The default priority for operations that should be performed very late in
	 * the sequence.
	 *
	 * @see #add(Runnable, int)
	 * @since Util 1.0
	 */
	public static final int			LATER			= -10000;

	private static final Shutdown	defaultShutdown	= new Shutdown();

	/**
	 * Provides a global Shutdown handler.
	 *
	 * @return the global Shutdown handler.
	 * @since Util 1.0
	 */
	public static Shutdown getDefaultShutdown() {
		return Shutdown.defaultShutdown;
	}

	private final TreeMap<Integer, ArrayList<Runnable>>	map	= new TreeMap<>();

	{
		Runtime.getRuntime()
				.addShutdownHook(new Thread(this::runShutdown));
	}

	private final void runShutdown() {
		Collection<ArrayList<Runnable>> c = this.map.descendingMap()
				.values();
		for (Iterator<ArrayList<Runnable>> iterator = c.iterator(); iterator.hasNext();) {
			ArrayList<Runnable> arrayList = iterator.next();
			for (Runnable r : arrayList) {
				r.run();
			}
		}
	}

	/**
	 * adds a {@link Runnable} with default priority ({@code 0}) to the shutdown
	 * system. This is a convenience method for {@link #add(Runnable, int)}.
	 *
	 * @param r
	 *            the runnable to add
	 * @throws NullPointerException
	 *             if the {@code r} argument is {@code null}
	 * @see #add(Runnable, int)
	 * @since Util 1.0
	 */
	public void add(Runnable r) {
		this.add(r, Shutdown.DEFAULT);
	}

	/**
	 * adds a {@link Runnable} with a defined priority to the shutdown system.
	 * There currently is no way to remove a handler once it has been added.
	 *
	 * @param r
	 *            the runnable to add
	 * @param priority
	 *            the priority of the runnable
	 * @throws NullPointerException
	 *             if the {@code r} argument is {@code null}
	 * @since Util 1.0
	 */
	public void add(Runnable r, int priority) {
		if (r == null)
			throw new NullPointerException("no null runnables supported");
		if (!this.map.containsKey(priority))
			this.map.put(priority, new ArrayList<>());
		this.map.get(priority)
				.add(r);
	}

}
