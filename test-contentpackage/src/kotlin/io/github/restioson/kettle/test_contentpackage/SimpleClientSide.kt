package io.github.restioson.kettle.test_contentpackage

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import io.github.restioson.kettle.ClientSide
import io.github.restioson.kettle.api.Kettle

class SimpleClientSide(engine: Kettle, width: Float, height: Float) : ClientSide(engine, width, height) {

    init {
        this.engine.registerAsset(AssetDescriptor(Gdx.files.local("assets/chicken.png"), Texture::class.java))
    }

}
