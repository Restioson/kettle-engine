package io.github.restioson.kettle.api.entity

import com.badlogic.ashley.core.Component
import kotlin.reflect.KClass

/**
 * Exception thrown when an entity had a Component that has the [RequiresComponent] annotation but does not have the required Component
 * @param requiringComponent the Component which requires the Component which is lacking
 * @param requiredComponent the Component which is required by the requiring Component
 */
class RequiresComponentException(
        requiringComponent: KClass<out Component>,
        requiredComponent: KClass<out Component>
): Exception(
        "Component ${requiringComponent.qualifiedName} requires component ${requiredComponent.qualifiedName}"
)
