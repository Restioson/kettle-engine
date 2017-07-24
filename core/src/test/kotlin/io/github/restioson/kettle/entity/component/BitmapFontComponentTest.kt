package io.github.restioson.kettle.entity.component

import com.badlogic.gdx.graphics.g2d.BitmapFont
import io.github.restioson.kettle.TestApplication
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class BitmapFontComponentTest : StringSpec() {

    init {
        "constructor(font: BitmapFont)" {
            TestApplication.schedule {
                val font = BitmapFont()
                val component = BitmapFontComponent(BitmapFont())

                component.font.apply {
                    component.font!!.toString() shouldBe font.toString()
                }
            }
            Thread.sleep(1000)
        }

        "reset()" {
            TestApplication.schedule {
                val component = BitmapFontComponent(BitmapFont())
                component.reset()
                component.font shouldBe null
            }
            Thread.sleep(1000)
        }
    }
}