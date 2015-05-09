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

import java.awt.event.MouseEvent;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Constants for the 3 mouse buttons.
 * 
 * @author Andreas Waelchli
 * @version 1.1, 2015-02-22
 * @since AwaeGameAPI 0.1
 */
public enum MouseButton {

	/** The left mouse button */
	LEFT,
	/** The middle mouse button (mouse wheel button) */
	MIDDLE,
	/** The right mouse button */
	RIGHT;

	/**
	 * Returns the MouseButton for the button of a given MouseEvent
	 * 
	 * @param e
	 *            the event to get the button for
	 * @return the corresponding button
	 * @throws IllegalArgumentException
	 *             if the given MouseEvent does not hold a valid mouse button.
	 */
	public static @NonNull MouseButton getButtonForEvent(@NonNull MouseEvent e) {
		int btn = e.getButton();
		switch (btn) {
		case MouseEvent.BUTTON1:
			return LEFT;
		case MouseEvent.BUTTON2:
			return MIDDLE;
		case MouseEvent.BUTTON3:
			return RIGHT;
		default:
			throw new IllegalArgumentException("mouse event " + e
					+ "had illegal button value: " + btn);
		}
	}
}
