package io.github.restioson.kettle.testcontentpackage

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver
import com.badlogic.gdx.graphics.Texture
import io.github.restioson.kettle.SpriteClientSide
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.Level

class TestClientSide(engine: Kettle, level: Level, width: Float, height: Float) : SpriteClientSide(engine, level, width, height) {

    init {
        this.engine.assetManager.apply {
            setLoader(Texture::class.java, TextureLoader(LocalFileHandleResolver()))
            load(AssetDescriptor(Gdx.files.local("assets/chicken.png"), Texture::class.java))
        }
    }

}
