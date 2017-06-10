package io.github.restioson.kettle

import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.Level
import io.github.restioson.kettle.level.Box2DLevel

/**
 * Base implementation of ServerSidePackage
 */

open class Box2DServerSide(engine: Kettle) : BaseServerSide() {

    override var level: Level = Box2DLevel(engine)

}
