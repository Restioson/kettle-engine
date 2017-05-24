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
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.entity.GraphicsComponent
import io.github.restioson.kettle.api.entity.PositionComponent
import io.github.restioson.kettle.api.entity.RequiresComponent
import io.github.restioson.kettle.api.entity.RequiresComponentException

/**
 * Main engine class, and [io.github.restioson.kettle.api.Kettle][Kettle] implementation
 */
class Engine : Game(), Kettle {

    private lateinit var batch: SpriteBatch
    private lateinit var assetManager: AssetManager
    private lateinit var entityEngine: Engine // TODO pooledengine
    private var delta: Float = 0f

    override fun create() {

        // Initialise fields
        this.batch = SpriteBatch()
        this.assetManager = AssetManager()
        this.entityEngine = Engine()

        // Register assets TODO remove: use ContentPackages
        this.registerAsset(AssetDescriptor("badlogic.jpg", Texture::class.java))

        // TODO load ContentPackages
        this.assetManager.finishLoading()

        val graphicsComponent = GraphicsComponent()
        graphicsComponent.texture = this.getAsset(AssetDescriptor("badlogic.jpg", Texture::class.java))
        val entity: Entity = Entity().add(graphicsComponent).add(PositionComponent())

        this.addEntity(entity)

        // Load assets
        //textures.add(this.getAsset(AssetDescriptor("badlogic.jpg", Texture::class.java)) as Texture) TODO remove
    }

    override fun render() {

        delta = Gdx.graphics.deltaTime

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        this.entityEngine.update(delta)

        this.batch.begin()

        // TODO remove: use contentpackages & render entities with GraphicsComponents

        this.batch.end()

    }


    override fun <Type> registerAsset(assetDescriptor: AssetDescriptor<Type>) {
        this.assetManager.load(assetDescriptor)
    }

    override fun <Type> getAsset(assetDescriptor: AssetDescriptor<Type>): Type {
        return this.assetManager[assetDescriptor]
    }

    override fun addEntity(entity: Entity) {

        // Thanks to waicool20 for helping with this

        // We filter out any components which don't have the @RequiredComponent annotation
        // Component is a component which has the @RequiredComponent annotation
        for (component in entity.components.filter{it::class.annotations.any{it::class == RequiresComponent::class}}) {

            // Here we filter get the @RequiredComponent annotation
            // it is a Component
            (component::class.annotations.firstOrNull { it::class == RequiresComponent::class } as RequiresComponent).component.forEach {

                // Here we loop through all the components and see if none of them are the component which the
                // @RequiredComponent annotation is specifying a requirement for
                if (entity.components.none { requiresComponent -> requiresComponent::class == it }) {

                    // No component found which meeting the specification of @RequiredComponent, so we throw
                    // a RequiresComponentException
                    throw RequiresComponentException(component::class, it)
                }
            }
        }

        this.entityEngine.addEntity(entity) // TODO pooled engine thing
    }

    override fun dispose() {
        this.batch.dispose()
    }
}
