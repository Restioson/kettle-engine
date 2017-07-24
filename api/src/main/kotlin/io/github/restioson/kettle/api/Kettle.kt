package io.github.restioson.kettle.api

import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.assets.AssetManager
import io.github.restioson.kettle.api.screen.KettleScreen

/**
 * Interface to describe engine API functions, implemented by Engine
 */
interface Kettle {

    /**
     * The current screen to be displayed by Kettle
     */
    var kScreen: KettleScreen

    /**
     * AssetManager used for loading, storing, and retrieving assets
     */
    var assetManager: AssetManager

    /**
     * InputMultiplexer for handling input
     */
    var multiplexer: InputMultiplexer
}