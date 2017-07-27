package io.github.restioson.kettle.testcontentpackage

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.assets.loaders.resolvers.ClasspathFileHandleResolver
import com.badlogic.gdx.graphics.Texture
import io.github.restioson.kettle.SpriteClientSide
import io.github.restioson.kettle.api.Kettle

class TestClientSide(engine: Kettle, private var level: TestLevel, var width: Float, var height: Float) : SpriteClientSide(engine, level, width, height) {

    override fun init() {
        this.engine.assetManager.apply {
            setLoader(Texture::class.java, TextureLoader(ClasspathFileHandleResolver()))
            load(AssetDescriptor("assets/chicken.png", Texture::class.java))
        }
    }

    override fun create() {
        this.kScreen = TestScreen(this.kLevel, this.width, this.height)
        this.engine.kScreen = this.kScreen
        this.engine.multiplexer.addProcessor(PlayerInputProcessor(this.level.player))
    }

}
