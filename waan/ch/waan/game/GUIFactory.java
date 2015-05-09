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

import java.awt.HeadlessException;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Factory for {@link GUI} instances
 * 
 * @author Andreas Waelchli
 * @version 1.1, 2015-02-23
 * @since AwaeGameAPI 0.1
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
