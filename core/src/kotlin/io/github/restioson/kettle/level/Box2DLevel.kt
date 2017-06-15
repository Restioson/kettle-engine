package io.github.restioson.kettle.level

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.Level
import io.github.restioson.kettle.api.physics.Units

open class Box2DLevel(override val engine: Kettle) : Level {

    override var entityEngine = PooledEngine()

    override var paused = false

    override var world = World(Vector2(0f, -9.8f * Units.PIXELS_TO_METERS), true)

    override fun create() {
    }

    override fun step(delta: Float) {
        this.world.step(delta, 6, 2)
        this.entityEngine.update(delta)
    }

    override fun pause() {
        this.paused = true
    }

    override fun resume() {
        this.paused = false
    }

}
