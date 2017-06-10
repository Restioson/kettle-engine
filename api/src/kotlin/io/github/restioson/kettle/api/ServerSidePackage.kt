package io.github.restioson.kettle.api

/**
 * Server (& internal Server) side logic for client packages should go here
 */
interface ServerSidePackage {

    var level: Level

    /**
     * Called after clientside and serverside is created
     *
     * Create entities here
     */
    fun create()

    /**
     * Advance one tick
     */
    fun step(delta: Float)

    /**
     * Called on game pause
     */
    fun pause()

    /**
     * Called on game unpause
     */
    fun resume()

    /**
     * Called at exit of game
     */
    fun dispose()

}