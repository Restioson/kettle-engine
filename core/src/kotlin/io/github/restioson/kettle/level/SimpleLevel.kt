package io.github.restioson.kettle.level

import com.badlogic.ashley.core.*
import com.badlogic.ashley.utils.ImmutableArray
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.Level
import io.github.restioson.kettle.api.physics.Units
import kotlin.reflect.KClass

class SimpleLevel(override val engine: Kettle) : Level {

    private var entityEngine = Engine() // TODO pooledengine

    override var world = World(Vector2(0f, -9.8f * Units.PIXELS_TO_METERS), true) // TODO let contentpackages set world properties

    override val entities: ImmutableArray<Entity>
        get() = this.entityEngine.entities

    override fun addEntity(entity: Entity) {
        this.entityEngine.addEntity(entity) // TODO pooled engine thing
    }

    override fun removeAllEntities() {
        this.entityEngine.removeAllEntities()
    }

    override fun addEntitySystem(entitySystem: EntitySystem) {
        this.entityEngine.addSystem(entitySystem)
    }

    override fun getEntitySystem(type: KClass<EntitySystem>): EntitySystem {
        return this.entityEngine.getSystem(type.java)
    }

    override fun addEntityListener(listener: EntityListener) {
        this.entityEngine.addEntityListener(listener)
    }

    override fun addEntityListener(family: Family, listener: EntityListener) {
        this.entityEngine.addEntityListener(family, listener)
    }

    override fun addEntityListener(priority: Int, listener: EntityListener) {
        this.entityEngine.addEntityListener(priority, listener)
    }

    override fun addEntityListener(family: Family, priority: Int, listener: EntityListener) {
        this.entityEngine.addEntityListener(family, priority, listener)
    }

    override fun step(delta: Float) {
        this.world.step(delta, 6, 2)
        this.entityEngine.update(delta)
    }
}
