package io.github.restioson.kettle

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.github.restioson.kettle.api.ContentPackage
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.level.Level
import io.github.restioson.kettle.api.screen.KettleScreen
import org.reflections.Reflections

/**
 * Main engine class, and [io.github.restioson.kettle.api.Kettle][Kettle] implementation
 */
class Engine : Game(), Kettle {

    private lateinit var batch: SpriteBatch

    private lateinit var assetManager: AssetManager

    private lateinit var contentPackage: ContentPackage

    override lateinit var level: Level

    private var delta: Float = 0f

    override var kScreen: KettleScreen
        get() = super.getScreen() as KettleScreen
        set(value) = super.setScreen(value)

    override fun create() {

        // Initialise fields
        this.batch = SpriteBatch()
        this.assetManager = AssetManager()

        this.contentPackage = this.loadContentPackage()
        this.contentPackage.engine = this
        this.contentPackage.registerResources()

        this.assetManager.finishLoading()
        // TODO: Level should be initialized by CP
        this.level = SimpleLevel(this)
        this.contentPackage.create()
    }

    override fun render() {

        delta = Gdx.graphics.deltaTime

        super.render()

        // TODO: Separate game ticks from render
        this.level.step(delta)
        this.kScreen.render(delta)
    }

    override fun <T> registerAsset(assetDescriptor: AssetDescriptor<T>) {
        this.assetManager.load(assetDescriptor)
    }

    override fun <T> getAsset(assetDescriptor: AssetDescriptor<T>): T {
        return this.assetManager[assetDescriptor]
    }

    override fun <T> isAssetLoaded(assetDescriptor: AssetDescriptor<T>): Boolean {
        return this.assetManager.isLoaded(assetDescriptor.fileName, assetDescriptor.type)
    }

    override fun dispose() {
        this.batch.dispose()
        this.assetManager.dispose()
    }

    private fun loadContentPackage(): ContentPackage {
        val reflections = Reflections("")
        return reflections.getSubTypesOf(ContentPackage::class.java).first { true }.newInstance()
    }
}
