package io.github.restioson.kettle

import io.github.restioson.kettle.api.ClientSidePackage
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.Level
import io.github.restioson.kettle.screen.SpriteScreen

/**
 * Base implementation of ClientSidePackage
 */
open class SpriteClientSide(val engine: Kettle, override val level: Level, val width: Float, val height: Float) : ClientSidePackage {

    /**
     * The GameScreen which is currently in use
     *
     * In this case it is an instance of SpriteScreen, which just initialises and adds a SpriteRenderingSystem
     */
    override lateinit var screen: SpriteScreen

    /**
     * Register assets here
     *
     * EG:
     * ```kotlin
     * this.engine.registerAsset(AssetDescriptor("mySprite.png", Texture::class))
     * ```
     */
    init {
    }

    /**
     * This function is called after assets are loaded
     *
     * For instance, GUI would be initialised here
     */
    override fun create() {
        this.screen = SpriteScreen(this.engine, this.level, this.width, this.height)
        this.engine.kScreen = this.screen
    }

    /**
     * This is called when the game is paused
     */
    override fun pause() {
        this.screen.pause()
    }

    /**
     * This is called when the game is unpaused
     */
    override fun resume() {
        this.screen.resume()
    }

    /**
     * This is called when the SpriteClientSide is no longer needed, so at exit of the game
     *
     * Dispose of any internal assets used here
     */
    override fun dispose() {
        this.screen.dispose()
    }
}