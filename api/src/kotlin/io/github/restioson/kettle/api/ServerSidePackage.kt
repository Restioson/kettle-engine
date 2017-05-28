package io.github.restioson.kettle.api

/**
 * Server (& internal Server) side logic for client packages should go here
 */
interface ServerSidePackage {

    /**
     * Advance one tick
     */
    fun step(delta: Float)

}