package io.github.restioson.kettle.api

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntityListener
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.utils.ImmutableArray
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.GdxRuntimeException
import kotlin.reflect.KClass

/**
 * Interface to describe engine API functions, implemented by [io.github.restioson.kettle.Kettle][Kettle]
 */
interface Kettle {

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
    fun <Type> registerAsset(assetDescriptor: AssetDescriptor<Type>)

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
    fun <Type> getAsset(assetDescriptor: AssetDescriptor<Type>): Type

    /**
     * Checks whether an asset is loaded
     *
     * @param assetDescriptor [AssetDescriptor](https://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/assets/AssetDescriptor.html) of the desired asset]
     *
     * @return whether it is loaded
     */
    fun <Type> isAssetLoaded(assetDescriptor: AssetDescriptor<Type>): Boolean

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
     * Gets all the entities from the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     *
     * @return entities from entity system
     */
    fun getEntities(): ImmutableArray<Entity>

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
     * Gets the instance of the Box2D world in use
     * @return box2d world
     */
    fun getWorld(): World

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
}