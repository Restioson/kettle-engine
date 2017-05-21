package io.github.restioson.kettle.api.entity

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool

/**
 * Component for position
 */
class PositionComponent: Component, Pool.Poolable {

    var x: Float = 0f
    var y: Float = 0f

    override fun reset() {
        this.x = 0f
        this.y = 0f
    }
}