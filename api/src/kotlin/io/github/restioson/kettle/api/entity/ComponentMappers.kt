package io.github.restioson.kettle.api.entity

import com.badlogic.ashley.core.ComponentMapper

/**
 * Object which stores Ashley ComponentMappers
 */
object ComponentMappers {
    val GRAPHICS_MAPPER: ComponentMapper<GraphicsComponent> = ComponentMapper.getFor(GraphicsComponent::class.java)
    val BOX2D_MAPPER: ComponentMapper<Box2DComponent> = ComponentMapper.getFor(Box2DComponent::class.java)
    val HEALTH_MAPPER: ComponentMapper<HealthComponent> = ComponentMapper.getFor(HealthComponent::class.java)
}