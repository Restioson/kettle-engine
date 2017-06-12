package io.github.restioson.kettle.testcontentpackage

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.physics.box2d.BodyDef
import io.github.restioson.kettle.Box2DServerSide
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.Level
import io.github.restioson.kettle.api.physics.Units
import io.github.restioson.kettle.entity.component.AssetLocationComponent
import io.github.restioson.kettle.entity.component.BodyComponentBuilder
import io.github.restioson.kettle.level.Box2DLevel
import mu.KLogging

class TestServerSide(engine: Kettle) : Box2DServerSide(engine) {

    override var level: Level = Box2DLevel(engine)

    companion object : KLogging()

    init {
        // Set up physics
        Units.PIXELS_TO_METERS = 5f
    }

    override fun create() {

        logger.debug("Spawning entity...")

        // Create entity
        this.level.entityEngine.apply {
            addEntity(
                    createEntity()
                            .add(createComponent(AssetLocationComponent::class.java).apply {
                                descriptor = AssetDescriptor("assets/chicken.png", Texture::class.java)
                            })

                            .add(BodyComponentBuilder()
                                    .withBody(BodyDef.BodyType.DynamicBody)
                                    .withBoxFixture(256f, 256f, true)
                                    .build(this, this@TestServerSide.level.world).apply {
                                        this.body!!.applyLinearImpulse(10f * body!!.mass, 0f, 0f, 0f, true)
                                    }
                            )
            )
        }

        logger.debug("Entity successfully spawned!")
    }
}
