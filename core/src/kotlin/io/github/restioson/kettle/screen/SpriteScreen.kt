package io.github.restioson.kettle.screen

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.OrthographicCamera
import io.github.restioson.kettle.api.Box2DLevel
import io.github.restioson.kettle.api.screen.KettleScreen
import io.github.restioson.kettle.entity.system.SpriteRenderer

open class SpriteScreen(final override val kLevel: Box2DLevel, w: Float, h: Float) : KettleScreen {

    override val guiCamera: Camera = OrthographicCamera(w, h)

    init {
        this.kLevel.entityEngine.addSystem(SpriteRenderer(w, h))
    }

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