package io.github.restioson.kettle

import com.badlogic.ashley.core.*
import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.utils.ImmutableArray
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import io.github.restioson.kettle.api.ContentPackage
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.entity.system.Renderer
import io.github.restioson.kettle.api.physics.Units
import io.github.restioson.kettle.api.screen.KettleScreen
import org.reflections.Reflections
import kotlin.reflect.KClass

/**
 * Main engine class, and [io.github.restioson.kettle.api.Kettle][Kettle] implementation
 */
class Engine : Game(), Kettle {
    private lateinit var batch: SpriteBatch

    private lateinit var entityEngine: Engine // TODO pooledengine
    private lateinit var contentPackage: ContentPackage

    private var delta: Float = 0f

    override lateinit var world: World // TODO let contentpackages set world properties

    override var screen: KettleScreen
        get() = super.getScreen() as KettleScreen
        set(value) = super.setScreen(value)

    override val entities: ImmutableArray<Entity>
        get() = this.entityEngine.entities

    override fun create() {

        // Initialise fields
        this.batch = SpriteBatch()
        this.entityEngine = Engine()
        this.world = World(Vector2(0f, -9.8f * Units.PIXELS_TO_METERS), true)

        this.contentPackage = this.loadContentPackage()
        this.contentPackage.engine = this

        this.contentPackage.create()
    }

    override fun render() {

        delta = Gdx.graphics.deltaTime

        super.render()

        // TODO: Separate game ticks from render

        this.world.step(delta, 6, 2)
        this.entityEngine.update(delta)

    }

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

    override fun resize(width: Int, height: Int) {
        this.entityEngine.systems
                .filter { it is Renderer }
                .forEach { (it as Renderer).resize(width, height) }
    }

    override fun dispose() {
        this.batch.dispose()
    }

    private fun loadContentPackage(): ContentPackage {
        val reflections = Reflections("")
        return reflections.getSubTypesOf(ContentPackage::class.java).first { true }.newInstance()
    }
}
