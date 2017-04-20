package com.ggaier.gigagalk.gigagal

import com.badlogic.gdx.Game

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 */
class GigagalGame: Game(){

    override fun create() {
        setScreen(GamePlayScreen())
    }

}