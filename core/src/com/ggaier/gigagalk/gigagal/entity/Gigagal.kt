package com.ggaier.gigagalk.gigagal.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.TimeUtils
import com.ggaier.gigagalk.gigagal.Level
import com.ggaier.gigagalk.gigagal.util.*

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 */
class Gigagal(val mSpawnLocation: Vector2, val mLevel: Level) {

    private val mVelocity: Vector2 = Vector2()
    private val mLastFramePosition: Vector2 = Vector2()
    val mPosition: Vector2 = Vector2()

    private var mFacing: Enums.Direction = Enums.Direction.RIGHT
    private var mJumpState = Enums.JumpState.FALLING
    private var mWalkingState: Enums.WalkingState = Enums.WalkingState.STANDING

    private var mJumpStartTime: Long = Long.MIN_VALUE
    private var mWalkStartTime: Long = Long.MIN_VALUE

    init {
        init()
    }

    fun init() {
        mPosition.set(mSpawnLocation)
        mLastFramePosition.set(mSpawnLocation)
        mVelocity.setZero()
        mJumpState = Enums.JumpState.FALLING
        mFacing = Enums.Direction.RIGHT
        mWalkingState = Enums.WalkingState.STANDING
    }


    fun update(delta: Float, platforms: Array<Platform>) {
        mLastFramePosition.set(mPosition)
        mVelocity.y -= delta * GRAVITY
        mPosition.mulAdd(mVelocity, delta)
        if (mPosition.y < KILLING_PANE) {
            init()
        }
        if (mJumpState != Enums.JumpState.JUMPING) {
            if (mJumpState != Enums.JumpState.RECOILING) {
                mJumpState = Enums.JumpState.FALLING
            }
            platforms.forEach {
                if (landedOnPlatform(it)) {
                    mJumpState = Enums.JumpState.GROUNDED
                    mVelocity.y = 0f
                    mVelocity.x = 0f
                    mPosition.y = it.mTop + GIGAGAL_EYE_HEIGHT
                }
            }
        }

        isCollideWithEnemies()

        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            when (mJumpState) {
                Enums.JumpState.GROUNDED -> startJump()
                Enums.JumpState.JUMPING -> continueJump()
            }
        } else {
            endJump()
        }

        if (mJumpState != Enums.JumpState.RECOILING) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                moveLeft(delta)
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                moveRight(delta)
            } else {
                mWalkingState = Enums.WalkingState.STANDING
            }
        }

        shootBulletIfKeyPressed()
    }

    private fun shootBulletIfKeyPressed() {
        if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            val bulletPosition = if (mFacing == Enums.Direction.LEFT) {
                Vector2(mPosition.x - GIGAGAL_CANNON_OFFSET.x,
                        mPosition.x + GIGAGAL_CANNON_OFFSET.y)
            } else {
                Vector2(mPosition.x + GIGAGAL_CANNON_OFFSET.x,
                        mPosition.y + GIGAGAL_CANNON_OFFSET.y)
            }
            mLevel.spawnBullet(bulletPosition,mFacing)
        }
    }

    private fun isCollideWithEnemies() {
        val rectangleBounds = Rectangle(mPosition.x - GIGAGAL_STANCE_WIDTH / 2,
                mPosition.y - GIGAGAL_EYE_HEIGHT, GIGAGAL_STANCE_WIDTH,
                GIGAGAL_HEIGHT)
        mLevel.mEnemies.forEach {
            val enemyBounds = Rectangle(it.mPosition.x - ENEMY_COLLISION_RADIUS,
                    it.mPosition.y - ENEMY_COLLISION_RADIUS,
                    2 * ENEMY_COLLISION_RADIUS, 2 * ENEMY_COLLISION_RADIUS)
            if (rectangleBounds.overlaps(enemyBounds)) {
                if (mPosition.x < it.mPosition.x) {
                    recoilFromEnemy(Enums.Direction.LEFT)
                } else {
                    recoilFromEnemy(Enums.Direction.RIGHT)
                }
            }
        }
    }

    private fun recoilFromEnemy(direction: Enums.Direction) {
        mJumpState = Enums.JumpState.RECOILING
        mVelocity.y = KNOCK_BACK_VELOCITY.y
        mVelocity.x = if (direction == Enums.Direction.LEFT) -KNOCK_BACK_VELOCITY.x
        else KNOCK_BACK_VELOCITY.x
    }

    fun landedOnPlatform(platform: Platform): Boolean {
        var leftFootIn = false
        var rightFootIn = false
        var straddle = false
        if (mLastFramePosition.y - GIGAGAL_EYE_HEIGHT >= platform.mTop && mPosition.y -
                GIGAGAL_EYE_HEIGHT < platform.mTop) {
            val leftFoot = mPosition.x - GIGAGAL_STANCE_WIDTH / 2
            val rightFoot = mPosition.x + GIGAGAL_STANCE_WIDTH / 2
            leftFootIn = (platform.mLeft < leftFoot && platform.mRright > leftFoot)
            rightFootIn = (platform.mLeft < rightFoot && platform.mRright > rightFoot)
            straddle = (platform.mLeft > leftFoot && platform.mRright < rightFoot)
        }
        return leftFootIn || rightFootIn || straddle
    }

    private fun moveLeft(delta: Float) {
        if (mJumpState == Enums.JumpState.GROUNDED && mWalkingState != Enums.WalkingState.WALKING) {
            mWalkStartTime = TimeUtils.nanoTime()
        }
        mWalkingState = Enums.WalkingState.WALKING
        mPosition.x -= delta * GIGAGAL_MOVING_SPEED
        mFacing = Enums.Direction.LEFT
    }

    private fun moveRight(delta: Float) {
        if (mJumpState == Enums.JumpState.GROUNDED && mWalkingState != Enums.WalkingState.WALKING) {
            mWalkStartTime = TimeUtils.nanoTime()
        }
        mFacing = Enums.Direction.RIGHT
        mWalkingState = Enums.WalkingState.WALKING
        mPosition.x += delta * GIGAGAL_MOVING_SPEED
    }

    private fun endJump() {
        if (mJumpState == Enums.JumpState.JUMPING) {
            mJumpState = Enums.JumpState.FALLING
        }
    }

    private fun startJump() {
        mJumpState = Enums.JumpState.JUMPING
        mJumpStartTime = TimeUtils.nanoTime()
        continueJump()
    }

    private fun continueJump() {
        if (mJumpState == Enums.JumpState.JUMPING) {
            val jumpDuration = MathUtils.nanoToSec * (TimeUtils.nanoTime() - mJumpStartTime)
            if (jumpDuration < MAX_JUMP_DURATION) {
                mVelocity.y = JUMP_SPEED
            } else {
                endJump()
            }
        }
    }

    fun render(batch: SpriteBatch) {
        val region = when {
            mFacing == Enums.Direction.RIGHT && mJumpState != Enums.JumpState.GROUNDED ->
                Assets.mGigagalAssets.mJumpingRight
            mFacing == Enums.Direction.RIGHT && mWalkingState == Enums.WalkingState.STANDING ->
                Assets.mGigagalAssets.mStandRight
            mFacing == Enums.Direction.RIGHT && mWalkingState == Enums.WalkingState.WALKING -> {
                val walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - mWalkStartTime)
                Assets.mGigagalAssets.mWalkingRightAnimation.getKeyFrame(walkTimeSeconds)
            }
            mFacing == Enums.Direction.LEFT && mJumpState != Enums.JumpState.GROUNDED ->
                Assets.mGigagalAssets.mJumpingLeft
            mFacing == Enums.Direction.LEFT && mWalkingState == Enums.WalkingState.STANDING ->
                Assets.mGigagalAssets.mStandLeft
            mFacing == Enums.Direction.LEFT && mWalkingState == Enums.WalkingState.WALKING -> {
                val walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - mWalkStartTime)
                Assets.mGigagalAssets.mWalkingLeftAnimation.getKeyFrame(walkTimeSeconds)
            }
            else ->
                Assets.mGigagalAssets.mStandRight
        }
        Utils.drawTextureRegions(batch, region, mPosition.x - GIGAGAL_EYE_POSITION.x,
                mPosition.y - GIGAGAL_EYE_POSITION.y)
    }


}