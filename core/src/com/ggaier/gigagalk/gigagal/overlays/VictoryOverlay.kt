package com.ggaier.gigagalk.gigagal.overlays

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.ggaier.gigagalk.gigagal.entity.Explosion
import com.ggaier.gigagalk.gigagal.util.EXPLOSION_COUNT
import com.ggaier.gigagalk.gigagal.util.LEVEL_END_DURATION
import com.ggaier.gigagalk.gigagal.util.VICTORY_MESSAGE
import com.ggaier.gigagalk.gigagal.util.WORLD_SIZE

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class VictoryOverlay() {

    val mViewport: Viewport = ExtendViewport(WORLD_SIZE, WORLD_SIZE)
    val mFont: BitmapFont = BitmapFont()
    private lateinit var mExplosions: Array<Explosion>

    init {
        mFont.data.setScale(1f)
    }

    fun init() {
        mExplosions = Array(EXPLOSION_COUNT)
        (1..EXPLOSION_COUNT).forEach {
            val explosion = Explosion(Vector2(MathUtils.random(mViewport.worldWidth),
                    MathUtils.random(mViewport.worldHeight)))
            explosion.mOffset = MathUtils.random(LEVEL_END_DURATION)
            mExplosions.add(explosion)
        }
    }

    fun render(batch: SpriteBatch) {
        mViewport.apply()
        batch.projectionMatrix = mViewport.camera.combined
        batch.begin()
        mExplosions.forEach {
            it.render(batch)
        }
        mFont.draw(batch, VICTORY_MESSAGE, mViewport.worldWidth / 2,
                mViewport.worldHeight / 2.5f,
                0f,
                Align.center, false)
        batch.end()
    }


}
