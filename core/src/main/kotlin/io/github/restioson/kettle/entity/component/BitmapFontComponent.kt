package io.github.restioson.kettle.entity.component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.utils.Pool

/**
 * Component for bitmap fonts
 */
class BitmapFontComponent() : Component, Pool.Poolable {

    var font: BitmapFont? = null

    constructor(font: BitmapFont) : this() {
        this.font = font
    }

    override fun reset() {
        this.font = null
    }
}