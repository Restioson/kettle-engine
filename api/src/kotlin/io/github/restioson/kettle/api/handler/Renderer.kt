package io.github.restioson.kettle.api.handler

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.utils.Array
import io.github.restioson.kettle.api.entity.Box2DComponent
import io.github.restioson.kettle.api.entity.GraphicsComponent

/**
 * Renderer abstract class
 */
abstract class Renderer : IteratingSystem(Family.all(GraphicsComponent::class.java, Box2DComponent::class.java).get()) {

    /**
     * An instance of Camera which represents the camera used in drawing
     */
    abstract val camera: Camera

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
    abstract override fun processEntity(entity: Entity, deltaTime: Float)

}