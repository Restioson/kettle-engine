package io.github.restioson.kettle.api.physics

/**
 * Utility object to help with conversion between pixels and metric units
 */
object Units {
    var METERS_TO_PIXELS: Float = 1f
    val PIXELS_TO_METERS: Float
        get() = 1 / METERS_TO_PIXELS
}