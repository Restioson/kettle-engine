package io.github.restioson.kettle.entity

import com.badlogic.ashley.core.ComponentMapper
import io.github.restioson.kettle.entity.component.*

/**
 * Object which stores Ashley ComponentMappers
 */
object ComponentMappers {

    val ASSET_LOCATION: ComponentMapper<AssetLocationComponent> = ComponentMapper.getFor(AssetLocationComponent::class.java)
    val GRAPHICS: ComponentMapper<GraphicsComponent> = ComponentMapper.getFor(GraphicsComponent::class.java)
    val BODY: ComponentMapper<BodyComponent> = ComponentMapper.getFor(BodyComponent::class.java)
    val HEALTH: ComponentMapper<HealthComponent> = ComponentMapper.getFor(HealthComponent::class.java)
    val SOUND: ComponentMapper<SoundComponent> = ComponentMapper.getFor(SoundComponent::class.java)
    val MUSIC: ComponentMapper<MusicComponent> = ComponentMapper.getFor(MusicComponent::class.java)
    val PIXMAP: ComponentMapper<PixmapComponent> = ComponentMapper.getFor(PixmapComponent::class.java)
    val BITMAP_FONT: ComponentMapper<BitmapFontComponent> = ComponentMapper.getFor(BitmapFontComponent::class.java)

}