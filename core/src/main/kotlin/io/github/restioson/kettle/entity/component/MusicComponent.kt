package io.github.restioson.kettle.entity.component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.utils.Pool

/**
 * Component for music/streamed audio
 */
class MusicComponent() : Component, Pool.Poolable {

    var music: Music? = null

    constructor (music: Music) : this() {
        this.music = music
    }

    override fun reset() {
        this.music = null
    }

}