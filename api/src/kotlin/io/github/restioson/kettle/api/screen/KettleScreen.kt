package io.github.restioson.kettle.api.screen

import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.utils.GdxRuntimeException
import io.github.restioson.kettle.api.Kettle

interface KettleScreen : Screen {

    /**
     * The engine associated with this screen
     */
    val engine: Kettle

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