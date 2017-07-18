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
     * Logic for Client side stuff
     */
    var kClientSide: ClientSidePackage

    /**
     * Logic for Server side stuff
     */
    var kServerSide: ServerSidePackage

    /**
     * Initialise [ClientSidePackage]. Create [ClientSidePackage] and call [ClientSidePackage.init]
     */
    fun initClient()

    /**
     * Called after [engine] is set, so initialise (not create) clientSide and serverSide here
     */
    fun create()

}