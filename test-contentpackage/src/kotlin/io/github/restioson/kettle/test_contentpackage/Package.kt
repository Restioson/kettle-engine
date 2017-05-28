package io.github.restioson.kettle.test_contentpackage

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import io.github.restioson.kettle.api.ContentPackage
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.entity.component.Box2DComponent
import io.github.restioson.kettle.api.entity.component.GraphicsComponent
import io.github.restioson.kettle.api.screen.KettleScreen
import io.github.restioson.kettle.screen.WorldScreen

class Package : ContentPackage {

    override lateinit var engine: Kettle
    val entity: Entity = Entity()
    lateinit var screen: KettleScreen

    override fun registerResources() {
        println(Gdx.files.local("assets/test.png").file().absolutePath)
        screen.registerAsset(AssetDescriptor(Gdx.files.local("assets/test.png"), Texture::class.java))
    }

    override fun create() {
        this.engine = engine
        this.screen = WorldScreen.View2D(engine)
        this.engine.screen = this.screen

        val graphicsComponent = GraphicsComponent()
        graphicsComponent.texture = this.screen.getAsset(AssetDescriptor("assets/test.png", Texture::class.java))

        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.position.set(Vector2(0f, 0f))

        entity.add(graphicsComponent).add(Box2DComponent(this.engine.world.createBody(bodyDef), graphicsComponent.texture!!.width * 1f, graphicsComponent.texture!!.height * 1f, 1f))
        this.engine.addEntity(entity)
    }

    override fun tick(delta: Double) {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun dispose() {
    }
}