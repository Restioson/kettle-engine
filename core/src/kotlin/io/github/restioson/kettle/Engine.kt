package io.github.restioson.kettle

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import io.github.restioson.kettle.api.ContentPackage
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.entity.Box2DComponent
import io.github.restioson.kettle.api.entity.GraphicsComponent
import io.github.restioson.kettle.api.handler.SpriteRenderer
import io.github.restioson.kettle.api.physics.Units
import org.reflections.Reflections

/**
 * Main engine class, and [io.github.restioson.kettle.api.Kettle][Kettle] implementation
 */
class Engine : Game(), Kettle {

    private lateinit var batch: SpriteBatch
    private lateinit var assetManager: AssetManager
    private lateinit var entityEngine: Engine // TODO pooledengine
    private lateinit var world: World // TODO let contentpackages set world
    private var delta: Float = 0f

    // TODO remove
    private lateinit var entity: Entity

    override fun create() {

        // Initialise fields
        this.batch = SpriteBatch()
        this.assetManager = AssetManager()
        this.entityEngine = Engine()
        this.world = World(Vector2(0f, -9.8f * Units.METERS_TO_PIXELS), true)

        // TODO remove
        this.entityEngine.addSystem(SpriteRenderer(640f, 480f)) // TODO remove
        this.registerAsset(AssetDescriptor("assets/test.png", Texture::class.java))

        // TODO load content package
        // this.contentPackage = this.loadContentPackage()

        this.assetManager.finishLoading()

        // TODO remove down from here
        val graphicsComponent = GraphicsComponent()
        graphicsComponent.texture = this.getAsset(AssetDescriptor("assets/test.png", Texture::class.java))

        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.position.set(Vector2(-320f, 240f))

        entity = Entity()
        entity.add(graphicsComponent).add(Box2DComponent(world.createBody(bodyDef), graphicsComponent.texture!!.width * 1f, graphicsComponent.texture!!.height * 1f, 1f))
        this.addEntity(entity)
        // TODO to here
    }

    override fun render() {

        delta = Gdx.graphics.deltaTime

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        this.world.step(delta, 6, 2)
        this.entityEngine.update(delta)

    }

    override fun <Type> registerAsset(assetDescriptor: AssetDescriptor<Type>) {
        this.assetManager.load(assetDescriptor)
    }

    override fun <Type> getAsset(assetDescriptor: AssetDescriptor<Type>): Type {
        return this.assetManager[assetDescriptor]
    }

    override fun addEntity(entity: Entity) {
        this.entityEngine.addEntity(entity) // TODO pooled engine thing
    }

    override fun dispose() {
        this.batch.dispose()
    }

    private fun loadContentPackage(): ContentPackage {
        val reflections = Reflections("")
        return reflections.getSubTypesOf(ContentPackage::class.java).first { true }.newInstance()
    }
}
