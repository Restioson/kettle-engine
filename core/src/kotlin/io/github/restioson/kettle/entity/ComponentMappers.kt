package io.github.restioson.kettle.entity

import com.badlogic.ashley.core.ComponentMapper
import io.github.restioson.kettle.entity.component.AssetLocationComponent
import io.github.restioson.kettle.entity.component.BitmapFontComponent
import io.github.restioson.kettle.entity.component.BodyComponent
import io.github.restioson.kettle.entity.component.GraphicsComponent
import io.github.restioson.kettle.entity.component.HealthComponent
import io.github.restioson.kettle.entity.component.MusicComponent
import io.github.restioson.kettle.entity.component.PixmapComponent
import io.github.restioson.kettle.entity.component.SoundComponent

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