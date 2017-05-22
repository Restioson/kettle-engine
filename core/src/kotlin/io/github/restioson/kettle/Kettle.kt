package io.github.restioson.kettle

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.GdxRuntimeException
import io.github.restioson.kettle.api.entity.*

// Can i haz test webhook?

/**
 * Main engine class. Exposes some methods relating to the engine for use in ContentPackages
 */
class Kettle : Game() {

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

    /**
     * Schedules an asset for loading by [AssetManager.load](https://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/assets/AssetManager.html#load--)
     *
     * @param assetDescriptor [AssetDescriptor](https://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/assets/AssetDescriptor.html) of the desired asset
     *
     *
     * @throws [GdxRuntimeException](https://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/utils/GdxRuntimeException.html) asset not loaded
     * @throws [GdxRuntimeException](https://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/utils/GdxRuntimeException.html) no loader for type
     * @throws [GdxRuntimeException](https://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/utils/GdxRuntimeException.html) couldn't load dependencies of asset
     *
     */
    @Throws(GdxRuntimeException::class)
    fun <Type> registerAsset(assetDescriptor: AssetDescriptor<Type>) {
        this.assetManager.load(assetDescriptor)
    }

    /**
     * Retrieves an asset from the [AssetManager](https://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/assets/AssetManager.html)
     *
     * @param assetDescriptor [AssetDescriptor](https://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/assets/AssetDescriptor.html) of the desired asset
     *
     * @return asset from [AssetManager](https://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/assets/AssetManager.html)
     *
     * @throws [GdxRuntimeException](https://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/utils/GdxRuntimeException.html) asset not loaded
     */
    @Throws(GdxRuntimeException::class)
    fun <Type> getAsset(assetDescriptor: AssetDescriptor<Type>): Type {
        return this.assetManager[assetDescriptor]
    }

    /**
     * Adds an entity to the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     *
     * @param entity entity to add
     *
     * @throws IllegalArgumentException entity already added
     * @throws RequiresComponentException entity has a component which requires a component entity does not have
     */
    @Throws(IllegalArgumentException::class)
    fun addEntity(entity: Entity) {

        // Thanks to waicool20 for helping with this

        // We filter out any components which don't have the @RequiredComponent annotation
        // Component is a component which has the @RequiredComponent annotation
        for (component in entity.components.filter{it::class.annotations.any{it::class == RequiresComponent::class}}) {

            // Here we filter out annotations which aren't @RequiredComponent
            // it is a @RequiredComponent annotation
            component::class.annotations.filterIsInstance(RequiresComponent::class.java).forEach {

                // Here we loop through all the components and see if none of them are the component which the
                // @RequiredComponent annotation is specifying a requirement for
                if (entity.components.none {requiresComponent -> requiresComponent::class == it.component}) {

                    // No component found which meeting the specification of @RequiredComponent, so we throw
                    // a RequiresComponentException
                    throw RequiresComponentException(component::class, it.component)
                }
            }
        }

        this.entityEngine.addEntity(entity) // TODO pooled engine thing
    }

    override fun dispose() {
        this.batch.dispose()
    }
}
