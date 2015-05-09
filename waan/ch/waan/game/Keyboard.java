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

/**
 * A keyboard recorder for easy keyboard access.
 * 
 * @author Andreas Waelchli
 * @version 1.2, 2015-03-19
 * @since AwaeGameAPI 0.1
 */
public interface Keyboard {

	/**
	 * Indicates if the key with the given key code is currently pressed
	 * 
	 * @param keyCode
	 *            the code of the key to check
	 * @return {@code true} iff the key is pressed.
	 */
	boolean isPressed(int keyCode);

	/**
	 * Indicates if the key with the given key code is currently released.
	 * 
	 * @param keyCode
	 *            the code of the key to check
	 * @return {@code true} iff the key is released
	 * 
	 * @default {@code !isPressed(keyCode)}
	 */
	default boolean isReleased(int keyCode) {
		return !this.isPressed(keyCode);
	}

	/**
	 * Indicates if all given keys are pressed.
	 * 
	 * @param keyCode0
	 *            the first key to check
	 * @param keyCodes
	 *            the remaining keys to check
	 * @return {@code true} iff all keys are pressed
	 * 
	 * @default checks each key code through {@link Keyboard#isPressed
	 *          isPressed} and returns {@code false} if any key is <i>not</i>
	 *          pressed
	 */
	default boolean areAllPressed(int keyCode0, int... keyCodes) {
		if (!this.isPressed(keyCode0))
			return false;
		for (int code : keyCodes)
			if (!this.isPressed(code))
				return false;
		return true;
	}

	/**
	 * Indicates if all given keys are released
	 * 
	 * @param keyCode0
	 *            the first key to check
	 * @param keyCodes
	 *            the remaining keys to check
	 * @return {@code true} iff all given keys are released
	 * 
	 * @default {@code !isAnyPressed(keyCode0, keyCodes)}
	 */
	default boolean areAllReleased(int keyCode0, int... keyCodes) {
		return !this.isAnyPressed(keyCode0, keyCodes);
	}

	/**
	 * Indicates if at least of the given keys is pressed
	 * 
	 * @param keyCode0
	 *            the first key to check
	 * @param keyCodes
	 *            the remaining keys to check
	 * @return {@code true} if at least one of the given keys is pressed
	 * 
	 * @default checks the given key codes with {@link Keyboard#isPressed
	 *          isPressed} and returns {@code true} if any one yields
	 *          {@code true}
	 */
	default boolean isAnyPressed(int keyCode0, int... keyCodes) {
		if (this.isPressed(keyCode0))
			return true;
		for (int code : keyCodes)
			if (this.isPressed(code))
				return true;
		return false;
	}

	/**
	 * Indicates if any of the given keys is released
	 * 
	 * @param keyCode0
	 *            the first key to check
	 * @param keyCodes
	 *            the remaining keys to check
	 * @return {@code true} if at least one of the given keys is released
	 * @default {@code !areAllPressed(keyCode0, keyCodes)}
	 */
	default boolean isAnyReleased(int keyCode0, int... keyCodes) {
		return !this.areAllPressed(keyCode0, keyCodes);
	}

}
