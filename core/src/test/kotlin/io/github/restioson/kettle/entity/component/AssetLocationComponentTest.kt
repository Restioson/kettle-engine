package io.github.restioson.kettle.entity.component

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class AssetLocationComponentTest : StringSpec() {
    init {

        "constructor(descriptor: AssetDescriptor<*>)" {
            val descriptor = AssetDescriptor("-", Texture::class.java)
            val component = AssetLocationComponent(descriptor)
            component.descriptor shouldBe descriptor
        }

        "reset()" {
            val component = AssetLocationComponent(AssetDescriptor("-", Texture::class.java))
            component.fulfilled = true

            component.reset()

            component.descriptor shouldBe null
            component.fulfilled shouldBe false
        }
    }
}
