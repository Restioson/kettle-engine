package io.github.restioson.kettle.entity.component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.utils.Pool

/**
 * Component for sound
 */
class SoundComponent() : Component, Pool.Poolable {

    var sound: Sound? = null

    constructor(sound: Sound) : this() {
        this.sound = sound
    }

    override fun reset() {
        this.sound = null
    }

}