package io.github.restioson.kettle.entity.component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool

/**
 * Component for health
 *
 */
class HealthComponent() : Component, Pool.Poolable {

    var health: Int = 0

    constructor(health: Int) : this() {
        this.health = health
    }

    override fun reset() {
        health = 0
    }
}