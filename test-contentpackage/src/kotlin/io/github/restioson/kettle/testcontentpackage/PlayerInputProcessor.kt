package io.github.restioson.kettle.testcontentpackage

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.physics.box2d.Body
import io.github.restioson.kettle.entity.ComponentMappers

class PlayerInputProcessor(player: Entity) : InputAdapter() {

    private var playerBody: Body = ComponentMappers.BODY[player].body ?:
            throw IllegalArgumentException("player must have BodyComponent")

    override fun keyDown(keycode: Int): Boolean {

        when (keycode) {
            Input.Keys.W ->
                this.playerBody.applyLinearImpulse(0f, 20f * this.playerBody.mass,
                        this.playerBody.position.x, this.playerBody.position.y, true)
            Input.Keys.S ->
                this.playerBody.applyLinearImpulse(0f, -20f * this.playerBody.mass,
                        this.playerBody.position.x, this.playerBody.position.y, true)
            Input.Keys.A ->
                this.playerBody.applyLinearImpulse(-20f * this.playerBody.mass, 0f,
                        this.playerBody.position.x, this.playerBody.position.y, true)
            Input.Keys.D ->
                this.playerBody.applyLinearImpulse(20f * this.playerBody.mass, 0f,
                        this.playerBody.position.x, this.playerBody.position.y, true)

            else -> return false
        }

        return true
    }
}