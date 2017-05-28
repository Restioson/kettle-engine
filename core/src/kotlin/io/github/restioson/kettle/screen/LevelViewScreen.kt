package io.github.restioson.kettle.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.screen.KettleScreen

abstract class LevelScreen(override val engine: Kettle) : KettleScreen {
    class View2D(engine: Kettle) : LevelScreen(engine) {
        override fun show() {
        }

        override fun pause() {
        }

        override fun resume() {
        }

        override fun resize(width: Int, height: Int) {
        }

        override fun hide() {
        }

        override fun dispose() {
        }

        override fun render(delta: Float) {
            Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F)
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

            //TODO: Render world
        }
    }

    class View3D(engine: Kettle) : LevelScreen(engine) {
        override fun show() {
        }

        override fun pause() {
        }

        override fun resume() {
        }

        override fun resize(width: Int, height: Int) {
        }

        override fun hide() {
        }

        override fun dispose() {
        }

        override fun render(delta: Float) {
            Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F)
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
            
            //TODO: Render world
        }
    }
}
