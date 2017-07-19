package io.github.restioson.kettle.api

import com.badlogic.gdx.physics.box2d.World

/**
 * 2D level with Box2D for physics simulation
 */
interface Box2DLevel : Level {

    /**
     * Instance of the Box2D world
     */
    var world: World

}