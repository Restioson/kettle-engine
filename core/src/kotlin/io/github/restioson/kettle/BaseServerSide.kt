package io.github.restioson.kettle

import io.github.restioson.kettle.api.Level
import io.github.restioson.kettle.api.ServerSidePackage

/**
 * Base implementation of ServerSidePackage
 */
abstract class BaseServerSide : ServerSidePackage {

    abstract override var level: Level

    override fun create() {
        this.level.create()
    }

    override fun step(delta: Float) {
        this.level.step(delta)
    }

    override fun pause() {
        this.level.pause()
    }

    override fun resume() {
        this.level.resume()
    }

    override fun dispose() {
    }


}
