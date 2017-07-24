package io.github.restioson.kettle.entity.component

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.Array
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec
import java.util.Arrays

class BodyComponentTest : StringSpec() {

    init {
        "reset()" {
            val world = World(Vector2(0f, 0f), true)
            val body = BodyComponent()

            body.body = world.createBody(BodyDef())
            body.fixtureDefs.add(FixtureDef())
            body.bodyDef = BodyDef()
            body.save = false
            body.sync = false

            body.reset()

            body.body shouldBe null
            Arrays.equals(body.fixtureDefs.items, Array<FixtureDef>(false, 1).items) shouldBe true
            body.save shouldBe true
            body.sync shouldBe true
        }
    }

}