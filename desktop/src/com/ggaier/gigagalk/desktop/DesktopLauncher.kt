package com.ggaier.gigagalk.desktop

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.ggaier.gigagalk.gigagal.GigagalGame
import com.ggaier.gigagalk.rectanglecirclecollision.RectangleCircleCollisionGame
import java.util.*

object DesktopLauncher {
    @JvmStatic fun main(arg: Array<String>) {
        var applicationListener: ApplicationListener? = null
        while (true) {
            println("Input which game you want start: " + Arrays.toString(Games.values()))
            val input = readLine()?.trim()
            println("input is $input")
            when (input) {
                Games.GIGAGAL.name -> applicationListener = GigagalGame()
                Games.COLLISION.name -> applicationListener = RectangleCircleCollisionGame()
                else -> {
                    println("Cannot find the Game $input to start, try again. ")
                }
            }
            if (applicationListener != null)
                break
        }
        LwjglApplication(applicationListener, LwjglApplicationConfiguration())
    }

    enum class Games {
        GIGAGAL,
        COLLISION
    }
}
