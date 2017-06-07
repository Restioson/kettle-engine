package io.github.restioson.kettle.entity.component

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.utils.Array
import io.github.restioson.kettle.api.physics.Units

/**
 * Builder for [Box2DComponent]s
 *
 * Allows serialization of the component, due to storing definitions (e.g BodyDefs)
 */
class Box2DComponentBuilder(jointDefSize: Int = 2, fixtureDefSize: Int = 1) {

    // TODO slim down this stuff
    var bodyDef: BodyDef? = null
    val joints = Array<JointDef>(false, jointDefSize) // TODO probably not needed -- Joint has enough data to reconstruct def
    val fixtures = Array<FixtureDef>(false, fixtureDefSize)
    private val shapesToDispose = Array<Shape>(false, fixtureDefSize)

    fun withBody(definition: BodyDef): Box2DComponentBuilder {
        this.bodyDef = definition
        return this
    }

    // TODO add more stuff to this
    fun withBody(
            type: BodyDef.BodyType,
            position: Vector2 = Vector2(0f, 0f),
            angle: Float = 0f
    ): Box2DComponentBuilder {

        this.bodyDef = BodyDef()

        this.bodyDef?.type = type
        this.bodyDef?.position?.set(position)
        this.bodyDef?.angle = angle

        return this

    }

    fun withJoint(definition: JointDef): Box2DComponentBuilder {
        this.joints.add(definition)
        return this
    }

    fun withJoints(definitions: Array<JointDef>): Box2DComponentBuilder {
        this.joints.addAll(definitions)
        return this
    }

    fun withJoints(vararg definitions: JointDef): Box2DComponentBuilder {
        this.joints.addAll(*definitions)
        return this
    }

    fun withFixture(fixture: FixtureDef): Box2DComponentBuilder {
        this.fixtures.add(fixture)

        return this
    }

    fun withBoxFixture(width: Float, height: Float, isPixels: Boolean = false, density: Float = 1f): Box2DComponentBuilder {

        val shape = PolygonShape()

        if (!isPixels) {
            shape.setAsBox(width / 2, height / 2)
        } else {
            shape.setAsBox(width * Units.METERS_TO_PIXELS / 2, height * Units.METERS_TO_PIXELS / 2)
        }

        this.withFixture(shape, density)
        this.shapesToDispose.add(shape)

        return this
    }

    fun withFixture(density: Float = 1f, vararg points: Float): Box2DComponentBuilder {

        val shape = PolygonShape()
        shape.set(points)

        this.withFixture(shape, density)
        this.shapesToDispose.add(shape)

        return this
    }

    fun withFixture(shape: Shape, density: Float = 1f): Box2DComponentBuilder {

        val fixture = FixtureDef()
        fixture.shape = shape
        fixture.density = density

        this.fixtures.add(fixture)
        return this
    }

    fun withFixtures(fixtures: Array<FixtureDef>): Box2DComponentBuilder {
        this.fixtures.addAll(fixtures)
        return this
    }

    fun withFixtures(vararg fixtures: FixtureDef): Box2DComponentBuilder {
        this.fixtures.addAll(*fixtures)
        return this
    }


    fun build(world: World): Box2DComponent {

        val component = Box2DComponent(this.joints.size, this.fixtures.size)

        component.bodyDef = this.bodyDef ?: throw IllegalArgumentException("Builder requires BodyDefinition!")
        component.body = world.createBody(this.bodyDef)

        if (this.joints.size > 0) { // Prevent iterator allocation

            for (joint in this.joints) {
                world.createJoint(joint)
            }

            component.jointDefs.addAll(this.joints)

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

