package io.github.restioson.kettle.api

/**
 * Interface for Content Package main classes
 */
interface ContentPackage {

    /**
     * Called on load of Content Package. Initialise everything here
     *
     * @param engine instance of Kettle object
     *
     *
     */
    fun create(engine: Kettle)

    /**
     * Called on dispose of application. Dispose of any assets with internal references
     *
     */
    fun dispose()

    /**
     * Called on pause of application by libGDX, by Kettle, at the end of it's lifecycle, or before [dispose][io.github.restioson.kettle.Kettle.dispose] is called.
     *
    */
    fun pause()

    /**
     * Called on resume of application by libGDX, or by Kettle.
     */
    fun resume()

    /**
     * Called once every tick. Update internal things
     *
     * @param delta time since last tick, ideally 50ms
     */
    fun tick(delta: Double)

}