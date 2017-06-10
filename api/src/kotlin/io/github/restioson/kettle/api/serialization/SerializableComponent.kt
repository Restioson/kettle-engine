package io.github.restioson.kettle.api.serialization

import com.badlogic.ashley.core.Component

/**
 * Interface for components which can be serialized
 */
interface SerializableComponent : KettleSerializable, Component {
    /**
     * Whether to sync the component with the client/server
     */
    var sync: Boolean

    /**
     * Whether to save the component to the save file
     */
    var save: Boolean
}