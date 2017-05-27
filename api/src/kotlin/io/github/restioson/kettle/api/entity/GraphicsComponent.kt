package io.github.restioson.kettle.api.entity

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.Pool
import ktx.assets.disposeSafely

/**
 * Component for graphics
 */
class GraphicsComponent : Component, Disposable, Pool.Poolable {

    var texture: Texture? = null

    override fun reset() {
        this.texture = null
    }

    /**
     * This function should only be called if the texture used in this component is unique
     * If it is disposed while other components are using the same texture, Kettle will crash
     * Use of this function is highly discouraged unless you know *exactly* what you are doing
     * If you need to dispose of this asset for everything, rather use the AssetManager
     */
    override fun dispose() {
        this.texture.disposeSafely()
    }
}