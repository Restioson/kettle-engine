package io.github.restioson.kettle.api.entity

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool

/**
 * Component for movement
 *
 * ## Requires
 *  - [PositionComponent]
 */
@RequiresComponent(PositionComponent::class)
class VelocityComponent: Component, Pool.Poolable {

    var x: Float = 0f
    var y: Float = 0f

    override fun reset(){
        this.y = 0f
        this.x = 0f
    }
}