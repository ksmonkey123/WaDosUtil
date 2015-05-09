package ch.waan.game.ui;

import org.eclipse.jdt.annotation.NonNull;

import ch.waan.game.Dimension;

/**
 * A component that may have its position and dimension altered by its
 * container.
 * <p>
 * To ensure proper operation with all containers the position and dimension of
 * the component should <i>never</i> be changed externally while being a member
 * of a container. A JavaBean pattern is therefore recommended for these values
 * in any implementation.
 * </p>
 * 
 * @author Andreas Wälchli
 * @version 1.1, 2015-02-23
 */
public interface UIResizable extends UIMovable {

	/**
	 * Updates the component dimension to the given point.
	 * 
	 * @param dimension
	 *            the new dimension. This may <i>never</i> be null
	 */
	public void setDimension(@NonNull Dimension dimension);

}
