package ch.waan.util;

import java.util.Objects;

import org.eclipse.jdt.annotation.NonNull;

import ch.waan.collection.PriorityQueue;

/**
 * This class serves as a wrapper for the Java-side Shutdown handlers with the
 * added option to define the shutdown order.
 *
 * @author Andreas Wälchli
 * @version 1.3, 2015-05-10
 */
public class Shutdown {

	/**
	 * The default priority.
	 *
	 * @see #add(Runnable)
	 * @see #add(Runnable, int)
	 */
	public static final int			DEFAULT			= 0;

	private static final Shutdown	defaultShutdown	= new Shutdown();

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

	/**
	 * Provides a global Shutdown handler.
	 *
	 * @return the global Shutdown handler
	 */
	public static Shutdown getDefaultShutdown() {
		return Shutdown.defaultShutdown;
	}

	// private final TreeMap<Integer, ArrayList<Runnable>> map = new
	// TreeMap<>();
	private final PriorityQueue<Runnable>	queue	= PriorityQueue.maxQueue();

	{
		Runtime.getRuntime()
				.addShutdownHook(new Thread(this::runShutdown));
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
	public void add(@NonNull Runnable r) {
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
	public void add(@NonNull Runnable r, int priority) {
		Objects.requireNonNull(r, "no null runnable allowed");
		this.queue.add(r, priority);
	}

	private final void runShutdown() {
		while (!this.queue.isEmpty()) {
			this.queue.element()
					.run();
		}
	}

}
