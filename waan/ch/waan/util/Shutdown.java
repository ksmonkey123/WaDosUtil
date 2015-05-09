package ch.waan.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * This class serves as a wrapper for the Java-side Shutdown handlers with the
 * added option to define the shutdown order.
 *
 * @author Andreas Wälchli
 * @version 1.2, 2014-11-17
 */
public class Shutdown {

	/**
	 * The default priority.
	 *
	 * @see #add(Runnable)
	 * @see #add(Runnable, int)
	 */
	public static final int			DEFAULT			= 0;

	/**
	 * The default priority for operations that should be performed very early
	 * in the sequence.
	 *
	 * @see #add(Runnable, int)
	 */
	public static final int			EARLIER			= 10000;

	/**
	 * The default priority for operations that should be performed rather early
	 * in the sequence.
	 *
	 * @see #add(Runnable, int)
	 */
	public static final int			EARLY			= 1000;

	/**
	 * The highest possible priority. This should only be used if an operation
	 * has to be performed strictly first.
	 *
	 * @see #add(Runnable, int)
	 */
	public static final int			FIRST			= Integer.MAX_VALUE;

	/**
	 * The lowest possible priority. This should only be used if an operation
	 * has to be performed strictly last.
	 *
	 * @see #add(Runnable, int)
	 */
	public static final int			LAST			= Integer.MIN_VALUE;

	/**
	 * The default priority for operations that should be performed rather late
	 * in the sequence.
	 *
	 * @see #add(Runnable, int)
	 */
	public static final int			LATE			= -1000;

	/**
	 * The default priority for operations that should be performed very late in
	 * the sequence.
	 *
	 * @see #add(Runnable, int)
	 */
	public static final int			LATER			= -10000;

	private static final Shutdown	defaultShutdown	= new Shutdown();

	/**
	 * Provides a global Shutdown handler.
	 *
	 * @return the global Shutdown handler
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
