package io.github.restioson.kettle.api.serialization

import java.nio.ByteBuffer

class KettleByteBuffer {
    private val buffer: ByteBuffer

    val writtenBytes: ByteArray
        get() = this.buffer.array().copyOfRange(this.buffer.arrayOffset(), this.buffer.position())

    internal constructor(data: ByteArray) {
        this.buffer = ByteBuffer.wrap(data)
    }

    internal constructor(capacity: Int = 16384) {
        this.buffer = ByteBuffer.allocate(capacity)
    }

    fun writeUInt8(value: Int) {
        if (value in 0..0xFF) {
            this.buffer.put(value.toByte())
        } else {
            throw IllegalArgumentException("value: $value outside of range")
        }
    }

    fun writeUInt16(value: Int) {
        if (value in 0..0xFFFF) {
            this.buffer.putShort((value and 0xFFFF).toShort())
        } else {
            throw IllegalArgumentException("value: $value outside of range")
        }
    }

    fun writeUInt32(value: Long) {
        if (value in 0..0xFFFFFFFF) {
            this.buffer.putInt((value and 0xFFFFFFFF).toInt())
        } else {
            throw IllegalArgumentException("value: $value outside of range")
        }
    }

    fun writeInt8(value: Byte) {
        this.buffer.put(value)
    }

    fun writeInt16(value: Short) {
        this.buffer.putShort(value)
    }

    fun writeInt32(value: Int) {
        this.buffer.putInt(value)
    }

    fun writeInt64(value: Long) {
        this.buffer.putLong(value)
    }

    fun writeFloat32(value: Float) {
        this.buffer.putFloat(value)
    }

    fun writeFloat64(value: Double) {
        this.buffer.putDouble(value)
    }

    fun writeStringASCII(value: String) {
        for (char in value) {
            this.writeUInt8(char.toInt())
        }
        this.writeUInt8(0)
    }

    fun readUInt8() = this.buffer.get().toInt() and 0xFF

    fun readUInt16() = this.buffer.short.toInt() and 0xFFFF

    fun readUInt32() = this.buffer.int.toLong() and 0xFFFFFFFF

    fun readInt8() = this.buffer.get()

    fun readInt16() = this.buffer.short

    fun readInt32() = this.buffer.int

    fun readStringASCII(): String {
        val builder = StringBuilder()
        var char = -1
        while (char != 0) {
            char = this.readUInt8()
            builder.append(char.toChar())
        }
        return builder.toString()
    }
}
