package io.github.restioson.kettle.api.physics

/**
 * Utility object to help with conversion between pixels and metric units
 */
object Units {
    var METERS_IN_PIXELS: Float = 15f
    val PIXELS_IN_METERS: Float
        get() = 1 / this.METERS_IN_PIXELS // Inverse
}