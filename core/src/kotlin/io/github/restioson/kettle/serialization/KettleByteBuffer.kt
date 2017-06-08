package io.github.restioson.kettle.serialization

import io.github.restioson.kettle.api.serialization.KettleBuffer
import java.nio.ByteBuffer

class KettleByteBuffer : KettleBuffer {

    private val buffer: ByteBuffer

    val writtenBytes: ByteArray
        get() = this.buffer.array().copyOfRange(this.buffer.arrayOffset(), this.buffer.position())

    internal constructor(data: ByteArray) {
        this.buffer = ByteBuffer.wrap(data)
    }

    internal constructor(capacity: Int = 16384) {
        this.buffer = ByteBuffer.allocate(capacity)
    }

    override fun writeUInt8(value: Int) {
        if (value in 0..0xFF) {
            this.buffer.put(value.toByte())
        } else {
            throw IllegalArgumentException("value: $value outside of range")
        }
    }

    override fun writeUInt16(value: Int) {
        if (value in 0..0xFFFF) {
            this.buffer.putShort((value and 0xFFFF).toShort())
        } else {
            throw IllegalArgumentException("value: $value outside of range")
        }
    }

    override fun writeUInt32(value: Long) {
        if (value in 0..0xFFFFFFFF) {
            this.buffer.putInt((value and 0xFFFFFFFF).toInt())
        } else {
            throw IllegalArgumentException("value: $value outside of range")
        }
    }

    override fun writeInt8(value: Byte) {
        this.buffer.put(value)
    }

    override fun writeInt16(value: Short) {
        this.buffer.putShort(value)
    }

    override fun writeInt32(value: Int) {
        this.buffer.putInt(value)
    }

    override fun writeInt64(value: Long) {
        this.buffer.putLong(value)
    }

    override fun writeFloat32(value: Float) {
        this.buffer.putFloat(value)
    }

    override fun writeFloat64(value: Double) {
        this.buffer.putDouble(value)
    }

    override fun writeStringASCII(value: String) {
        for (char in value) {
            this.writeUInt8(char.toInt())
        }
        this.writeUInt8(0)
    }

    override fun readUInt8() = this.buffer.get().toInt() and 0xFF

    override fun readUInt16() = this.buffer.short.toInt() and 0xFFFF

    override fun readUInt32() = this.buffer.int.toLong() and 0xFFFFFFFF

    override fun readInt8() = this.buffer.get()

    override fun readInt16() = this.buffer.short

    override fun readInt32() = this.buffer.int

    override fun readInt64() = this.buffer.long

    override fun readFloat32() = this.buffer.float

    override fun readFloat64() = this.buffer.double

    override fun readStringASCII(): String {
        val builder = StringBuilder()
        var char = -1
        while (char != 0) {
            char = this.readUInt8()
            builder.append(char.toChar())
        }
        return builder.toString()
    }
}
