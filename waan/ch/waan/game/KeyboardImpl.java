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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Keyboard Implementation. Registers Action listeners to the GUI
 * 
 * @author Andreas Waelchli
 * @version 1.1, 2015-02-27
 * @since AwaeGameAPI 0.1
 */
final class KeyboardImpl implements Keyboard {

	final @NonNull Set<Integer> keyList;

	private final KeyAdapter adapter = new KeyAdapter() {

		@Override
		public void keyPressed(final KeyEvent e) {
			KeyboardImpl.this.keyList.add(Integer.valueOf(e.getKeyCode()));
		}

		@Override
		public void keyReleased(final KeyEvent e) {
			KeyboardImpl.this.keyList.remove(Integer.valueOf(e.getKeyCode()));
		}

	};

	KeyboardImpl(final @NonNull JFrame frame) {
		frame.addKeyListener(this.adapter);
		this.keyList = new HashSet<>();
	}

	@Override
	public boolean isPressed(final int keyCode) {
		return this.keyList.contains(Integer.valueOf(keyCode));
	}

}
