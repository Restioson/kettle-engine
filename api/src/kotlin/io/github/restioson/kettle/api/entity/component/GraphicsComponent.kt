package io.github.restioson.kettle.api.entity.component

import ktx.assets.disposeSafely

/**
 * Component for graphics
 */
class GraphicsComponent : com.badlogic.ashley.core.Component, com.badlogic.gdx.utils.Disposable, com.badlogic.gdx.utils.Pool.Poolable {

    var texture: com.badlogic.gdx.graphics.Texture? = null

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