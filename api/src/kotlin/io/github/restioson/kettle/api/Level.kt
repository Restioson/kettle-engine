package io.github.restioson.kettle.api

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.physics.box2d.World

interface Level {

    /**
     * Reference to the engine simulating this Level
     */
    val engine: Kettle

    /**
     * Instance of the Box2D world
     */
    // TODO make Box2DLevel with this
    var world: World

    /**
     * Ashley entity engine
     *
     * All components must implement Pool.Poolable
     */
    val entityEngine: PooledEngine

    /**
     * Whether game is paused
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
