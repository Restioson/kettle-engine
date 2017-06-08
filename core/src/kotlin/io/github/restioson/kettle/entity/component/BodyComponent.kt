package io.github.restioson.kettle.entity.component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Pool

/**
 * Component for box2d bodies to enable physics and position
 *
 * Use BodyComponentBuilder to instantiate
 */

// TODO serialise?
class BodyComponent(fixtureDefSize: Int) : Component, Pool.Poolable {

    lateinit var bodyDef: BodyDef
    var body: Body? = null
    val fixtureDefs = Array<FixtureDef>(false, fixtureDefSize)

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

    override fun reset() {
        this.body = null
    }
}