package com.ggaier.gigagalk.gigagal.overlays

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.ggaier.gigagalk.gigagal.entity.Gigagal
import com.ggaier.gigagalk.gigagal.util.*

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class OnScreenControls() {

    val mViewport: Viewport = ExtendViewport(ONSCREEN_CONTROLS_VIEWPORT_SIZE,
            ONSCREEN_CONTROLS_VIEWPORT_SIZE)
    val mMoveLeftCenter = Vector2()
    val mMoveRightCenter = Vector2()
    val mShootCenter = Vector2()
    val mJumpCenter = Vector2()

    lateinit var mGigagal: Gigagal

    init {
        recalculateButtonPositions()
    }

    fun recalculateButtonPositions() {
        mMoveLeftCenter.set(BUTTON_RADIUS * 3 / 4, BUTTON_RADIUS)
        mMoveRightCenter.set(BUTTON_RADIUS * 2, BUTTON_RADIUS * 3 / 4)

        mShootCenter.set(mViewport.worldWidth - BUTTON_RADIUS * 2, BUTTON_RADIUS * 3 / 4)
        mJumpCenter.set(mViewport.worldWidth - BUTTON_RADIUS * 3 / 4, BUTTON_RADIUS)

    }

    fun render(batch: SpriteBatch) {
        mViewport.apply()
        batch.projectionMatrix = mViewport.camera.combined
        batch.begin()

        Utils.drawTextureRegions(batch, Assets.mOnScreenControlsAssets.mMoveLeft,
                mMoveLeftCenter, BUTTON_CENTER)
        Utils.drawTextureRegions(batch, Assets.mOnScreenControlsAssets.mMoveRight,
                mMoveRightCenter, BUTTON_CENTER)
        Utils.drawTextureRegions(batch, Assets.mOnScreenControlsAssets.mShoot,
                mShootCenter, BUTTON_CENTER)
        Utils.drawTextureRegions(batch, Assets.mOnScreenControlsAssets.mJump,
                mJumpCenter, BUTTON_CENTER)

        batch.end()
    }

}
