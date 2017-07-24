package io.github.restioson.kettle.api.serialization

/**
 * Interface for serialization buffers
 */
@Deprecated("Kryo + protobuf serialization")
interface KettleBuffer {

    /**
     * Write an int as an unsigned 8 bit int (byte)
     */
    fun writeUInt8(value: Int)

    /**
     * Write an int as an unsigned 16 bit int (short)
     */
    fun writeUInt16(value: Int)

    /**
     * Write a long as an unsigned 32 bit int (int)
     */
    fun writeUInt32(value: Long)

    /**
     * Write a byte
     */
    fun writeInt8(value: Byte)

    /**
     * Write a short
     */
    fun writeInt16(value: Short)

    /**
     * Write an int
     */
    fun writeInt32(value: Int)

    /**
     * Write a long
     */
    fun writeInt64(value: Long)

    /**
     * Write a float
     */
    fun writeFloat32(value: Float)

    /**
     * Write a double
     */
    fun writeFloat64(value: Double)

    /**
     * Write an ASCII encoded string
     */
    fun writeStringASCII(value: String)

    /**
     * Read an unsigned byte as an int
     */
    fun readUInt8(): Int

    /**
     * Read an unsigned short as an int
     */
    fun readUInt16(): Int

    /**
     * Read an unsigned int as a long
     */
    fun readUInt32(): Long

    /**
     * Read a byte
     */
    fun readInt8(): Byte

    /**
     * Read a short
     */
    fun readInt16(): Short

    /**
     * Read an int
     */
    fun readInt32(): Int

    /**
     * Read a long
     */
    fun readInt64(): Long

    /**
     * Read a float
     */
    fun readFloat32(): Float

    /**
     * Read a double
     */
    fun readFloat64(): Double

    /**
     * Read an ASCII encoded string
     */
    fun readStringASCII(): String
}
