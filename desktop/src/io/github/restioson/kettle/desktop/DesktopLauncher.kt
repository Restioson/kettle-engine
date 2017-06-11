package io.github.restioson.kettle.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import io.github.restioson.kettle.Engine
import mu.KLogging

object DesktopLauncher : KLogging() {

    @JvmStatic fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()

        config.width = 1024
        config.height = 576
        config.useGL30 = true

        logger.debug {
            StringBuilder()
                    .appendln("LWJGL Application Settings:")
                    .appendln("Width: ${config.width}")
                    .appendln("Height: ${config.height}")
                    .appendln("Allow software mode: ${config.allowSoftwareMode}")
                    .appendln("Preferences directory: ${config.preferencesDirectory}")
                    .appendln("Preferences filetype: ${config.preferencesFileType}")
                    .appendln("Fullscreen: ${config.fullscreen}")
                    .appendln("Resizable: ${config.resizable}")
                    .appendln("MSAA Samples: ${config.samples}")
                    .appendln("Use HDPI mode (Mac): ${config.useHDPI}")
                    .appendln("VSync enabled: ${config.vSyncEnabled}")
                    .toString()
        }

        logger.trace {
            StringBuilder()
                    .appendln("GL30: ${config.useGL30}") // Should always be on
                    .appendln("Bits per colour channel: ${config.r}, ${config.g}, ${config.b}, ${config.a}")
                    .appendln("Audio device buffer count: ${config.audioDeviceBufferCount}")
                    .appendln("Audio device buffer size: ${config.audioDeviceBufferSize}")
                    .appendln("Audio device simultaneous sources: ${config.audioDeviceSimultaneousSources}")
                    .appendln("Background FPS: ${config.backgroundFPS}")
                    .appendln("Foreground FPS: ${config.foregroundFPS}")
                    .appendln("Bits for depth buffer: ${config.depth}")
                    .appendln("Bits for stencil buffer: ${config.stencil}")
                    .appendln("Force exit: ${config.forceExit}")
                    .appendln("Initial background colour: ${config.initialBackgroundColor}")
                    .appendln("Override Density: ${config.overrideDensity}")
                    .appendln("Window x: ${config.x}")
                    .appendln("Window y: ${config.y}")
                    .toString()
        }

        logger.info("Launching LWJGL application")
        LwjglApplication(Engine(), config)
    }

}
