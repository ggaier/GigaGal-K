package com.ggaier.gigagalk.gigagal.entity

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ggaier.gigagalk.gigagal.util.Assets
import com.ggaier.gigagalk.gigagal.util.ENEMY_CENTER
import com.ggaier.gigagalk.gigagal.util.Utils

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class Enemy(val mPlatform: Platform){

    val mPosition:Vector2= Vector2(mPlatform.mLeft,
            mPlatform.mTop+ ENEMY_CENTER.y)

    fun update(delta:Float){

    }

    /**
     * 渲染Enemy自身的方法。
     */
    fun render(batch:SpriteBatch){
        Utils.drawTextureRegions(batch,
                Assets.mEnemyAssets.mEnemy,mPosition,
                ENEMY_CENTER)
    }


}
