package io.github.restioson.kettle.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import io.github.restioson.kettle.Engine

object DesktopLauncher {

    @JvmStatic fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.width = 1024
        config.height = 576
        LwjglApplication(Engine(), config)
    }

}
