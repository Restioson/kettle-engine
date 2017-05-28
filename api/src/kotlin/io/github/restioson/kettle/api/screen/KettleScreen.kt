package io.github.restioson.kettle.api.screen

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Camera
import io.github.restioson.kettle.api.Kettle

interface KettleScreen : Screen {

    /**
     * The engine associated with this screen
     */
    val engine: Kettle

    /**
     * Camera to render GUI with
     */
    val guiCamera: Camera

    /**
     * Render GUI
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
