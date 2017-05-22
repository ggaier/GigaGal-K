package com.ggaier.gigagalk.gigagal.overlays

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.ggaier.gigagalk.gigagal.util.*

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class GigagalHud() {

    val mViewport = ExtendViewport(HUD_VIEWPORT_SIZE, HUD_VIEWPORT_SIZE)
    val mFont = BitmapFont()

    init {
        mFont.data.setScale(1f)
    }

    fun render(batch: SpriteBatch, lives: Int, ammo: Int, score: Int) {
        mViewport.apply()
        batch.begin()

        val hud = "$HUD_SCORE_LABEL$score\n$HUD_AMMO_LABEL$ammo"
        mFont.draw(batch, hud, HUD_MARGIN, mViewport.worldWidth - HUD_MARGIN)
        val standRight = Assets.mGigagalAssets.mStandRight
        (1..lives)
                .map {
                    Vector2(
                            mViewport.worldWidth - it * (HUD_MARGIN / 2 + standRight.regionWidth),
                            mViewport.worldHeight - HUD_MARGIN - standRight.regionHeight)
                }
                .forEach { Utils.drawTextureRegion(batch, standRight, it) }

        batch.end()

    }

}