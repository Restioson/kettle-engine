package io.github.restioson.kettle.api.serialization

/**
 * For injection of Kettle objects after deserialisation
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
annotation class KettleInject(val target: InjectionTarget = InjectionTarget.AUTO)

enum class InjectionTarget {

    /**
     * Autodetects the target based on propety type
     */
    AUTO,

    KETTLE
}