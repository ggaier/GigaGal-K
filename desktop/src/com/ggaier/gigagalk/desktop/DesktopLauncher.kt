package com.ggaier.gigagalk.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.ggaier.gigagalk.rectanglecirclecollision.RectangleCircleCollisionGame

object DesktopLauncher {
    @JvmStatic fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
//        LwjglApplication(GigagalGame(), config)
        LwjglApplication(RectangleCircleCollisionGame(), config)
    }
}
