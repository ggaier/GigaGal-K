package com.ggaier.gigagalk.gigagal.overlays

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.TimeUtils
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.ggaier.gigagalk.gigagal.entity.Enemy
import com.ggaier.gigagalk.gigagal.entity.Platform
import com.ggaier.gigagalk.gigagal.util.*

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class GameOverOverlay() {

    val mViewport: Viewport = ExtendViewport(WORLD_SIZE, WORLD_SIZE)
    private val mFont = BitmapFont(Gdx.files.internal(FONT_FILE))
    private var mStartTime = 0L
    private lateinit var mEnemies: Array<Enemy>

    init {
        mFont.data.setScale(1f)
        init()
    }

    fun init() {
        mStartTime = TimeUtils.nanoTime()
        mEnemies = Array<Enemy>(ENEMY_COUNT)
        (1..ENEMY_COUNT).map {
            val platForm = Platform(MathUtils.random(mViewport.worldWidth),
                    MathUtils.random(mViewport.worldHeight), 0f, 0f)
            Enemy(platForm)
        }.forEach {
            mEnemies.add(it)
        }
    }

    fun render(batch: SpriteBatch) {
        mViewport.apply()
        batch.projectionMatrix = mViewport.camera.combined
        batch.begin()

        val timeElapsed = Utils.secondsSince(mStartTime)
        val enemiesToShow = (ENEMY_COUNT * (timeElapsed / LEVEL_END_DURATION)).toInt()

        (0..enemiesToShow - 1).forEach {
            mEnemies[it].update(0f)
            mEnemies[it].render(batch)
        }

        mFont.draw(batch, GAME_OVER_MESSAGE, mViewport.worldWidth / 2, mViewport.worldHeight / 2.5f,
                0f, Align.center, false)

        batch.end()
    }

}
