package com.ggaier.gigagalk.gigagal.overlays

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
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
class OnScreenControls : InputAdapter() {

    val mViewport: Viewport = ExtendViewport(ONSCREEN_CONTROLS_VIEWPORT_SIZE,
            ONSCREEN_CONTROLS_VIEWPORT_SIZE)
    val mMoveLeftCenter = Vector2()
    val mMoveRightCenter = Vector2()
    val mShootCenter = Vector2()
    val mJumpCenter = Vector2()

    private var mMoveLeftPointer = 0
    private var mMoveRightPointer = 1
    private var mJumpPointer = 2


    lateinit var mGigagal: Gigagal

    init {
        recalculateButtonPositions()
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val viewportPosition = mViewport.unproject(Vector2(screenX.f, screenY.f))
        if (viewportPosition.dst(mShootCenter) < BUTTON_RADIUS) {
            mGigagal.shoot()
        } else if (viewportPosition.dst(mMoveLeftCenter) < BUTTON_RADIUS) {
            mMoveLeftPointer = pointer
            mGigagal.leftButtonPressed = true
        } else if (viewportPosition.dst(mJumpCenter) < BUTTON_RADIUS) {
            mJumpPointer = pointer
            mGigagal.jumpButtonPressed = true
        } else if (viewportPosition.dst(mMoveRightCenter) < BUTTON_RADIUS) {
            mMoveRightPointer = pointer
            mGigagal.rightButtonPressed = true
        }
        return super.touchDown(screenX, screenY, pointer, button)
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        val viewportPosition = mViewport.unproject(Vector2(screenX.f, screenY.f))
        if (pointer == mMoveLeftPointer && viewportPosition.dst(mMoveRightCenter) < BUTTON_RADIUS) {
            mGigagal.leftButtonPressed = false
            mGigagal.rightButtonPressed = true
            mMoveLeftPointer = 0
            mMoveRightPointer = pointer
        }

        if (pointer == mMoveRightPointer && viewportPosition.dst(mMoveLeftCenter) < BUTTON_RADIUS) {
            mGigagal.rightButtonPressed = false
            mGigagal.leftButtonPressed = true
            mMoveRightPointer = 0
            mMoveLeftPointer = pointer
        }
        return super.touchDragged(screenX, screenY, pointer)
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

        if (!Gdx.input.isTouched(mJumpPointer)) {
            mGigagal.jumpButtonPressed = false
            mJumpPointer = 0
        }
        if (!Gdx.input.isTouched(mMoveLeftPointer)) {
            mGigagal.leftButtonPressed = false
            mMoveLeftPointer = 0
        }
        if (!Gdx.input.isTouched(mMoveRightPointer)) {
            mGigagal.rightButtonPressed = false
            mMoveRightPointer = 0
        }


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

private val Int.f: Float
    get() {
        return toFloat()
    }
