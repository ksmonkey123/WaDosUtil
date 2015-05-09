package ch.waan.game;

import java.awt.HeadlessException;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Factory for {@link GUI} instances
 * 
 * @author Andreas Wälchli
 * @version 1.1, 2015-02-23
 */
final class GUIFactory {

	private GUIFactory() {
		throw new AssertionError(); // should not happen
	}

	/**
	 * creates a new UI instance width the given dimension
	 * 
	 * @param width
	 *            the initial window width
	 * @param height
	 *            the initial window height
	 * @param closeAction
	 *            the action to be taken when the window close button is clicked
	 * @return a GUI instance
	 * @throws IllegalStateException
	 *             if double buffering cannot be initialised on the underlying
	 *             window
	 * @throws HeadlessException
	 *             if the JVM is running on a headless system
	 * @throws IllegalArgumentException
	 *             if any of the dimension parameters is not strictly positive
	 */
	static @NonNull GUI createGUI(final int width, final int height,
			@NonNull Runnable closeAction) {
		if (width <= 0)
			throw new IllegalArgumentException(
					"GUI width must be positive. value was " + width);
		if (height <= 0)
			throw new IllegalArgumentException(
					"GUI height must be positive. value was " + height);
		return GUIImpl.newInstance(width, height, closeAction);
	}
}
