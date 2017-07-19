package io.github.restioson.kettle.testcontentpackage

import io.github.restioson.kettle.Box2DServerSide
import io.github.restioson.kettle.api.Level
import io.github.restioson.kettle.api.physics.Units
import mu.KLogging

class TestServerSide : Box2DServerSide() {

    override var kLevel: Level = TestLevel()

    companion object : KLogging()

    init {
        // Set up physics
        Units.METERS_IN_PIXELS = 16f
    }

}
