package io.github.restioson.kettle.testcontentpackage

import io.github.restioson.kettle.Box2DServerSide
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.Level
import io.github.restioson.kettle.api.physics.Units
import mu.KLogging

class TestServerSide(engine: Kettle) : Box2DServerSide(engine) {

    override var level: Level = TestLevel(engine)

    companion object : KLogging()

    init {
        // Set up physics
        Units.PIXELS_TO_METERS = 1f
    }

}
