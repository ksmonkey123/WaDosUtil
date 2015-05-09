package ch.waan.game;

import java.awt.GraphicsDevice;

import org.eclipse.jdt.annotation.NonNull;

import ch.waan.game.ui.UIComponent;
import ch.waan.game.ui.UIContainer;
import ch.waan.game.ui.UIResizableContainer;

/**
 * The root of the UI system.
 * 
 * This manages the UI system itself and its rendering into a window.
 * 
 * @author Andreas Wälchli
 * @version 1.2, 2015-03-19
 */
public interface GUI {

	/**
	 * Indicates whether or not the UI is currently in full-screen mode.
	 * 
	 * <p>
	 * The full-screen mode should be using the native full-screen exclusive
	 * mode if applicable, resizing to screen width and disabling movement and
	 * resizing should be the alternative.
	 * </p>
	 * 
	 * @return {@code true} if full-screen mode is active
	 */
	boolean isFullscreen();

	/**
	 * Activates or deactivates the full-screen mode.
	 * 
	 * If the new mode is identical to the previous one, no action should be
	 * taken.
	 * 
	 * @param fullscreen
	 *            {@code true} if full-screen mode is requested, {@code false}
	 *             otherwise
	 */
	void setFullscreen(boolean fullscreen);

	/**
	 * Returns the current graphics device for the UI system.
	 * 
	 * This is the graphics device that is used for full-screen mode
	 * 
	 * @return the graphics device
	 */
	@NonNull
	GraphicsDevice getGraphicsDevice();

	/**
	 * Updates the graphics device to use for the UI system.
	 * 
	 * This is the graphics device that is used for full-screen mode. The
	 * graphics device may only be changed when in window mode.
	 * 
	 * @param device
	 *            the new graphics device. This may <i>never</i> be null
	 * @throws IllegalStateException
	 *             if the GUI is currently in full-screen mode
	 */
	void setGraphicsDevice(@NonNull GraphicsDevice device);

	/**
	 * Returns the root container of the UI system.
	 * <p>
	 * The root container will always be centred on the window and sized to fill
	 * it completely. As specified by the {@link UIResizableContainer} any
	 * window resizing will yield an invocation of the
	 * {@link UIComponent#parentUpdated(UIContainer) parentUpdated} method on
	 * all children.
	 * </p>
	 * 
	 * @return the root container of the UI system
	 */
	@NonNull
	UIContainer<UIComponent> getRootContainer();

	/**
	 * Renders the UI system to the screen.
	 * 
	 * This is the active rendering of a single frame. External timing is
	 * required.
	 */
	void render();

	/**
	 * Disposes of the GUI. This effectively closes the window.
	 */
	void dispose();

	/**
	 * Updates the title of the GUI window.
	 * 
	 * @param title
	 *            the new window title. This may <i>never</i> be null
	 */
	void setTitle(@NonNull String title);

	/**
	 * Returns the keyboard instance of this GUI.
	 * 
	 * @return the keyboard.
	 */
	@NonNull
	Keyboard getKeyboard();

	/**
	 * Indicates whether the GUI has components or not.
	 * 
	 * @return {@code true} if the GUI has no components, {@code false}
	 *         otherwise.
	 */
	boolean isEmpty();
}
