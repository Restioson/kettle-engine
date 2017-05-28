package io.github.restioson.kettle.screen

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.OrthographicCamera
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.screen.KettleScreen

class SimpleScreen(override val engine: Kettle, w: Float, h: Float) : KettleScreen {

    override val guiCamera: Camera = OrthographicCamera(w, h)

    override fun render(delta: Float) {
    }

    override fun hide() {
    }

    override fun show() {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
    }

}