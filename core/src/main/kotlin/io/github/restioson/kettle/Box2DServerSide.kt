package io.github.restioson.kettle

import com.badlogic.gdx.math.Vector2
import io.github.restioson.kettle.api.Level
import io.github.restioson.kettle.level.Level2D

/**
 * Base implementation of ServerSidePackage
 */

open class Box2DServerSide(gravity: Vector2) : BaseServerSide() {

    override var kLevel: Level = Level2D(gravity)

}
