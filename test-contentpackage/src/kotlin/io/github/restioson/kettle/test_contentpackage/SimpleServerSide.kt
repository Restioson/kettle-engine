package io.github.restioson.kettle.test_contentpackage

import io.github.restioson.kettle.ServerSide
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.physics.Units

class SimpleServerSide(engine: Kettle) : ServerSide(engine) {

    init {
        Units.PIXELS_TO_METERS = 1f
    }

    override fun step(delta: Float) {
    }

}