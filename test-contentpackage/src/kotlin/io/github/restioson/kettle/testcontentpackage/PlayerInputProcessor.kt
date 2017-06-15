package io.github.restioson.kettle.testcontentpackage

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.physics.box2d.Body
import io.github.restioson.kettle.entity.ComponentMappers
import io.github.restioson.kettle.entity.component.BodyComponent

class PlayerInputProcessor(player: Entity) : InputAdapter() {

    private var playerBody: Body

    init {
        if (player.getComponent(BodyComponent::class.java) == null) {
            throw IllegalArgumentException("player must have BodyComponent")
        }

        this.playerBody = ComponentMappers.BODY[player].body!!
    }

    override fun keyDown(keycode: Int): Boolean {

        when (keycode) {
            Input.Keys.W ->
                this.playerBody.applyLinearImpulse(0f, 200f * this.playerBody.mass,
                        this.playerBody.position.x, this.playerBody.position.y, true)
            Input.Keys.S ->
                this.playerBody.applyLinearImpulse(0f, -200f * this.playerBody.mass,
                        this.playerBody.position.x, this.playerBody.position.y, true)
            Input.Keys.A ->
                this.playerBody.applyLinearImpulse(-200f * this.playerBody.mass, 0f,
                        this.playerBody.position.x, this.playerBody.position.y, true)
            Input.Keys.D ->
                this.playerBody.applyLinearImpulse(200f * this.playerBody.mass, 0f,
                        this.playerBody.position.x, this.playerBody.position.y, true)
        }

        return true
    }
}