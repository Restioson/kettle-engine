package io.github.restioson.kettle.api.serialization

/**
 * Interface for components which can be serialized
 */
interface SerializableComponent : KettleSerializable {
    /**
     * Whether to sync the component with the client/server
     */
    var sync: Boolean

    /**
     * Whether to save the component to the save file
     */
    var save: Boolean
}