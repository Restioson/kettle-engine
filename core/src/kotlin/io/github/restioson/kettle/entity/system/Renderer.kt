package io.github.restioson.kettle.entity.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.viewport.Viewport
import io.github.restioson.kettle.entity.component.Box2DComponent
import io.github.restioson.kettle.entity.component.GraphicsComponent

/**
 * Renderer abstract class
 */
abstract class Renderer : IteratingSystem(Family.all(GraphicsComponent::class.java, Box2DComponent::class.java).get()) {

    /**
     * An instance of Camera which represents the camera used in drawing
     */
    abstract val camera: Camera

    /**
     * Viewport to view the world with
     */
    abstract val viewport: Viewport

    /**
     * A queue of entities to be rendered
     */
    abstract val queuedEntities: Array<Entity>

    /**
     * Draw all queued entities
     *
     * @param delta time since last frame
     */
    override fun update(delta: Float) {
        super.update(delta)
    }

    /**
     * Add entity to queue
     */
    override fun processEntity(entity: Entity, deltaTime: Float) {
        this.queuedEntities.add(entity)
    }

    /**
     * Called on window resize
     */
    fun resize(x: Int, y: Int) {
        this.viewport.update(x, y, true)
        this.camera.update()
    }

}