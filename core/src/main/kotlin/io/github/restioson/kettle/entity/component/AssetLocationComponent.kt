package io.github.restioson.kettle.entity.component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.utils.Pool

/**
 * Class that describes an asset to be loaded into memory client side
 */
// TODO equals()
class AssetLocationComponent() : Component, Pool.Poolable {

    var descriptor: AssetDescriptor<*>? = null
    var fulfilled = false

    constructor(descriptor: AssetDescriptor<*>) : this() {
        this.descriptor = descriptor
    }

    override fun reset() {
        this.descriptor = null
        this.fulfilled = false
    }

}