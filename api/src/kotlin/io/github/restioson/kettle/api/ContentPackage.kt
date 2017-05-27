package io.github.restioson.kettle.api

/**
 * Interface for Content Package main classes
 * Resources should be scheduled to be loaded in the constructor
 */
interface ContentPackage {

    /**
     * Instance of Kettle engine. Will be set by Kettle loader
     */
    var engine: Kettle

    /**
     * Schedule resources to be loaded by the assetmanager here
     */
    fun registerResources()

    /**
     * Initialise things here
     *
     * @param engine instance of Kettle object
     *
     *
     */
    fun create()

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
    // TODO is this necessary? Entity systems could handle this...
    fun tick(delta: Double)

}