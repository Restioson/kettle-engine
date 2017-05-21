package io.github.restioson.kettle.api.entity

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.Pool

/**
 * Component for graphics
 *
 * ## Requires
 *  - [PositionComponent]
 */
@RequiresComponent(PositionComponent::class)
class GraphicsComponent: Component, Pool.Poolable {

    var texture: Texture? = null

    override fun reset() {
        texture = null
    }
}