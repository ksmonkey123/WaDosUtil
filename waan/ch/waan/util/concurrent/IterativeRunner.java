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
package ch.waan.util.concurrent;

/**
 * This premade Runnable implementation allows for a very simple implememtation.
 *
 * @author Andreas Waelchli (andreas.waelchli@me.com)
 * @version 1.1, 2014-11-24
 * @since AwaeUtil 2.0
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
