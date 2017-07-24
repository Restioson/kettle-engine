package io.github.restioson.kettle.entity.component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.utils.Pool

/**
 * Component for Pixmaps
 */
class PixmapComponent() : Component, Pool.Poolable {

    var pixmap: Pixmap? = null

    constructor(pixmap: Pixmap) : this() {
        this.pixmap = pixmap
    }

    override fun reset() {
    }

}