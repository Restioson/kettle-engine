package io.github.restioson.kettle.api.entity.component

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape

/**
 * Component for box2d bodies to enable physics and position
 *
 * Currently only has constructors for boxes
 */

// TODO add constructors for generic polygons
class Box2DComponent() : com.badlogic.ashley.core.Component, com.badlogic.gdx.utils.Pool.Poolable {

    var body: com.badlogic.gdx.physics.box2d.Body? = null

    constructor(body: Body) : this() {
        this.body = body
    }

    constructor(body: Body, fixtureDef: FixtureDef) : this() {
        this.body = body
        body.createFixture(fixtureDef)
    }

    constructor(body: Body, shape: PolygonShape, density: Float) : this() {
        this.body = body
        body.createFixture(shape, density)
    }

    constructor(body: Body, width: Float, height: Float, density: Float) : this() {
        this.body = body

        val shape = com.badlogic.gdx.physics.box2d.PolygonShape()
        shape.setAsBox(width / 2, height / 2)

        body.createFixture(shape, density)
        shape.dispose()
    }

    override fun reset() {
        this.body = null
    }
}