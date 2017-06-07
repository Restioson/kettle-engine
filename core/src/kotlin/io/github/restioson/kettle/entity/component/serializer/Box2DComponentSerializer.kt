package io.github.restioson.kettle.entity.component.serializer

import io.github.restioson.kettle.api.entity.component.ComponentSerializer
import io.github.restioson.kettle.entity.component.Box2DComponent
import java.nio.ByteBuffer


/**
 * Serializer Box2D buffer into [ByteBuffer]
 */

/*
 * Buffer breakdown:
 *
 *
 * {
 *      angle;
 *      angularDamping;
 *      angularVelocity;
 *      gravityScale;
 *      interia;
 *      mass;
 *      linearDamping;
 *
 *      linearVelocity.x;
 *      linearVelocity.y;
 *
 *      localCenter.x;
 *      localCenter.y;
 *
 *      massData.mass;
 *      massData.center.x;
 *      massData.center.y;
 *
 *      position.x;
 *      position.y;
 *
 *      transform.rotation;
 *      transform.orientation.x;
 *      transform.orientation.y;
 *      transform.position.x;
 *      transform.position.y; // TODO is this the same as position?
 *
 *      transform.vals[0];
 *      transform.vals[1];
 *      transform.vals[2];
 *      transform.vals[3];
 *
 *      type.ordinal; // TODO use this to cut down on serialized size
 *
 *      worldCenter.x;
 *      worldCenter.y;
 *
 *      isBullet;
 *      isFixedRotation;
 *      isSleepingAllowed;
 * }
 */
object Box2DComponentSerializer : ComponentSerializer<Box2DComponent> {

    val BYTES_PER_BODY_PROPERTIES = 113 // 28 floats
    // 1 byte

    override fun serialize(component: Box2DComponent): ByteBuffer {

        val body = component.body ?: return ByteBuffer.allocate(0)

        val bytes = ByteBuffer.allocate(32) // TODO auto enlarge
        // currently 29

        arrayOf(

                body.angle,
                body.angularVelocity,
                body.angularVelocity,
                body.gravityScale,
                body.inertia,
                body.mass,
                body.linearDamping,

                body.linearVelocity.x,
                body.linearVelocity.y,

                body.localCenter.x,
                body.localCenter.y,

                body.massData.mass,
                body.massData.center.x,
                body.massData.center.y,
                body.massData.I,

                body.position.x,
                body.position.y,


                body.transform.rotation,
                body.transform.orientation.x,
                body.transform.orientation.y,
                body.transform.position.x,
                body.transform.position.y,

                body.transform.vals[0],
                body.transform.vals[1],
                body.transform.vals[2],
                body.transform.vals[3],

                body.worldCenter.x,
                body.worldCenter.y

        ).forEach {
            bytes.putFloat(it)
        }

        bytes.put(body.type.ordinal.toByte())

        // JointList
        // FixtureList

        // TODO bitmask these
        // body.isActive
        // body.isAwake
        // body.isBullet
        // body.isFixedRotation
        // body.isSleepingAllowed

        TODO("Not finished implementing")
    }

    override fun deserialize(buffer: ByteBuffer): Box2DComponent {
        TODO("Not yet implemented")
    }

}

