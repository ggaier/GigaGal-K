package com.ggaier.gigagalk.gigagal.util

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.TimeUtils

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
object Utils {

    fun drawTextureRegions(batch: SpriteBatch,
                           region: TextureRegion,
                           position: Vector2,
                           offset: Vector2) {
        drawTextureRegions(batch, region, position.x - offset.x,
                position.y - offset.y)

    }

    fun drawTextureRegion(batch: SpriteBatch, region: TextureRegion, position: Vector2) {
        drawTextureRegions(batch, region, position.x, position.y)
    }

    fun drawTextureRegions(batch: SpriteBatch, region: TextureRegion,
                           x: Float, y: Float) {
        batch.draw(region.texture,
                x,
                y,
                0f, 0f,
                region.regionWidth.toFloat(), region.regionHeight.toFloat(),
                1f, 1f, 0f,
                region.regionX, region.regionY,
                region.regionWidth, region.regionHeight,
                false, false)

    }

    fun secondsSince(timeNanos: Long) = MathUtils.nanoToSec * (TimeUtils.nanoTime() -
            timeNanos)

}