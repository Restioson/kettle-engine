package io.github.restioson.kettle.api

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.utils.GdxRuntimeException
import io.github.restioson.kettle.api.entity.RequiresComponentException

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
     * Adds an entity to the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     *
     * @param entity entity to add
     *
     * @throws IllegalArgumentException entity already added
     * @throws RequiresComponentException entity has a component which requires a component entity does not have
     */
    @Throws(IllegalArgumentException::class, RequiresComponentException::class)
    fun addEntity(entity: Entity)

}