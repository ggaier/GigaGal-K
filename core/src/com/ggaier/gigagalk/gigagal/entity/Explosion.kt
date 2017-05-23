package com.ggaier.gigagalk.gigagal.entity

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.TimeUtils
import com.ggaier.gigagalk.gigagal.util.Assets
import com.ggaier.gigagalk.gigagal.util.EXPLOSION_CENTER
import com.ggaier.gigagalk.gigagal.util.Utils

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class Explosion(val mPosition: Vector2) {

    private val mStartTime = TimeUtils.nanoTime()
    var mOffset = 0f


    fun render(batch: SpriteBatch) {
        if (!isFinished() && !yetToStart())
            Utils.drawTextureRegions(batch,
                    Assets.mExplosionAssets.mExplosion.getKeyFrame(Utils.secondsSince(mStartTime)),
                    mPosition,
                    EXPLOSION_CENTER)
    }


    fun isFinished(): Boolean = Assets.mExplosionAssets.mExplosion.isAnimationFinished(Utils
            .secondsSince(mStartTime))

    fun yetToStart(): Boolean = Utils.secondsSince(mStartTime) - mOffset < 0

}