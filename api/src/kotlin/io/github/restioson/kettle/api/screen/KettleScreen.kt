package io.github.restioson.kettle.api.screen

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Camera
import io.github.restioson.kettle.api.Level

interface KettleScreen : Screen {

    /**
     * Camera to render GUI with
     */
    val guiCamera: Camera

    /**
     * Level being displayed.
     * Used for adding the `AssetLocationComponent` listener to the level *client side*
     */
    val kLevel: Level

    /**
     * Render
     */
    override fun render(delta: Float): Unit {
    }

    /**
     * Hide screen
     */
    override fun hide() {
    }

    /**
     * Show screen
     */
    override fun show() {
    }

    /**
     * Pause screen
     */
    override fun pause() {
    }

    /**
     * Resume screen
     */
    override fun resume() {
    }

    /**
     * Resize screen
     */
    override fun resize(width: Int, height: Int) {
    }

    /**
     * Dispose of assets internally used by screen
     */
    override fun dispose() {
    }
}
