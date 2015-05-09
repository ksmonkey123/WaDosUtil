/*
 * AwaeGameAPI - easy to use 2D game API
 * Copyright (C) 2015 Andreas Waelchli
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.waan.game;

import javax.swing.JFrame;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Factory for {@link Keyboard} instances
 * 
 * @author Andreas Waelchli
 * @version 1.1, 2015-02-27
 * @since AwaeGameAPI 0.1
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
