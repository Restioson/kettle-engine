package io.github.restioson.kettle.api.serialization

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class Reference<in R, T>() : ReadOnlyProperty<R, T> {

    private lateinit var getFunc: () -> T

    @Transient
    private val lazyDelegate: Lazy<T> = lazy(this.getFunc)

    @DelegatedTransient
    private val cachedValue: T by this.lazyDelegate

    constructor(getFunc: () -> T) : this() {
        this.getFunc = getFunc
    }

    override fun getValue(thisRef: R, property: KProperty<*>): T {
        return this.cachedValue
    }
}