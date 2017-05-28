package io.github.restioson.kettle

import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.ServerSidePackage

/**
 * Base implementation of ServerSidePackage
 */

open class ServerSide(engine: Kettle) : ServerSidePackage {

    override fun step(delta: Float) {
    }

}
