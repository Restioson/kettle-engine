package io.github.restioson.kettle.api.entity

import kotlin.reflect.KClass
import com.badlogic.ashley.core.Component

/**
 * Annotation for Components which denotes that they require that any entity that contains it needs another specific component
 *
 * @param component the required component
 */
annotation class RequiresComponent(val component: KClass<out Component>)