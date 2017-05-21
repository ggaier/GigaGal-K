package com.ggaier.gigagalk.gigagal.overlays

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.ggaier.gigagalk.gigagal.util.HUD_VIEWPORT_SIZE

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class GigagalHud(){

    val mViewport=ExtendViewport(HUD_VIEWPORT_SIZE, HUD_VIEWPORT_SIZE)
    val mFont=BitmapFont()

    fun render(batch :SpriteBatch){
        mViewport.apply()
        batch.begin()
        mFont.draw(batch,"Testing,testing. Gigagal",
                mViewport.worldWidth/2,
                mViewport.worldHeight/4,
                0f,Align.center,false)

        batch.end()

    }

}