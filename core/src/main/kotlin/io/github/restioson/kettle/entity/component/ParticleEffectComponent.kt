package io.github.restioson.kettle.entity.component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect
import com.badlogic.gdx.utils.Pool

/**
 * Component for ParticleEffects
 */
class ParticleEffectComponent() : Component, Pool.Poolable {

    var effect: ParticleEffect? = null

    constructor(effect: ParticleEffect) : this() {
        this.effect = effect
    }

    override fun reset() {
        this.effect = null
    }
}