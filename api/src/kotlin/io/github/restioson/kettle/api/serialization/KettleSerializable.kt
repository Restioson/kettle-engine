package io.github.restioson.kettle.api.serialization

/**
 * Interface for classes that can be serialized
 */
// TODO deprecate in favour of Kryo/Protobuf
interface KettleSerializable {

    /**
     * Serialize the component into the buffer
     *
     * @param buffer buffer to serialize into
     * @param extraList list of extras to be serialized with the state but not associated with the component
     */
    fun serialize(buffer: KettleBuffer, extraList: ArrayList<KettleSerializable>)

    /**
     * Deserialize the component from the buffer
     *
     * @param buffer buffer to deserialize from
     */
    fun deserialize(buffer: KettleBuffer)

}
