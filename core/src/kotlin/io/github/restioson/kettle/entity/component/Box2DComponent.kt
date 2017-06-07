package io.github.restioson.kettle.entity.component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.JointDef
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Pool

/**
 * Component for box2d bodies to enable physics and position
 *
 * Use Box2DComponentBuilder to instantiate
 */

// TODO serialise?
class Box2DComponent(jointDefSize: Int, fixtureDefSize: Int) : Component, Pool.Poolable {

    lateinit var bodyDef: BodyDef
    var body: Body? = null
    val jointDefs = Array<JointDef>(false, jointDefSize)
    val fixtureDefs = Array<FixtureDef>(false, fixtureDefSize)

    override fun reset() {
        this.body = null
    }
}