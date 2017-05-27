package io.github.restioson.kettle.api.entity

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.utils.Pool

/**
 * Component for box2d bodies to enable physics and position
 */
class Box2DComponent : Component, Pool.Poolable {

    var body: Body? = null
    var fixture: Fixture? = null

    constructor(body: Body, fixture: Fixture) {
        this.body = body
        this.fixture = fixture
    }

    constructor(body: Body, fixtureDef: FixtureDef) {
        this.body = body
        this.fixture = body.createFixture(fixtureDef)
    }

    constructor(body: Body, shape: PolygonShape, density: Float) {
        this.body = body
        this.fixture = body.createFixture(shape, density)
    }

    constructor(body: Body, width: Float, height: Float, density: Float) {
        this.body = body

        val shape = PolygonShape()
        shape.setAsBox(width / 2, height / 2)

        this.fixture = body.createFixture(shape, density)
        shape.dispose()
    }

    override fun reset() {
        this.body = null
    }
}