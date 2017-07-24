package io.github.restioson.kettle.entity.component

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.JointDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

class BodyComponentBuilderTest : StringSpec() {
    init {
        "withBody(definition: BodyDef): BodyComponentBuilder" {
            val builder = BodyComponentBuilder()

            val def = BodyDef()
            def.position.set(Vector2(102320f, 100f)) // Make different from default

            val ret = builder.withBody(def)

            builder.bodyDef shouldBe def
            ret shouldBe builder
        }

        "withBody(type: BodyDef.BodyType, position: Vector2 = Vector2(0, 0), angle: Float = 0f): BodyComponentBuilder" {
            val builder = BodyComponentBuilder()

            val type = BodyDef.BodyType.KinematicBody
            val position = Vector2(102320f, 100f)
            val angle = 5.7f

            val ret = builder.withBody(type, position, angle)

            val expectedDef = BodyDef()
            expectedDef.type = type
            expectedDef.position.set(position)
            expectedDef.angle = angle

            builder.bodyDef.apply {
                type shouldBe expectedDef.type
                position shouldEqual expectedDef.position
                angle shouldEqual expectedDef.angle
            }

            ret shouldBe builder
        }

        "withJoint(definition: BodyDefJoint): BodyComponentBuilder" {
            val builder = BodyComponentBuilder()
            val def = BodyComponentBuilder.BodyDefJoint(BodyDef(), JointDef.JointType.FrictionJoint, true)

            val ret = builder.withJoint(def)

            builder.bodyDefJoints.contains(def) shouldBe true
            ret shouldBe builder
        }

        "withJoint(type: JointDef.JointType, otherBody: BodyDef, collideConnected: Boolean = false): BodyComponentBuilder" {
            val builder = BodyComponentBuilder()
            val expectedDef = BodyComponentBuilder.BodyDefJoint(BodyDef(), JointDef.JointType.GearJoint, true)

            val ret = builder.withJoint(expectedDef.type, expectedDef.otherBodyDef, expectedDef.collideConnected)

            builder.bodyDefJoints[0].apply {
                type shouldBe expectedDef.type
                otherBodyDef shouldBe expectedDef.otherBodyDef
                collideConnected shouldBe true
            }

            ret shouldBe builder
        }

        "withJoints(definitions: kotlin.Array<BodyDefJoint>): BodyComponentBuilder" {
            val builder = BodyComponentBuilder()
            val def = BodyComponentBuilder.BodyDefJoint(BodyDef(), JointDef.JointType.FrictionJoint, true)

            val ret = builder.withJoints(arrayOf(def))

            builder.bodyDefJoints.contains(def) shouldBe true
            ret shouldBe builder

        }

        "withJoint(definition: BodyJoint): BodyComponentBuilder " {
            val builder = BodyComponentBuilder()
            val world = World(Vector2(0f, 0f), true)
            val def = BodyComponentBuilder.BodyJoint(world.createBody(BodyDef()), JointDef.JointType.FrictionJoint, true)

            val ret = builder.withJoint(def)

            builder.bodyJoints.contains(def) shouldBe true
            ret shouldBe builder
        }

        "withJoint(type: JointDef.JointType, otherBody: Body, collideConnected: Boolean = false): BodyComponentBuilder" {
            val builder = BodyComponentBuilder()
            val world = World(Vector2(0f, 0f), true)
            val expectedDef = BodyComponentBuilder.BodyJoint(world.createBody(BodyDef()), JointDef.JointType.GearJoint, true)

            val ret = builder.withJoint(expectedDef.type, expectedDef.otherBody, expectedDef.collideConnected)

            builder.bodyJoints[0].apply {
                type shouldBe expectedDef.type
                otherBody shouldBe expectedDef.otherBody
                collideConnected shouldBe true
            }

            ret shouldBe builder
        }

        "withJoints(definitions: kotlin.Array<BodyJoint>): BodyComponentBuilder" {
            val builder = BodyComponentBuilder()
            val world = World(Vector2(0f, 0f), true)
            val def = BodyComponentBuilder.BodyJoint(world.createBody(BodyDef()), JointDef.JointType.FrictionJoint, true)

            val ret = builder.withJoints(arrayOf(def))

            builder.bodyJoints.contains(def) shouldBe true
            ret shouldBe builder

        }

        "withFixture(definition: FixtureDef): BodyComponentBuilder" {
            val builder = BodyComponentBuilder()

            val def = FixtureDef()
            def.density = 0.4f // Make different from default

            val ret = builder.withFixture(def)

            builder.fixtures.contains(def) shouldBe true
            ret shouldBe builder
        }

        "withFixture(density: Float = 1f, points: kotlin.Array<Vector2>): BodyComponentBuilder" {
            val builder = BodyComponentBuilder()

            val expectedDef = FixtureDef()
            expectedDef.density = 0.5f // Make different from default

            val points = arrayOf(Vector2(0f, 0f), Vector2(0.5f, 0.1f), Vector2(0f, 1f))
            val expectedShape = PolygonShape().apply { set(points) }
            expectedDef.shape = expectedShape // Make different from default

            val ret = builder.withFixture(expectedDef.density, points)

            builder.fixtures[0].apply {
                density shouldEqual expectedDef.density

                val polygonShape = (shape as PolygonShape)
                for (index in 0..polygonShape.vertexCount - 1) {
                    val vector = Vector2(0f, 0f)
                    val expectedVector = Vector2(0f, 0f)

                    polygonShape.getVertex(index, vector)
                    expectedShape.getVertex(index, expectedVector)

                    vector shouldBe expectedVector
                }

            }

            ret shouldBe builder

        }

        "withFixture(density: Float = 1f, points: FloatArray): BodyComponentBuilder" {
            val builder = BodyComponentBuilder()

            val expectedDef = FixtureDef()
            expectedDef.density = 0.5f // Make different from default

            val points = arrayOf(0f, 0f, 0.5f, 0.1f, 0f, 1f).toFloatArray()
            val expectedShape = PolygonShape().apply { set(points) }
            expectedDef.shape = expectedShape // Make different from default

            val ret = builder.withFixture(expectedDef.density, points)

            builder.fixtures[0].apply {
                density shouldEqual expectedDef.density

                val polygonShape = (shape as PolygonShape)
                for (index in 0..polygonShape.vertexCount - 1) {
                    val vector = Vector2(0f, 0f)
                    val expectedVector = Vector2(0f, 0f)

                    polygonShape.getVertex(index, vector)
                    expectedShape.getVertex(index, expectedVector)

                    vector shouldBe expectedVector
                }

            }

            ret shouldBe builder
        }

        "withFixture(shape: Shape, density: Float = 1f)" {
            val builder = BodyComponentBuilder()

            val expectedShape = PolygonShape().apply { set(arrayOf(Vector2(0f, 0f), Vector2(0.5f, 1f), Vector2(1f, 0f))) }

            val ret = builder.withFixture(expectedShape, 0.5f)

            builder.fixtures[0].apply {
                shape shouldBe expectedShape
                density shouldEqual 0.5f
            }

            ret shouldBe builder
        }

        "withFixtures(fixtures: kotlin.Array<FixtureDef>): BodyComponentBuilder" {
            val builder = BodyComponentBuilder()

            val expectedShape = PolygonShape().apply { set(arrayOf(Vector2(0f, 0f), Vector2(0.5f, 1f), Vector2(1f, 0f))) }

            val ret = builder.withFixture(expectedShape, 0.5f)

            builder.fixtures[0].apply {
                shape shouldBe expectedShape
                density shouldEqual 0.5f
            }

            ret shouldBe builder
        }

        "withBoxFixture(width: Float, height: Float, density: Float = 1f): BodyComponentBuilder" {
            val builder = BodyComponentBuilder()
            val expectedShape = PolygonShape().apply { setAsBox(100f, 100f) }
            val expectedDef = FixtureDef()
            expectedDef.density = 0.5f

            val ret = builder.withBoxFixture(100f, 100f, 0.5f)

            builder.fixtures[0].apply {
                density shouldEqual expectedDef.density

                val polygonShape = (shape as PolygonShape)
                for (index in 0..polygonShape.vertexCount - 1) {
                    val vector = Vector2(0f, 0f)
                    val expectedVector = Vector2(0f, 0f)

                    polygonShape.getVertex(index, vector)
                    expectedShape.getVertex(index, expectedVector)

                    vector shouldBe expectedVector
                }
            }

            ret shouldBe builder
        }
    }
}
