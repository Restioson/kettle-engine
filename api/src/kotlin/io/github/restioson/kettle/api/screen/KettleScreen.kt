package io.github.restioson.kettle.api.screen

import com.badlogic.gdx.Screen
import io.github.restioson.kettle.api.Kettle

interface KettleScreen : Screen {

    /**
     * The engine associated with this screen
     */
    val engine: Kettle

}
