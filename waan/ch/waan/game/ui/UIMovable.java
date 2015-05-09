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
package ch.waan.game.ui;

import org.eclipse.jdt.annotation.NonNull;

import ch.waan.game.Point;

/**
 * A component that may have its position altered by its container.
 * <p>
 * To ensure proper operation with all containers the position of the component
 * should <i>never</i> be changed externally while being a member of a
 * container. A JavaBean pattern is therefore recommended for this value in any
 * implementation.
 * </p>
 * 
 * @author Andreas Waelchli
 * @version 1.1, 2015-02-23
 * @since AwaeGameAPI 0.1
 */
public interface UIMovable extends UIComponent {

	/**
	 * Updates the component position to the given point.
	 * 
	 * @param position
	 *            the new position. This may <i>never</i> be null
	 */
	public void setPosition(@NonNull Point position);

}
