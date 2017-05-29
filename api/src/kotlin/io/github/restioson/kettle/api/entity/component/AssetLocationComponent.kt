package io.github.restioson.kettle.api.entity.component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.utils.Disposable

class AssetLocationComponent<A : Any>(descriptor: AssetDescriptor<A>) : Component, Disposable {

    var descriptor: AssetDescriptor<A>? = descriptor

    override fun dispose() {
        this.descriptor = null
    }

}