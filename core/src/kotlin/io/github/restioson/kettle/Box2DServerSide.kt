package io.github.restioson.kettle

import io.github.restioson.kettle.api.Level
import io.github.restioson.kettle.level.Level2D

/**
 * Base implementation of ServerSidePackage
 */

open class Box2DServerSide : BaseServerSide() {

    override var kLevel: Level = Level2D()

}
