package io.github.restioson.kettle.test_contentpackage

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import io.github.restioson.kettle.ServerSide
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.entity.component.AssetLocationComponent
import io.github.restioson.kettle.api.entity.component.Box2DComponent
import io.github.restioson.kettle.api.physics.Units

class SimpleServerSide(val engine: Kettle) : ServerSide(engine) {

    val entity: Entity = Entity()

    init {

        // Set up physics
        Units.PIXELS_TO_METERS = 1f

        // Create entity
        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.position.set(Vector2(0f, 0f))

        this.entity.add(AssetLocationComponent(AssetDescriptor("assets/chicken.png", Texture::class.java)))
        this.entity.add(Box2DComponent(this.engine.level.world.createBody(bodyDef), 256f * Units.METERS_TO_PIXELS, 256f * Units.METERS_TO_PIXELS, 1f))

        // Add entity to entityEngine
        this.engine.level.addEntity(entity)
    }

    override fun step(delta: Float) {
    }

}