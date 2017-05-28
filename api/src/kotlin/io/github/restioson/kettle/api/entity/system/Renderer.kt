package io.github.restioson.kettle.api.entity.system

import com.badlogic.ashley.core.Entity

/**
 * Renderer abstract class
 */
abstract class Renderer : com.badlogic.ashley.systems.IteratingSystem(com.badlogic.ashley.core.Family.all(io.github.restioson.kettle.api.entity.component.GraphicsComponent::class.java, io.github.restioson.kettle.api.entity.component.Box2DComponent::class.java).get()) {

    /**
     * An instance of Camera which represents the camera used in drawing
     */
    abstract val camera: com.badlogic.gdx.graphics.Camera
    abstract val viewport: com.badlogic.gdx.utils.viewport.Viewport

    /**
     * A queue of entities to be rendered
     */
    abstract val queuedEntities: com.badlogic.gdx.utils.Array<Entity>

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
    override fun processEntity(entity: com.badlogic.ashley.core.Entity, deltaTime: Float) {
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