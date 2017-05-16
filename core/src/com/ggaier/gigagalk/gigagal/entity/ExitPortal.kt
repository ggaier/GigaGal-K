package com.ggaier.gigagalk.gigagal.entity

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.TimeUtils
import com.ggaier.gigagalk.gigagal.util.Assets
import com.ggaier.gigagalk.gigagal.util.EXIT_PORTAL_CENTER
import com.ggaier.gigagalk.gigagal.util.Utils

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class ExitPortal(val mPosition: Vector2) {

    private val mStartTime = TimeUtils.nanoTime()

    fun render(batch: SpriteBatch) {
        val elapsedTime = Utils.secondsSince(mStartTime)
        val region = Assets.mExitPortalAssets.mExitPortalAnimation.getKeyFrame(elapsedTime, true)
        Utils.drawTextureRegions(batch, region, mPosition, EXIT_PORTAL_CENTER)
    }

}