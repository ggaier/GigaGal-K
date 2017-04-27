package com.ggaier.gigagalk.gigagal.entity

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.ggaier.gigagalk.gigagal.util.Assets

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 */
class Platform(val mLeft: Float, val mTop: Float, val mWidth: Float, val mHeight: Float) {

    val mBottom = mTop - mHeight
    val mRright = mLeft + mWidth

    fun render(batch: SpriteBatch) {
        Assets.mPlatformAssets.mPlatformNinePatch.draw(batch, mLeft - 1,
                mBottom - 1, mWidth + 2, mHeight + 2)
    }

}