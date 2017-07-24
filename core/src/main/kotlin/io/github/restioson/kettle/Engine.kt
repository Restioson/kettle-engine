package io.github.restioson.kettle

import com.badlogic.ashley.core.Family
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.assets.AssetManager
import io.github.restioson.kettle.api.ContentPackage
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.screen.KettleScreen
import io.github.restioson.kettle.entity.component.AssetLocationComponent
import io.github.restioson.kettle.entity.listener.AssetLocationComponentListener
import io.github.restioson.kettle.script.Batch
import mu.KLogging
import org.reflections.Reflections
import org.reflections.scanners.SubTypesScanner
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import org.reflections.util.FilterBuilder

/**
 * Main engine class, and [Kettle] implementation
 */
class Engine : Game(), Kettle {

    override lateinit var assetManager: AssetManager
    override lateinit var multiplexer: InputMultiplexer

    private lateinit var contentPackage: ContentPackage
    private var delta: Float = 0f

    companion object : KLogging() {

        private fun findContentPackage(): ContentPackage {
            val reflections = Reflections(ConfigurationBuilder()
                    .filterInputsBy(FilterBuilder().include(".*\\.class"))
                    .setUrls(Package.getPackages().flatMap { ClasspathHelper.forPackage(it.name) })
                    .setScanners(SubTypesScanner()))

            return reflections.getSubTypesOf(ContentPackage::class.java).first().newInstance()
        }

    }

    override var kScreen: KettleScreen
        get() = super.getScreen() as KettleScreen
        set(value) = super.setScreen(value)

    override fun create() {

        logger.info("Starting Kettle...")

        // Initialize fields
        this.assetManager = AssetManager()
        this.multiplexer = InputMultiplexer()

        this.contentPackage = Engine.findContentPackage()
        this.initContentPackage(this.contentPackage)

        this.contentPackage.create() // TODO separate client from server
        this.contentPackage.initClient()
        this.assetManager.finishLoading()

        this.contentPackage.kServerSide.create()
        this.contentPackage.kClientSide.create()

        Gdx.input.inputProcessor = multiplexer
        this.contentPackage.kClientSide.kScreen.kLevel.entityEngine.addEntityListener(
                Family.all(AssetLocationComponent::class.java).get(),
                AssetLocationComponentListener(this).apply {
                    process(this@Engine.contentPackage.kClientSide.kScreen)
                })

    }

    // TODO: Separate game ticks from render
    override fun render() {

        delta = Gdx.graphics.deltaTime

        // Render screen
        super.render()

        Batch.execute(delta)

        // Update server side
        this.contentPackage.kServerSide.step(delta)
    }

    override fun dispose() {
        this.contentPackage.kClientSide.dispose()
        this.contentPackage.kServerSide.dispose()
        this.assetManager.dispose()
    }

    override fun pause() {
        super.pause()
        this.contentPackage.kClientSide.pause()
        this.contentPackage.kServerSide.pause()
    }

    override fun resume() {
        super.resume()
        this.contentPackage.kClientSide.resume()
        this.contentPackage.kServerSide.resume()
    }

    private fun initContentPackage(pack: ContentPackage) {
        pack.engine = this
        this.assetManager.finishLoading()
    }

}
