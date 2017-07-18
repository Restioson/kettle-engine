package io.github.restioson.kettle.entity.system

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import io.github.restioson.kettle.api.physics.Units
import io.github.restioson.kettle.entity.ComponentMappers

/**
 * Renders entities with a spritebatch
 */
open class SpriteRenderer(w: Float, h: Float) : Renderer() {

    override final val camera: Camera = OrthographicCamera(w, h)
    override val viewport: Viewport = FitViewport(w, h, camera)

    override val queuedEntities: Array<Entity> = Array()

    val batch: SpriteBatch = SpriteBatch()

    override fun update(delta: Float) {
        super.update(delta)

        this.batch.projectionMatrix = camera.combined
        this.batch.begin()

        Gdx.gl.glClearColor(0f, 0f, 1f, 1f)
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        for (entity in this.queuedEntities) {

            // Atomic for concurrency
            // TODO is this fast?
            val body = ComponentMappers.BODY[entity].body
            val texture = ComponentMappers.GRAPHICS[entity].texture

            if (body != null && texture != null) {
                this.batch.draw(texture, // TODO TextureRegion
                        body.position.x * Units.METERS_IN_PIXELS,
                        body.position.y * Units.METERS_IN_PIXELS
                )
            }
        }

        this.batch.end()
        this.queuedEntities.clear()
    }

}