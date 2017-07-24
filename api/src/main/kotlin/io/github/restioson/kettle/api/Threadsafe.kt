package io.github.restioson.kettle.api

/**
 * Marks a class or method as thread safe. Nothing should be considered thread safe unless explicitly declared thread safe
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@MustBeDocumented
annotation class Threadsafe
