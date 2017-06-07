package io.github.restioson.kettle

import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.Level
import io.github.restioson.kettle.api.ServerSidePackage
import io.github.restioson.kettle.level.SimpleLevel

/**
 * Base implementation of ServerSidePackage
 */

open class ServerSide(engine: Kettle) : ServerSidePackage {

    override var level: Level = SimpleLevel(engine)

    override fun create() {
    }

    override fun step(delta: Float) {
    }

    override fun pause() {
    }

    override fun unpause() {
    }

    override fun dispose() {
    }


}
