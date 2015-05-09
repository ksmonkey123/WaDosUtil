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
 * @author Andreas Wälchli
 * @version 1.1, 2015-02-23
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
