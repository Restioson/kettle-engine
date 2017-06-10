package io.github.restioson.kettle.entity.component

import com.badlogic.gdx.physics.box2d.*
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

// TODO serialise?
class BodyComponent : SerializableComponent, Pool.Poolable {

    override var sync = true
    override var save = true

    var bodyDef: BodyDef? = null
    var body: Body? = null
    val fixtureDefs = Array<FixtureDef>(false, 1)

    fun createFixture(fixtureDef: FixtureDef) {
        this.body?.createFixture(fixtureDef)
        this.fixtureDefs.add(fixtureDef)
    }

    fun createFixture(shape: Shape, density: Float) {
        this.body?.createFixture(shape, density)
        this.fixtureDefs.add(FixtureDef().apply {
            this.shape = shape
            this.density = density
        })
    }

    fun createBoxFixture(width: Float, height: Float, density: Float) {
        val shape = PolygonShape()
        shape.setAsBox(width, height)
        this.createFixture(shape, density)
    }

    override fun serialize(buffer: KettleBuffer, extraList: ArrayList<KettleSerializable>) {

        // Make sure that bodyDef and body are filled


    }

    override fun deserialize(buffer: KettleBuffer) {
    }

    override fun reset() {
        this.body = null
    }
}