package io.github.restioson.kettle.entity.component

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.utils.Array
import io.github.restioson.kettle.api.physics.Units

/**
 * Builder for [BodyComponent]s
 *
 * Allows serialization of the component, due to storing definitions (e.g BodyDefs)
 */
class BodyComponentBuilder(jointDefSize: Int = 2, fixtureDefSize: Int = 1) {

    var bodyDef: BodyDef? = null
    private val joints = Array<JointDef>(false, jointDefSize)
    private val fixtures = Array<FixtureDef>(false, fixtureDefSize)
    private val shapesToDispose = Array<Shape>(false, fixtureDefSize)

    fun withBody(definition: BodyDef): BodyComponentBuilder {
        this.bodyDef = definition
        return this
    }

    // TODO add more stuff to this
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

    fun withJoint(definition: JointDef): BodyComponentBuilder {
        this.joints.add(definition)
        return this
    }

    fun withJoints(definitions: Array<JointDef>): BodyComponentBuilder {
        this.joints.addAll(definitions)
        return this
    }

    fun withJoints(vararg definitions: JointDef): BodyComponentBuilder {
        this.joints.addAll(*definitions)
        return this
    }

    fun withFixture(fixture: FixtureDef): BodyComponentBuilder {
        this.fixtures.add(fixture)

        return this
    }

    fun withBoxFixture(width: Float, height: Float, isPixels: Boolean = false, density: Float = 1f): BodyComponentBuilder {

        val shape = PolygonShape()

        if (!isPixels) {
            shape.setAsBox(width / 2, height / 2)
        } else {
            shape.setAsBox(width * Units.PIXELS_IN_METERS / 2, height * Units.PIXELS_IN_METERS / 2)
        }

        this.withFixture(shape, density)
        this.shapesToDispose.add(shape)

        return this
    }

    fun withFixture(density: Float = 1f, vararg points: Float): BodyComponentBuilder {

        val shape = PolygonShape()
        shape.set(points)

        this.withFixture(shape, density)
        this.shapesToDispose.add(shape)

        return this
    }

    fun withFixture(shape: Shape, density: Float = 1f): BodyComponentBuilder {

        val fixture = FixtureDef()
        fixture.shape = shape
        fixture.density = density

        this.fixtures.add(fixture)
        return this
    }

    fun withFixtures(fixtures: Array<FixtureDef>): BodyComponentBuilder {
        this.fixtures.addAll(fixtures)
        return this
    }

    fun withFixtures(vararg fixtures: FixtureDef): BodyComponentBuilder {
        this.fixtures.addAll(*fixtures)
        return this
    }


    fun build(engine: PooledEngine, world: World): BodyComponent {

        val component = engine.createComponent(BodyComponent::class.java)

        component.bodyDef = this.bodyDef ?: throw IllegalArgumentException("Builder requires BodyDefinition!")
        component.body = world.createBody(this.bodyDef)

        if (this.joints.size > 0) { // Prevent iterator allocation

            for (joint in this.joints) {
                world.createJoint(joint)
            }

        }

        if (this.fixtures.size > 0) {
            for (fixture in this.fixtures) {
                component.body?.createFixture(fixture)
            }

            component.fixtureDefs.addAll(this.fixtures)
        }

        if (this.shapesToDispose.size > 0) {
            for (shape in this.shapesToDispose) {
                shape.dispose()
            }
        }

        return component

    }

}

