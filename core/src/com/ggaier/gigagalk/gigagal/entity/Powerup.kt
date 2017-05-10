package com.ggaier.gigagalk.gigagal.entity

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ggaier.gigagalk.gigagal.util.Assets
import com.ggaier.gigagalk.gigagal.util.POWERUP_CENTER
import com.ggaier.gigagalk.gigagal.util.Utils

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class Powerup(val mPosition:Vector2){


    fun render(batch:SpriteBatch){
        val region=Assets.mPowerupAssets.mPowerup
        Utils.drawTextureRegions(batch,region,mPosition,
                POWERUP_CENTER)
    }

}