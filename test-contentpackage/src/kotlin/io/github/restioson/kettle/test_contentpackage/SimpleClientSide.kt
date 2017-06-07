package io.github.restioson.kettle.test_contentpackage

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver
import com.badlogic.gdx.graphics.Texture
import io.github.restioson.kettle.ClientSide
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.Level

class SimpleClientSide(engine: Kettle, level: Level, width: Float, height: Float) : ClientSide(engine, level, width, height) {

    init {
        this.engine.assetManager.apply {
            setLoader(Texture::class.java, TextureLoader(LocalFileHandleResolver()))
            load(AssetDescriptor(Gdx.files.local("assets/chicken.png"), Texture::class.java))
        }
    }

}
