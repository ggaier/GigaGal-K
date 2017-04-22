package com.ggaier.gigagalk.gigagal

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.ggaier.gigagalk.gigagal.entity.Gigagal

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 */
class Level(){

    private val gigagal=Gigagal()

    public fun update(delta:Float){
        gigagal.update(delta)
    }

    public fun render(batch :SpriteBatch){
        gigagal.render(batch)
    }

}