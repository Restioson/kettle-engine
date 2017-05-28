package io.github.restioson.kettle.test_contentpackage

import com.badlogic.ashley.core.Entity
import io.github.restioson.kettle.api.ClientSidePackage
import io.github.restioson.kettle.api.ContentPackage
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.ServerSidePackage

class Package : ContentPackage {

    override lateinit var engine: Kettle
    override lateinit var clientSide: ClientSidePackage
    override lateinit var serverSide: ServerSidePackage
    val entity: Entity = Entity()

    init {
        this.serverSide = SimpleServerSide(this.engine)
        this.clientSide = SimpleClientSide(this.engine, 640f, 360f)
    }

    override fun create() {

        this.clientSide.create()

        /*
        val graphicsComponent = GraphicsComponent()
        graphicsComponent.texture = this.engine.getAsset(AssetDescriptor("assets/chicken.png", Texture::class.java))

        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.position.set(Vector2(0f, 0f))

        this.entity.add(graphicsComponent).add(Box2DComponent(this.engine.level.world.createBody(bodyDef), graphicsComponent.texture!!.width * 1f, graphicsComponent.texture!!.height * 1f, 1f))
        this.engine.level.addEntity(entity)

        TODO above code must be moved somewhere...
        */
    }
}