package io.github.restioson.kettle.api.entity

import com.badlogic.ashley.core.Component
import kotlin.reflect.KClass

/**
 * Annotation for Components which denotes that they require that any entity that contains it needs another specific component
 *
 * @param component the required component
 */
annotation class RequiresComponent(vararg val component: KClass<out Component>)