package com.ggaier.gigagalk.gigagal.entity

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.TimeUtils
import com.ggaier.gigagalk.gigagal.util.*

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class Enemy(val mPlatform: Platform) {

    val mPosition: Vector2 = Vector2(mPlatform.mLeft,
            mPlatform.mTop + ENEMY_CENTER.y)
    private var mDirection: Enums.Direction = Enums.Direction.RIGHT;
    private val mStartTime = TimeUtils.nanoTime()

    fun update(delta: Float) {
        when (mDirection) {
            Enums.Direction.RIGHT -> mPosition.x += delta * ENEMY_MOVING_SPEED
            Enums.Direction.LEFT -> mPosition.x -= delta * ENEMY_MOVING_SPEED
        }

        if (mPosition.x < mPlatform.mLeft) {
            mPosition.x = mPlatform.mLeft
            mDirection = Enums.Direction.RIGHT
        } else if (mPosition.x > mPlatform.mRright) {
            mPosition.x = mPlatform.mRright;
            mDirection = Enums.Direction.LEFT
        }

        val elapsedTime = Utils.secondsSince(mStartTime)
        val bobMultiplier = 1 + MathUtils.sin(MathUtils.PI2 * elapsedTime / ENEMY_BOB_PERIOD)
        mPosition.y = mPlatform.mTop + ENEMY_CENTER.y + ENEMY_BOB_AMPLITUDE * bobMultiplier
    }

    /**
     * 渲染Enemy自身的方法。
     */
    fun render(batch: SpriteBatch) {
        Utils.drawTextureRegions(batch,
                Assets.mEnemyAssets.mEnemy, mPosition,
                ENEMY_CENTER)
    }


}
