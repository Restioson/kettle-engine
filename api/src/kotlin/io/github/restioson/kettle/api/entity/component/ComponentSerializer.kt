package io.github.restioson.kettle.api.entity.component

import com.badlogic.ashley.core.Component
import java.nio.ByteBuffer

/**
 * Interface for component serializer
 *
 */
interface ComponentSerializer<T : Component> {

    /**
     * Serialize the object and return the serialized form as a ByteBuffer
     *
     * The output should be deserializable by the [deserialize] method
     *
     * @param component
     */
    fun serialize(component: T): ByteBuffer

    /**
     * Deserialize the object from the buffer, setting its properties according to the bytes
     */
    fun deserialize(buffer: ByteBuffer): T

}