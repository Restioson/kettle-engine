package io.github.restioson.kettle.api.handler

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Array
import io.github.restioson.kettle.api.entity.ComponentMappers
import io.github.restioson.kettle.api.physics.Units

/**
 * Renders entities as sprites
 */
class SpriteRenderer(w: Float, h: Float) : Renderer() {

    override val camera: Camera = OrthographicCamera(w, h)
    override val queuedEntities: Array<Entity> = Array()
    val batch: SpriteBatch = SpriteBatch()

    override fun update(delta: Float) {
        super.update(delta)

        this.batch.projectionMatrix = camera.combined
        this.batch.begin()
        for (entity in this.queuedEntities) {
            val pos = ComponentMappers.BOX2D_MAPPER[entity].body!!.position
            this.batch.draw(ComponentMappers.GRAPHICS_MAPPER[entity].texture!!,
                    pos.x * Units.METERS_TO_PIXELS,
                    pos.y * Units.METERS_TO_PIXELS
            )
        }

        this.batch.end()
        this.queuedEntities.clear()
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        this.queuedEntities.add(entity)
    }

}