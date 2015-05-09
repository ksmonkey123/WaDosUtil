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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;

/**
 * A basic UI container.
 * 
 * <p>
 * A container is a UI component that is also able to contain other components.
 * A container may be designed to contain any arbitrary component or only a
 * certain subset of those components.
 * </p>
 * 
 * @author Andreas Waelchli
 * @version 1.1, 2014-02-22
 * @since AwaeGameAPI 0.1
 * 
 * @param <E>
 *            the component type allowed in the container
 */
public interface UIContainer<@NonNull E extends UIComponent> extends
		UIComponent {

	/**
	 * Adds the given component to the container. The component must be added as
	 * the front-most component. If the component is already a member of the
	 * container, rather than adding it again, it must be moved to the top
	 * 
	 * @param component
	 *            the component to add to the container
	 */
	public void addComponent(@NonNull E component);

	/**
	 * Removes the given component from the container. If the component is not
	 * contained in the container, this method must silently terminate and
	 * return
	 * 
	 * @param component
	 *            the component to remove from the container
	 */
	public void removeComponent(@NonNull E component);

	/**
	 * Returns a list of all components that are contained in this container. If
	 * no such members exist, an empty list must be returned.
	 * 
	 * @return a list containing all components contained in this container
	 */
	public @NonNull List<@NonNull E> getComponents();
}
