package com.ggaier.gigagalk.gigagal.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ggaier.gigagalk.gigagal.util.Assets
import com.ggaier.gigagalk.gigagal.util.GIGAGAL_EYE_HEIGHT
import com.ggaier.gigagalk.gigagal.util.GIGAGAL_EYE_POSITION
import com.ggaier.gigagalk.gigagal.util.GIGAGAL_MOVING_SPEED

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 */
class Gigagal {

    val mPosition: Vector2 = Vector2(20f, GIGAGAL_EYE_HEIGHT)
    private var mFacing: Facing = Facing.RIGHT

    public fun update(delta: Float) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveLeft(delta)
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveRight(delta)
        }
    }

    private fun moveLeft(delta: Float) {
        mFacing = Facing.LEFT
        mPosition.x -= delta * GIGAGAL_MOVING_SPEED
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
}