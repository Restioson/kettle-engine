package io.github.restioson.kettle.testcontentpackage

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import io.github.restioson.kettle.api.physics.Units
import io.github.restioson.kettle.entity.component.AssetLocationComponent
import io.github.restioson.kettle.entity.component.BodyComponentBuilder
import io.github.restioson.kettle.level.Level2D
import mu.KLogging

class TestLevel : Level2D() {
    internal lateinit var player: Entity

    companion object : KLogging()

    override fun create() {

        val floor = BodyDef()
        floor.type = BodyDef.BodyType.StaticBody
        floor.position.set(0f, -576 / 2 * Units.PIXELS_IN_METERS)

        val shape = PolygonShape()
        shape.setAsBox(576 / 2f * Units.PIXELS_IN_METERS, 0.5f * Units.PIXELS_IN_METERS)

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
                        .withBoxFixture(256f, 256f, true)
                        .build(this.entityEngine, this@TestLevel.world).apply {
                    body!!.applyLinearImpulse(10f * body!!.mass, 0f, 0f, 0f, true)
                }
                )

        this.entityEngine.addEntity(this.player)

        logger.debug("Entity successfully spawned!")
    }

}