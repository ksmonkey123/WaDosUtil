package ch.judos.generic.wrappers;

import java.util.TimerTask;


/**
 * @since 23.05.2015
 * @author Julian Schelker
 */
public class WrappedTimerTask extends TimerTask {
	
	
	private Runnable	runnable;

	public WrappedTimerTask(Runnable runnable) {
		this.runnable = runnable;
	}

	@Override
	public void run() {
		this.runnable.run();
	}

}
