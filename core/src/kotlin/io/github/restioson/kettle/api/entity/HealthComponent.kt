package io.github.restioson.kettle.api.entity

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool

/**
 * Component for health
 *
 */
class HealthComponent: Component, Pool.Poolable {
    var health: Int = 0

    override fun reset() {
        health = 0
    }
}