package io.github.restioson.kettle.testcontentpackage

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import io.github.restioson.kettle.api.physics.Units
import io.github.restioson.kettle.entity.ComponentMappers
import io.github.restioson.kettle.entity.component.AssetLocationComponent
import io.github.restioson.kettle.entity.component.BodyComponentBuilder
import io.github.restioson.kettle.level.Level2D
import mu.KLogging

class TestLevel : Level2D(Vector2(0f, -9.8f)) {
    internal lateinit var player: Entity

    companion object : KLogging()

    override fun create() {

        val floor = BodyDef()
        floor.type = BodyDef.BodyType.StaticBody
        floor.position.set(0f, Units.toMeters(-576 / 2f))
        println(Units.toMeters(-576 / 2f))

        val shape = PolygonShape()
        shape.setAsBox(Units.toMeters(576 / 2f), Units.toMeters(0.5f))

        this.world.createBody(floor).createFixture(shape, 0f)

        shape.dispose()

        logger.debug("Spawning entity...")

        // Create entity
        this.player = this.entityEngine.createEntity()

                .add(this.entityEngine.createComponent(AssetLocationComponent::class.java).apply {
                    descriptor = AssetDescriptor("assets/chicken.png", Texture::class.java)
                })

                .add(BodyComponentBuilder()
                        .withBody(BodyDef.BodyType.DynamicBody)
                        .withBoxFixture(Units.toMeters(256f / 2f), Units.toMeters(256f / 2))
                        .build(this.entityEngine, this@TestLevel.world)
                )

        this.entityEngine.addEntity(this.player)

        logger.debug("Entity successfully spawned!")

    }

    override fun step(delta: Float) {
        super.step(delta)
        println("${ComponentMappers.BODY[this.player].body!!.position}")
    }
}