package com.ggaier.gigagalk.gigagal.entity

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ggaier.gigagalk.gigagal.Level
import com.ggaier.gigagalk.gigagal.util.*

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class Bullet(val mLevel: Level, val mPosition: Vector2, val mDirection: Enums.Direction) {

    var mActive: Boolean = true

    fun update(delta: Float) {
        when (mDirection) {
            Enums.Direction.LEFT -> mPosition.x -= delta * BULLET_MOVE_SPEED
            Enums.Direction.RIGHT -> mPosition.x += delta * BULLET_MOVE_SPEED
        }
        val worldWidth = mLevel.mViewport.worldWidth
        val cameraCenterX = mLevel.mViewport.camera.position.x

        //todo: 不理解
        if (mPosition.x < cameraCenterX - worldWidth / 2||mPosition.x>
                cameraCenterX+worldWidth/2) {
            mActive=false
        }
    }

    fun render(batch: SpriteBatch) {
        Utils.drawTextureRegions(batch, Assets.mBulletAssets.mBullet,
                mPosition, BULLET_CENTER)
    }

}