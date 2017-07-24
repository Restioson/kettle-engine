package io.github.restioson.kettle.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import io.github.restioson.kettle.Engine
import mu.KLogging

object DesktopLauncher : KLogging() {

    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()

        config.width = 1024
        config.height = 576
        config.useGL30 = true

        logger.debug {
            """
            LWJGL Application Settings:
            Width: ${config.width}
            Height: ${config.height}
            Allow software mode: ${config.allowSoftwareMode}
            Preferences directory: ${config.preferencesDirectory}
            Preferences filetype: ${config.preferencesFileType}
            Fullscreen: ${config.fullscreen}
            Resizable: ${config.resizable}
            MSAA Samples: ${config.samples}
            Use HDPI mode (Mac): ${config.useHDPI}
            VSync enabled: ${config.vSyncEnabled}
            """.trimIndent()
        }

        logger.trace {
            """
            GL30: ${config.useGL30}"
            Bits per colour channel: ${config.r}, ${config.g}, ${config.b}, ${config.a}
            Audio device buffer count: ${config.audioDeviceBufferCount}
            Audio device buffer size: ${config.audioDeviceBufferSize}
            Audio device simultaneous sources: ${config.audioDeviceSimultaneousSources}
            Background FPS: ${config.backgroundFPS}
            Foreground FPS: ${config.foregroundFPS}
            Bits for depth buffer: ${config.depth}
            Bits for stencil buffer: ${config.stencil}
            Force exit: ${config.forceExit}
            Initial background colour: ${config.initialBackgroundColor}
            Override Density: ${config.overrideDensity}
            Window x: ${config.x}
            Window y: ${config.y}
            """.trimIndent()
        }

        logger.info("Launching LWJGL application")
        LwjglApplication(Engine(), config)
    }

}
