/**
 * This API is designed for lightweight 2D game implementation.
 * <p>
 * It manages all aspects of the actively rendered UI and the basic tick clock
 * stabilisation. The API supports single-window UIs both in window and in
 * full-screen mode. The base logic is targeted at the usage of a finite state
 * application model. Lightweight state transitions are internally handled. Even
 * game termination is designed as a termination state.
 * </p>
 * <p>
 * The API entry point is the {@link ch.waan.game.Game Game} class. Specifically
 * the static {@link ch.waan.game.Game#initializeGame(SceneController)
 * initializeGame(SceneController)} method. It manages the complete UI
 * initialisation based off the single initial state.
 * </p>
 * 
 * @author Andreas Wälchli
 */
package ch.waan.game;