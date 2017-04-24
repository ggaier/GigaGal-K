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

    public fun update(delta: Float) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveLeft(delta)
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveRight(delta)
        }
    }

    private fun moveLeft(delta: Float) {
        mPosition.x -= delta * GIGAGAL_MOVING_SPEED
    }

    private fun moveRight(delta: Float) {
        mPosition.x += delta * GIGAGAL_MOVING_SPEED
    }


    public fun render(batch: SpriteBatch) {
        val region = Assets.mGigagalAssets.mStandRight
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
}