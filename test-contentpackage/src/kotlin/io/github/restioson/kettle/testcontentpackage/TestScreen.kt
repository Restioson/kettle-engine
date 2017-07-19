package io.github.restioson.kettle.testcontentpackage

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import io.github.restioson.kettle.api.Box2DLevel
import io.github.restioson.kettle.api.physics.Units
import io.github.restioson.kettle.screen.SpriteScreen

class TestScreen(level: Box2DLevel, width: Float, height: Float) : SpriteScreen(level, width, height) {

    val renderer = Box2DDebugRenderer()
    val matrix = Matrix4(this.guiCamera.combined)
    val batch = SpriteBatch()

    init {
        matrix.scale(Units.PIXELS_IN_METERS, Units.PIXELS_IN_METERS, 1f)
    }

    override fun render(delta: Float) {
        super.render(delta)

        this.batch.begin()
        this.renderer.render(this.kLevel.world, matrix)
        this.batch.end()

    }

    override fun dispose() {
        this.renderer.dispose()
        this.batch.dispose()
    }
}