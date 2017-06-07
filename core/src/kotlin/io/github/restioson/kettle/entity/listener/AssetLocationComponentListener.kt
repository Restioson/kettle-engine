package io.github.restioson.kettle.entity.listener

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntityListener
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.entity.ComponentMappers
import io.github.restioson.kettle.entity.component.*

/**
 * Loads respective components into entities if they have AssetLocationComponents
 */

// TODO probably doesn't work for second and above entities
// TODO make listener interface for when a component is added
class AssetLocationComponentListener(val engine: Kettle) : EntityListener {

    override fun entityAdded(entity: Entity?) {

        ComponentMappers.ASSET_LOCATION[entity ?: return].apply {

            if (descriptor?.type == null) return

            // TODO implement more
            // TODO texture atlas regions
            when (descriptor?.type?.kotlin) {

                Texture::class ->
                    entity.add(GraphicsComponent(engine.assetManager[descriptor] as Texture))

                Sound::class ->
                    entity.add(SoundComponent(engine.assetManager[descriptor] as Sound))

                Music::class ->
                    entity.add(MusicComponent(engine.assetManager[descriptor] as Music))

                Pixmap::class ->
                    entity.add(PixmapComponent(engine.assetManager[descriptor] as Pixmap))

                BitmapFont::class ->
                    entity.add(BitmapFontComponent(engine.assetManager[descriptor] as BitmapFont))

                ParticleEffect::class ->
                    entity.add(ParticleEffectComponent(engine.assetManager[descriptor] as ParticleEffect))


            }

            fulfilled = true

        }

    }

    // TODO implement some way of resource removal from server
    override fun entityRemoved(entity: Entity?) {
    }

}