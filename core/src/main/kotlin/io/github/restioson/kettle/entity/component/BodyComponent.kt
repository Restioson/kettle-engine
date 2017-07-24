package io.github.restioson.kettle.entity.component

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Pool
import io.github.restioson.kettle.api.serialization.KettleBuffer
import io.github.restioson.kettle.api.serialization.KettleSerializable
import io.github.restioson.kettle.api.serialization.SerializableComponent

/**
 * Component for box2d bodies to enable physics and position
 *
 * Use BodyComponentBuilder to instantiate
 */

class BodyComponent : SerializableComponent, Pool.Poolable {

    override var sync = true
    override var save = true

    var bodyDef: BodyDef? = null
    var body: Body? = null
    var fixtureDefs = Array<FixtureDef>(false, 1)

    override fun serialize(buffer: KettleBuffer, extraList: ArrayList<KettleSerializable>) {
    }

    override fun deserialize(buffer: KettleBuffer) {
    }

    override fun reset() {
        this.sync = true
        this.save = true
        this.bodyDef = null
        this.body = null
        this.fixtureDefs = Array<FixtureDef>(false, 1)
    }
}