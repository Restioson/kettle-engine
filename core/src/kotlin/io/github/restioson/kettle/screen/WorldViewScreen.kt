package io.github.restioson.kettle.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.GL20
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.screen.KettleScreen

abstract class WorldScreen(override val engine: Kettle) : KettleScreen {
    private val assetManager = AssetManager()

    init {
        this.assetManager.finishLoading()
    }

    override fun <T> registerAsset(assetDescriptor: AssetDescriptor<T>) {
        this.assetManager.load(assetDescriptor)
    }

    override fun <T> getAsset(assetDescriptor: AssetDescriptor<T>): T {
        return this.assetManager[assetDescriptor]
    }

    override fun <T> isAssetLoaded(assetDescriptor: AssetDescriptor<T>): Boolean {
        return this.assetManager.isLoaded(assetDescriptor.fileName, assetDescriptor.type)
    }

    override fun dispose() {
        this.assetManager.dispose()
    }

    class View2D(engine: Kettle) : WorldScreen(engine) {
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

        override fun render(delta: Float) {
            Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F)
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

            //TODO: Render world
        }
    }

    class View3D(engine: Kettle) : WorldScreen(engine) {
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

        override fun render(delta: Float) {
            Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F)
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
            
            //TODO: Render world
        }
    }
}