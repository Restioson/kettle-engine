package io.github.restioson.kettle

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import io.kotlintest.ProjectConfig

object TestConfig : ProjectConfig() {

    private lateinit var app: LwjglApplication

    override fun beforeAll() {
        app = LwjglApplication(TestApplication())
    }

    override fun afterAll() {
        app.exit()
    }
}