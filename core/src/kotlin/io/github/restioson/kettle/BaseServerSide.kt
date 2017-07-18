package io.github.restioson.kettle

import io.github.restioson.kettle.api.Level
import io.github.restioson.kettle.api.ServerSidePackage

/**
 * Base implementation of ServerSidePackage
 */
abstract class BaseServerSide : ServerSidePackage {

    abstract override var kLevel: Level

    override fun create() {
        this.kLevel.create()
    }

    override fun step(delta: Float) {
        this.kLevel.step(delta)
    }

    override fun pause() {
        this.kLevel.pause()
    }

    override fun resume() {
        this.kLevel.resume()
    }

    override fun dispose() {
    }


}
