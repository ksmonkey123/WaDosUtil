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
 * @author Andreas Wälchli
 * @version 1.1, 2014-02-22
 * 
 * @param <E>
 *            the component type allowed in the container
 */
public interface UIContainer<@NonNull E extends UIComponent> extends UIComponent {

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
