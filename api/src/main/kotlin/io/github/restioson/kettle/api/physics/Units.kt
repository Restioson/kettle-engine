package io.github.restioson.kettle.api.physics

import com.badlogic.gdx.math.Vector2

/**
 * Utility object to help with conversion between pixels and metric units
 */
object Units {
    var METERS_IN_PIXELS: Float = 15f
    val PIXELS_IN_METERS: Float
        get() = 1 / this.METERS_IN_PIXELS // Inverse

    fun toPixels(meters: Float) = meters * METERS_IN_PIXELS
    fun toMeters(pixels: Float) = pixels * PIXELS_IN_METERS

    fun toPixels(meters: Vector2) = meters.set(meters.x * METERS_IN_PIXELS, meters.y * METERS_IN_PIXELS)
    fun toMeters(pixels: Vector2) = pixels.set(pixels.x * PIXELS_IN_METERS, pixels.y * PIXELS_IN_METERS)

    fun toPixels(metersX: Float, metersY: Float) = arrayOf(metersX * METERS_IN_PIXELS, metersY * METERS_IN_PIXELS)
    fun toMeters(pixelsX: Float, pixelsY: Float) = arrayOf(pixelsX * PIXELS_IN_METERS, pixelsY * PIXELS_IN_METERS)
}