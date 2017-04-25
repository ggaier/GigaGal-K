package com.ggaier.gigagalk.gigagal.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.TimeUtils
import com.ggaier.gigagalk.gigagal.util.*

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 */
class Gigagal {

    val mPosition: Vector2 = Vector2(20f, GIGAGAL_EYE_HEIGHT)
    private var mFacing: Facing = Facing.RIGHT
    private val mVelocity: Vector2 = Vector2()
    private var mJumpState = JumpState.FALLING
    private var mJumpStartTime: Long = Long.MIN_VALUE

    public fun update(delta: Float) {
        mVelocity.y -= delta * GRAVITY
        mPosition.mulAdd(mVelocity, delta)

        if (mJumpState != JumpState.JUMPING) {
            mJumpState = JumpState.FALLING
        }

        if (mPosition.y - GIGAGAL_EYE_HEIGHT < 0) {
            mJumpState = JumpState.GROUNDED
            mPosition.y = GIGAGAL_EYE_HEIGHT
            mVelocity.y = 0f
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            when (mJumpState) {
                JumpState.GROUNDED -> startJump()
                JumpState.JUMPING -> continueJump()
                else -> {

                }
            }
        } else {
            endJump()
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveLeft(delta)
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveRight(delta)
        }
    }

    private fun moveLeft(delta: Float) {
        mPosition.x -= delta * GIGAGAL_MOVING_SPEED
        mFacing = Facing.LEFT
    }

    private fun endJump() {
        if (mJumpState == JumpState.JUMPING) {
            mJumpState = JumpState.FALLING
        }
    }

    private fun startJump() {
        mJumpState = JumpState.JUMPING
        mJumpStartTime = TimeUtils.nanoTime()
        continueJump()
    }

    private fun continueJump() {
        if (mJumpState == JumpState.JUMPING) {
            val jumpDuration = MathUtils.nanoToSec * (TimeUtils.nanoTime() - mJumpStartTime)
            if (jumpDuration < MAX_JUMP_DURATION) {
                mVelocity.y = JUMP_SPEED
            } else {
                endJump()
            }
        }
    }

    private fun moveRight(delta: Float) {
        mFacing = Facing.RIGHT
        mPosition.x += delta * GIGAGAL_MOVING_SPEED
    }


    public fun render(batch: SpriteBatch) {
        val region = if (mFacing == Facing.RIGHT)
            Assets.mGigagalAssets.mStandRight
        else Assets.mGigagalAssets.mStandLeft

        batch.draw(region.texture,
                mPosition.x - GIGAGAL_EYE_POSITION.x,
                mPosition.y - GIGAGAL_EYE_POSITION.y,
                0f, 0f,
                region.regionWidth.toFloat(), region.regionHeight.toFloat(),
                1f, 1f, 0f,
                region.regionX, region.regionY,
                region.regionWidth, region.regionHeight,
                false, false)
    }

    enum class Facing {
        LEFT, RIGHT
    }

    enum class JumpState {
        JUMPING,
        FALLING,
        GROUNDED
    }
}