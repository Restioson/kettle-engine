package io.github.restioson.kettle

import android.os.Bundle

import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration

class AndroidLauncher : AndroidApplication() {
    protected fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        val config = AndroidApplicationConfiguration()
        initialize(Kettle(), config)
    }
}
