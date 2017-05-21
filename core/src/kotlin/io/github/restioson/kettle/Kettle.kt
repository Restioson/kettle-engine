package io.github.restioson.kettle

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.GdxRuntimeException

/**
 * Main engine class. Exposes some methods relating to the engine for use in ContentPackages
 */
class Kettle : Game() {

    private lateinit var batch: SpriteBatch
    private lateinit var assetManager: AssetManager
    private val textures = ArrayList<Texture>()

    override fun create() {

        // Initialise fields
        this.batch = SpriteBatch()
        this.assetManager = AssetManager()

        // Register assets TODO remove: use ContentPackages
        this.registerAsset(AssetDescriptor("badlogic.jpg", Texture::class.java))
        this.registerAsset(AssetDescriptor("nonexistent", Texture::class.java))

        // TODO load ContentPackages
        this.assetManager.finishLoading()

        // Load assets
        textures.add(this.getAsset(AssetDescriptor("badlogic.jpg", Texture::class.java)) as Texture)
    }

    override fun render() {

        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        this.batch.begin()

        // TODO remove: use contentpackages & render Renderables
        for (texture: Texture in this.textures) {
            this.batch.draw(texture, 0f, 0f)
        }

        this.batch.end()

    }

    /**
     * Schedules an asset for loading by [AssetManager.load](https://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/assets/AssetManager.html#load--)
     *
     * @param assetDescriptor [AssetDescriptor](https://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/assets/AssetDescriptor.html) of the desired asset
     *
     *
     * @throws GdxRuntimeException asset not loaded
     * @throws GdxRuntimeException no loader for type
     * @throws GdxRuntimeException couldn't load dependencies of asset
     *
     */
    @Throws(GdxRuntimeException::class)
    fun <Type> registerAsset(assetDescriptor: AssetDescriptor<Type>) {
        this.assetManager.load(assetDescriptor)
    }

    /**
     * Retrieves an asset from the AssetManager
     *
     * @param assetDescriptor AssetDescriptor of the desired asset
     *
     * @return asset from AssetManager
     *
     * @throws GdxRuntimeException asset not loaded
     *
     * @see <a href=https://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/assets/AssetDescriptor.html> AssetManager </a>
     * @see <a href=https://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/assets/AssetDescriptor.html> AssetDescriptor </a>
     */
    @Throws(GdxRuntimeException::class)
    fun <Type> getAsset(assetDescriptor: AssetDescriptor<Type>): Type {
        return this.assetManager[assetDescriptor]
    }

    override fun dispose() {
        this.batch.dispose()
    }
}
