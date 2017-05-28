package io.github.restioson.kettle.api.entity.component

/**
 * Component for box2d bodies to enable physics and position
 */
class Box2DComponent : com.badlogic.ashley.core.Component, com.badlogic.gdx.utils.Pool.Poolable {

    var body: com.badlogic.gdx.physics.box2d.Body? = null
    var fixture: com.badlogic.gdx.physics.box2d.Fixture? = null

    constructor(body: com.badlogic.gdx.physics.box2d.Body, fixture: com.badlogic.gdx.physics.box2d.Fixture) {
        this.body = body
        this.fixture = fixture
    }

    constructor(body: com.badlogic.gdx.physics.box2d.Body, fixtureDef: com.badlogic.gdx.physics.box2d.FixtureDef) {
        this.body = body
        this.fixture = body.createFixture(fixtureDef)
    }

    constructor(body: com.badlogic.gdx.physics.box2d.Body, shape: com.badlogic.gdx.physics.box2d.PolygonShape, density: Float) {
        this.body = body
        this.fixture = body.createFixture(shape, density)
    }

    constructor(body: com.badlogic.gdx.physics.box2d.Body, width: Float, height: Float, density: Float) {
        this.body = body

        val shape = com.badlogic.gdx.physics.box2d.PolygonShape()
        shape.setAsBox(width / 2, height / 2)

        this.fixture = body.createFixture(shape, density)
        shape.dispose()
    }

    override fun reset() {
        this.body = null
    }
}