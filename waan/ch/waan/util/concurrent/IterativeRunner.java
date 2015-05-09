package ch.waan.util.concurrent;

/**
 * This premade Runnable implementation allows for a very simple implementation.
 *
 * @author Andreas Wälchli
 * @version 1.1, 2014-11-24
 */
public abstract class IterativeRunner implements Runnable {

	@Override
	public final void run() {
		this.onStart();
		Thread owner = Thread.currentThread();
		interruptBox: {
			loop: while (!owner.isInterrupted()) {
				try {
					if (this.step())
						continue;
					break interruptBox;
				} catch (InterruptedException e) {
					break loop;
				}
			}
			this.onInterrupt();
		}
		this.onTerminate();
	}

	/**
	 * This method is invoked once each iteration step.
	 *
	 * @return {@code true} if the iteration should proceed, {@code false} if it
	 *         should terminate.
	 * @throws InterruptedException
	 *             if the iteration was interrupted and should be terminated
	 *             using the interrupt shutdown procedure
	 */
	protected abstract boolean step() throws InterruptedException;

	/**
	 * This method is invoked if an interrupt is detected. The implementation of
	 * must always return normally.
	 */
	protected void onInterrupt() {
		// default: no action
	}

	/**
	 * This method is invoked whenever the iteration is terminated.
	 *
	 * This allows for the implementation of cleanup code. If the termination
	 * was due to an interrupt, {@link #onInterrupt()} will be invoked before
	 * this method.
	 */
	protected void onTerminate() {
		// default: no action
	}

	/**
	 * This method is invoked before the first iteration step.
	 *
	 * This allows for the implementation of setup code.
	 */
	protected void onStart() {
		// default: no action
	}

}
