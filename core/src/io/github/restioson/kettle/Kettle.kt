package io.github.restioson.kettle

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Kettle : Game() {

    private lateinit var batch: SpriteBatch
    private lateinit var img: Texture
    private lateinit var assetManager: AssetManager
    private val textures = ArrayList<Texture>()

    override fun create() {

        // Initialise fields
        this.batch = SpriteBatch()
        this.assetManager = AssetManager()

        // Register assets TODO remove: use ContentPackages
        this.registerAsset(AssetDescriptor("badlogic.jpg", Texture::class.java))

        // TODO load ContentPackages

        // Load assets
        textures.add(this.getAsset(AssetDescriptor("badlogic.jpg", Texture::class.java)) as Texture)
    }

    override fun render() {

        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        this.batch.begin()

        // TODO remove: use contentpackages & render Renderables (proposed interface)
        for (texture: Texture in this.textures) {
            this.batch.draw(texture, 0f, 0f)
        }

        this.batch.end()

    }

    fun <Type> registerAsset(assetDescriptor: AssetDescriptor<Type>) {
        this.assetManager.load(assetDescriptor)
    }

    fun <Type> getAsset(assetDescriptor: AssetDescriptor<Type>): Type {
        return this.assetManager.get(assetDescriptor)
    }

    override fun dispose() {
        this.batch.dispose()
        this.img.dispose()
    }
}
