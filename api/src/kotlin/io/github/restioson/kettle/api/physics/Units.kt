package io.github.restioson.kettle.api.physics

/**
 * Utility object to help with conversion between pixels and metric units
 */
object Units {
    var PIXELS_TO_METERS: Float = 15f
    val METERS_TO_PIXELS: Float
        get() = 1 / PIXELS_TO_METERS
}