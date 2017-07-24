package io.github.restioson.kettle.entity.component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.Pool

/**
 * Component for graphics
 */
class GraphicsComponent() : Component, Pool.Poolable {

    var texture: Texture? = null

    constructor(texture: Texture) : this() {
        this.texture = texture
    }

    override fun reset() {
        this.texture = null
    }

}