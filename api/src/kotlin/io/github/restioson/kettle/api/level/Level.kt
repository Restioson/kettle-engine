package io.github.restioson.kettle.api.level

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntityListener
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.utils.ImmutableArray
import com.badlogic.gdx.physics.box2d.World
import io.github.restioson.kettle.api.Kettle
import kotlin.reflect.KClass

interface Level {

    /**
     * Reference to the engine simulating this Level
     */
    val engine: Kettle

    /**
     * Instance of the Box2D world
     */
    var world: World

    /**
     * Array of entities from the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     */
    val entities: ImmutableArray<Entity>

    /**
     * Steps this level by a single tick
     *
     * @param delta the delta time
     */
    fun step(delta: Float)

    /**
     * Adds an entity to the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     *
     * @param entity entity to add
     *
     * @throws IllegalArgumentException entity already added
     */
    @Throws(IllegalArgumentException::class)
    fun addEntity(entity: Entity)

    /**
     * Removes all entities from the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     */
    fun removeAllEntities()

    /**
     * Adds an entity system to the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     *
     * @param entitySystem entitySystem to add
     */
    fun addEntitySystem(entitySystem: EntitySystem)

    /**
     * Gets an entity system from the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     *
     * @param type type of entity system
     */
    fun getEntitySystem(type: KClass<EntitySystem>): EntitySystem

    /**
     * Adds an entity listener to the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     *
     * @param listener entity listener to add
     */
    fun addEntityListener(listener: EntityListener)

    /**
     * Adds an entity listener to the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     *
     * @param family family to listen for
     * @param listener entity listener to add
     */
    fun addEntityListener(family: Family, listener: EntityListener)

    /**
     * Adds an entity listener to the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     *
     * @param priority priority of entity listener
     * @param listener entity listener to add
     */
    fun addEntityListener(priority: Int, listener: EntityListener)

    /**
     * Adds an entity listener to the [Entity Engine](http://libgdx.badlogicgames.com/ashley/docs/com/badlogic/ashley/core/PooledEngine.html)
     *
     * @param priority priority of entity listener
     * @param family family to listen for
     * @param listener entity listener to add
     */
    fun addEntityListener(family: Family, priority: Int, listener: EntityListener)

}
