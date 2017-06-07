package io.github.restioson.kettle

import com.badlogic.ashley.core.Family
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import io.github.restioson.kettle.api.ContentPackage
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.screen.KettleScreen
import io.github.restioson.kettle.entity.component.AssetLocationComponent
import io.github.restioson.kettle.entity.listener.AssetLocationComponentListener
import org.reflections.Reflections

/**
 * Main engine class, and [Kettle] implementation
 */
class Engine : Game(), Kettle {

    override lateinit var assetManager: AssetManager
    private lateinit var contentPackage: ContentPackage
    private var delta: Float = 0f

    override var kScreen: KettleScreen
        get() = super.getScreen() as KettleScreen
        set(value) = super.setScreen(value)

    override fun create() {

        // Initialise fields
        this.assetManager = AssetManager()

        this.contentPackage = this.findContentPackage()
        this.initContentPackage(this.contentPackage)

        this.contentPackage.create() // TODO seperate client from server
        this.assetManager.finishLoading()

        // TODO see AssetLocationComponentListener todo
        this.contentPackage.clientSide.level.entityEngine.addEntityListener(Family.all(AssetLocationComponent::class.java).get(), AssetLocationComponentListener(this))

        this.contentPackage.serverSide.create()
        this.contentPackage.clientSide.create()

    }

    override fun render() {

        delta = Gdx.graphics.deltaTime

        super.render()

        // TODO: Separate game ticks from render
        this.contentPackage.serverSide.step(delta)
    }

    override fun dispose() {
        this.contentPackage.clientSide.dispose()
        this.contentPackage.serverSide.dispose()
        this.assetManager.dispose()
    }

    override fun pause() {
        super.pause()
    }

    private fun findContentPackage(): ContentPackage {
        val reflections = Reflections("")
        return reflections.getSubTypesOf(ContentPackage::class.java).first { true }.newInstance()
    }

    private fun initContentPackage(pack: ContentPackage) {
        pack.engine = this
        this.assetManager.finishLoading()

    }

}
