package io.github.restioson.kettle

import io.github.restioson.kettle.api.ClientSidePackage
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.Level
import io.github.restioson.kettle.api.screen.KettleScreen
import io.github.restioson.kettle.screen.SpriteScreen

/**
 * Base implementation of ClientSidePackage
 */
open class SpriteClientSide(val engine: Kettle, level: Level, private val width: Float, private val height: Float) : ClientSidePackage {

    override var kLevel = level
        set(o) {
            this.kLevel = o
            this.kScreen = SpriteScreen(o, this.width, this.height)
        }

    /**
     * The GameScreen which is currently in use
     *
     * In this case it is an instance of SpriteScreen, which just initialises and adds a SpriteRenderingSystem
     */
    override lateinit var kScreen: KettleScreen

    /**
     * Register assets here
     *
     * EG:
     * ```kotlin
     * this.engine.registerAsset(AssetDescriptor("mySprite.png", Texture::class))
     * ```
     */
    override fun init() {
    }

    /**
     * This function is called after assets are loaded
     *
     * For instance, GUI would be initialised here
     */
    override fun create() {
        this.kScreen = SpriteScreen(this.kLevel, this.width, this.height)
        this.engine.kScreen = this.kScreen
    }

    /**
     * This is called when the game is paused
     */
    override fun pause() {
        this.kScreen.pause()
    }

    /**
     * This is called when the game is unpaused
     */
    override fun resume() {
        this.kScreen.resume()
    }

    /**
     * This is called when the SpriteClientSide is no longer needed, so at exit of the game
     *
     * Dispose of any internal assets used here
     */
    override fun dispose() {
        this.kScreen.dispose()
    }
}