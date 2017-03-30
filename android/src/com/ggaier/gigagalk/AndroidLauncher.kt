package com.ggaier.gigagalk

import android.os.Bundle
import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.ggaier.gigagalk.texturedrawing.TextureDrawing

/**
 * Created by ggaier at 30/03/2017 .
 * jwenbo52@gmail.com
 */
class AndroidLauncher : AndroidApplication() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val flag = intent.getIntExtra(EXTRA_FLAG, -1)
        var applicationListener: ApplicationListener = GigaGalGame()
        when (flag) {
            FLAG_DRAW_A_LOGO -> applicationListener = TextureDrawing()
        }
        if (applicationListener != null) {
            val config: AndroidApplicationConfiguration = AndroidApplicationConfiguration()
            initialize(applicationListener, config)
        }

    }

}