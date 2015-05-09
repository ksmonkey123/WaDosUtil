package ch.waan.game;

import javax.swing.JFrame;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Factory for {@link Keyboard} instances
 * 
 * @author Andreas Wälchli
 * @version 1.1, 2015-02-27
 */
final class KeyboardFactory {

	private KeyboardFactory() {
		throw new AssertionError(); // unreachable
	}

	/**
	 * Creates a new keyboard instance bound to the given frame
	 * 
	 * @param frame
	 *            the frame to bind the keyboard to
	 * @return the keyboard instance
	 */
	static @NonNull Keyboard createKeyboard(final @NonNull JFrame frame) {
		return new KeyboardImpl(frame);
	}

}
