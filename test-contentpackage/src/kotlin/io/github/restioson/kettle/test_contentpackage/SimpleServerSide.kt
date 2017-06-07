package io.github.restioson.kettle.test_contentpackage

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.physics.box2d.BodyDef
import io.github.restioson.kettle.ServerSide
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.Level
import io.github.restioson.kettle.api.physics.Units
import io.github.restioson.kettle.entity.component.AssetLocationComponent
import io.github.restioson.kettle.entity.component.Box2DComponentBuilder
import io.github.restioson.kettle.level.SimpleLevel

class SimpleServerSide(engine: Kettle) : ServerSide(engine) {

    override var level: Level = SimpleLevel(engine)

    init {

        // Set up physics
        Units.PIXELS_TO_METERS = 1f
    }

    override fun create() {
        // Create entity
        val entity = Entity()
                .add(AssetLocationComponent(AssetDescriptor<Texture>("assets/chicken.png", Texture::class.java)))
                .add(Box2DComponentBuilder()
                        .withBody(BodyDef.BodyType.DynamicBody)
                        .withBoxFixture(256f, 256f, true)
                        .build(this.level.world)
                )

        // Add entity to entityEngine
        this.level.entityEngine.addEntity(entity)
    }

    override fun step(delta: Float) {
        this.level.step(delta)
    }

}