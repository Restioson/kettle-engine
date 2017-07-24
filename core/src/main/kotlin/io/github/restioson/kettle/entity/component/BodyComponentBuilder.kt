package io.github.restioson.kettle.entity.component

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.JointDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.Shape
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.Array

/**
 * Builder for [BodyComponent]s
 *
 * Allows serialization of the component, due to storing definitions (e.g BodyDefs)
 */
class BodyComponentBuilder(jointDefSize: Int = 2, fixtureDefSize: Int = 1) {

    /**
     * Class representing a joint between a body definition and the builder's body definition
     */
    class BodyDefJoint(val otherBodyDef: BodyDef, val type: JointDef.JointType, val collideConnected: Boolean = false)

    /**
     * Class representing a joint between a body and the builders body definition
     */
    class BodyJoint(val otherBody: Body, val type: JointDef.JointType, val collideConnected: Boolean = false)

    var bodyDef: BodyDef? = null
    val bodyDefJoints = Array<BodyDefJoint>(false, jointDefSize)
    val bodyJoints = Array<BodyJoint>(false, jointDefSize)
    val fixtures = Array<FixtureDef>(false, fixtureDefSize)
    val shapesToDispose = Array<Shape>(false, fixtureDefSize)

    /**
     * Sets the body definition
     *
     * @param definition the definition
     *
     * @return builder
     */
    fun withBody(definition: BodyDef): BodyComponentBuilder {
        this.bodyDef = definition
        return this
    }

    /**
     * Sets the body definition to be a definition with the following parameters:
     *
     * @param type type of the body
     * @param position the position of the body, defaultedly 0, 0
     * @param angle the angle of the body, defaultedly 0, 0
     *
     * @return builder
     */
    fun withBody(
            type: BodyDef.BodyType,
            position: Vector2 = Vector2(0f, 0f),
            angle: Float = 0f
    ): BodyComponentBuilder {

        this.bodyDef = BodyDef()

        this.bodyDef?.type = type
        this.bodyDef?.position?.set(position)
        this.bodyDef?.angle = angle

        return this

    }

    /**
     * Adds a [BodyDefJoint] to the builder
     *
     * @param definition the joint definition
     *
     * @return builder
     */
    fun withJoint(definition: BodyDefJoint): BodyComponentBuilder {
        this.bodyDefJoints.add(definition)
        return this
    }

    /**
     * Adds a [BodyDefJoint] with the following params:
     *
     * @param type type of the joint
     * @param otherBody the other body definition
     * @param collideConnected collideConnected whether all connected bodies receive collision events, defaultedly false
     *
     * @return builder
     */
    fun withJoint(type: JointDef.JointType, otherBody: BodyDef, collideConnected: Boolean = false): BodyComponentBuilder {

        this.bodyDefJoints.add(BodyDefJoint(otherBody, type, collideConnected))

        return this
    }

    /**
     * Adds an array of joint definitions
     *
     * @param definitions the definitions
     */
    fun withJoints(definitions: kotlin.Array<BodyDefJoint>): BodyComponentBuilder {
        this.bodyDefJoints.addAll(*definitions)
        return this
    }

    /**
     * Adds a [BodyJoint] to the builder
     *
     * @param definition the joint definition
     *
     * @return builder
     */
    fun withJoint(definition: BodyJoint): BodyComponentBuilder {
        this.bodyJoints.add(definition)
        return this
    }

    /**
     * Adds a [BodyDefJoint] with the following params:
     *
     * @param type type of the joint
     * @param otherBody the other body definition
     * @param collideConnected collideConnected whether all connected bodies receive collision events, defaultedly false
     *
     * @return builder
     */
    fun withJoint(type: JointDef.JointType, otherBody: Body, collideConnected: Boolean = false): BodyComponentBuilder {

        this.bodyJoints.add(BodyJoint(otherBody, type, collideConnected))

        return this
    }

    /**
     * Adds an array of joint definitions
     *
     * @param definitions the definitions
     */
    fun withJoints(definitions: kotlin.Array<BodyJoint>): BodyComponentBuilder {
        this.bodyJoints.addAll(*definitions)
        return this
    }

    /**
     * Adds a fixture
     *
     * @param definition fixture definition
     *
     * @return builder
     */
    fun withFixture(definition: FixtureDef): BodyComponentBuilder {
        this.fixtures.add(definition)

        return this
    }

    /**
     * Adds a fixture with the following parameters:
     *
     * @param density density of the fixture, defaultedly 1f
     * @param points the points of the shape for the fixture
     *
     * @return builder
     */
    fun withFixture(density: Float = 1f, points: kotlin.Array<Vector2>): BodyComponentBuilder {

        val shape = PolygonShape()
        shape.set(points)

        this.withFixture(shape, density)
        this.shapesToDispose.add(shape)

        return this
    }

    fun withFixture(density: Float = 1f, points: FloatArray): BodyComponentBuilder {
        val shape = PolygonShape()
        shape.set(points)

        this.withFixture(shape, density)
        this.shapesToDispose.add(shape)

        return this
    }

    /**
     * Adds a fixture with the following parameters:
     *
     * @param shape the shape for the fixture
     * @param density the density of the fixture, defaultedly 1f
     */
    fun withFixture(shape: Shape, density: Float = 1f): BodyComponentBuilder {

        val fixture = FixtureDef()
        fixture.shape = shape
        fixture.density = density

        this.fixtures.add(fixture)
        return this
    }

    /**
     * Adds a array of fixtures
     *
     * @param fixtures fixtures to add
     *
     * @return builder
     */
    fun withFixtures(fixtures: kotlin.Array<FixtureDef>): BodyComponentBuilder {
        this.fixtures.addAll(*fixtures)
        return this
    }

    /**
     * Adds a box fixture with the following parameters:
     *
     * @param width the width of the box's shape (passed directly to setAsBox)
     * @param height the height of the box's shape (passed directly to setAsBox)
     * @param density the density of the box, defaultedly 1f
     *
     * @return builder
     */
    fun withBoxFixture(width: Float, height: Float, density: Float = 1f): BodyComponentBuilder {

        val shape = PolygonShape()

        shape.setAsBox(width, height)

        this.withFixture(shape, density)
        this.shapesToDispose.add(shape)

        return this
    }

    /**
     * Builds the builder into a [BodyComponent]
     *
     * @param engine entity engine to create component with
     * @param world Box2D world to create body with
     *
     * @return BodyComponent built
     */
    fun build(engine: PooledEngine, world: World): BodyComponent {

        val component = engine.createComponent(BodyComponent::class.java)

        component.bodyDef = this.bodyDef ?: throw IllegalArgumentException("Builder requires BodyDefinition")
        component.body = world.createBody(this.bodyDef)

        for (joint in this.bodyDefJoints) {
            world.createJoint(JointDef().apply {
                type = joint.type
                collideConnected = joint.collideConnected
                bodyA = component.body
                bodyB = world.createBody(joint.otherBodyDef)
            })
        }

        for (joint in this.bodyJoints) {
            world.createJoint(JointDef().apply {
                type = joint.type
                collideConnected = joint.collideConnected
                bodyA = component.body
                bodyB = joint.otherBody
            })
        }

        for (fixture in this.fixtures) {
            component.body?.createFixture(fixture)
            component.fixtureDefs.add(fixture)
        }

        for (shape in this.shapesToDispose) {
            shape.dispose()
        }

        return component

    }

}

