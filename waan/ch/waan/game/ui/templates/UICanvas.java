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
package ch.waan.game.ui.templates;

import org.eclipse.jdt.annotation.NonNull;

import ch.waan.game.Dimension;
import ch.waan.game.Point;
import ch.waan.game.ui.UIMovable;

/**
 * A simple fixed-size canvas that can be used for basic sprite rendering or as
 * a base for more complex operations and IO interactions.
 * 
 * @author Andreas Waelchli
 * @version 1.1, 2015-02-23
 * @since AwaeGameAPI 0.1
 */
public abstract class UICanvas implements UIMovable {

	private @NonNull Point position;
	private final @NonNull Dimension dimension;

	/**
	 * Creates a new canvas instance.
	 * 
	 * @param position
	 *            the position of the canvas
	 * @param dimension
	 *            the dimension of the canvas
	 */
	public UICanvas(final @NonNull Point position,
			final @NonNull Dimension dimension) {
		this.position = position;
		this.dimension = dimension;
	}

	@Override
	public final @NonNull Dimension getDimension() {
		return this.dimension;
	}

	@Override
	public final @NonNull Point getPosition() {
		return this.position;
	}

	@Override
	public final void setPosition(final Point position) {
		assert position != null;
		this.position = position;
	}

}
