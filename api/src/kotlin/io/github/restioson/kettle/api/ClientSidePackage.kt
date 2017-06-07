package io.github.restioson.kettle.api

import io.github.restioson.kettle.api.screen.KettleScreen

/**
 * Client side logic for content packages should go here
 *
 * Register assets in constructor
 */
interface ClientSidePackage {

    /**
     * Current screen
     */
    val screen: KettleScreen

    /**
     * Current level
     */
    // TODO ClientLevel interface?
    val level: Level

    /**
     * Called after assets are loaded, so initialise things requiring assets here
     */
    fun create()

    /**
     * Called on pause of game
     */
    fun pause()

    /**
     * Called on resume of game
     */
    fun resume()

    /**
     * Dispose of internal Disposables
     */
    fun dispose()

}