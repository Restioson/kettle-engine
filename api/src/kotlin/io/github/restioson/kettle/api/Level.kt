package io.github.restioson.kettle.api

import com.badlogic.ashley.core.PooledEngine

interface Level {

    /**
     * Ashley entity engine
     *
     * All components must implement Pool.Poolable
     */
    val entityEngine: PooledEngine

    /**
     * Whether simulation is paused
     */
    var paused: Boolean

    /**
     * Called on creation of level
     */
    fun create()

    /**
     * Steps this level by a single tick
     *
     * @param delta the delta time
     */
    fun step(delta: Float)

    /**
     * Called to pause sim
     */
    fun pause()

    /**
     * Called to unpause sim
     */
    fun resume()

}
