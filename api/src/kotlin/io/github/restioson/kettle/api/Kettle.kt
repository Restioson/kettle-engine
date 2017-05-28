package io.github.restioson.kettle.api

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntityListener
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.utils.ImmutableArray
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.GdxRuntimeException
import io.github.restioson.kettle.api.screen.KettleScreen
import kotlin.reflect.KClass

/**
 * Interface to describe engine API functions, implemented by [io.github.restioson.kettle.Kettle][Kettle]
 */
interface Kettle {

    /**
     * Instance of the Box2D world
     */
    var world: World

    /**
     * Array of entities from the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     */
    val entities: ImmutableArray<Entity>

    /**
     * The current screen to be displayed by Kettle
     */
    var screen: KettleScreen

    /**
     * Adds an entity to the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     *
     * @param entity entity to add
     *
     * @throws IllegalArgumentException entity already added
     */
    @Throws(IllegalArgumentException::class)
    fun addEntity(entity: Entity)

    /**
     * Removes all entities from the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     */
    fun removeAllEntities()

    /**
     * Adds an entity system to the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     *
     * @param entitySystem entitySystem to add
     */
    fun addEntitySystem(entitySystem: EntitySystem)

    /**
     * Gets an entity system from the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     *
     * @param type type of entity system
     */
    fun getEntitySystem(type: KClass<EntitySystem>): EntitySystem

    /**
     * Adds an entity listener to the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     *
     * @param listener entity listener to add
     */
    fun addEntityListener(listener: EntityListener)

    /**
     * Adds an entity listener to the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     *
     * @param family family to listen for
     * @param listener entity listener to add
     */
    fun addEntityListener(family: Family, listener: EntityListener)

    /**
     * Adds an entity listener to the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     *
     * @param priority priority of entity listener
     * @param listener entity listener to add
     */
    fun addEntityListener(priority: Int, listener: EntityListener)

    /**
     * Adds an entity listener to the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     *
     * @param priority priority of entity listener
     * @param family family to listen for
     * @param listener entity listener to add
     */
    fun addEntityListener(family: Family, priority: Int, listener: EntityListener)

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
    fun <T> registerAsset(assetDescriptor: AssetDescriptor<T>)

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
    fun <T> getAsset(assetDescriptor: AssetDescriptor<T>): T

    /**
     * Checks whether an asset is loaded
     *
     * @param assetDescriptor [AssetDescriptor](https://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/assets/AssetDescriptor.html) of the desired asset]
     *
     * @return whether it is loaded
     */
    fun <T> isAssetLoaded(assetDescriptor: AssetDescriptor<T>): Boolean
}