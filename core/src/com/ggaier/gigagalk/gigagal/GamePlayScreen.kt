package com.ggaier.gigagalk.gigagal

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.TimeUtils
import com.ggaier.gigagalk.gigagal.overlays.GameOverOverlay
import com.ggaier.gigagalk.gigagal.overlays.GigagalHud
import com.ggaier.gigagalk.gigagal.overlays.OnScreenControls
import com.ggaier.gigagalk.gigagal.overlays.VictoryOverlay
import com.ggaier.gigagalk.gigagal.util.*

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 */
class GamePlayScreen : ScreenAdapter() {

    private val mBatch: SpriteBatch = SpriteBatch()
    private val mGigagalHud = GigagalHud()

    private lateinit var mChaseCam: ChaseCam
    private lateinit var mLevel: Level
    private lateinit var mVictoryOverlay: VictoryOverlay
    private lateinit var mGameOverOverlay: GameOverOverlay
    private lateinit var mOnScreenControls: OnScreenControls

    private var mEndOverlayStartTime: Long = 0L


    override fun show() {
        mVictoryOverlay = VictoryOverlay()
        mGameOverOverlay = GameOverOverlay()
        mOnScreenControls = OnScreenControls()
        if (onMobile())
            Gdx.app.input.inputProcessor = mOnScreenControls
        startNewLevel()
    }

    fun onMobile(): Boolean = Gdx.app.type == Application.ApplicationType.Android ||
            Gdx.app.type == Application.ApplicationType.iOS

    private fun startNewLevel() {
        //        mLevel = Level.debugLevel()
        val levelName = LEVELS[MathUtils.random(LEVELS.size - 1)]
        mLevel = LevelLoader.load(levelName)
        mChaseCam = ChaseCam(mLevel.mViewport.camera, mLevel.mGigagal)
        mOnScreenControls.mGigagal = mLevel.mGigagal
        resize(Gdx.graphics.width, Gdx.graphics.height)
    }

    override fun render(delta: Float) {
        mLevel.update(delta)
        mChaseCam.update(delta)

        Gdx.gl.glClearColor(BACKGROUND_COLOR.r,
                BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b,
                BACKGROUND_COLOR.a)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mLevel.render(mBatch)
        mOnScreenControls.render(mBatch)
        mGigagalHud.render(mBatch, mLevel.mGigagal.mLives, mLevel.mGigagal.mAmmo, mLevel.mScore)
        renderLevelEndOverlays(mBatch)
    }

    fun renderLevelEndOverlays(batch: SpriteBatch) {
        if (mLevel.mVictory) {
            if (mEndOverlayStartTime == 0L) {
                mEndOverlayStartTime = TimeUtils.nanoTime()
                mVictoryOverlay.init()
            }
            mVictoryOverlay.render(batch)
            if (Utils.secondsSince(mEndOverlayStartTime) > LEVEL_END_DURATION) {
                mEndOverlayStartTime = 0L
                levelComplete()
            }
        }

        if (mLevel.mGameOver) {
            if (mEndOverlayStartTime == 0L) {
                mEndOverlayStartTime = TimeUtils.nanoTime()
                mGameOverOverlay.init()
            }
            mGameOverOverlay.render(batch)
            if (Utils.secondsSince(mEndOverlayStartTime) > LEVEL_END_DURATION) {
                mEndOverlayStartTime = 0L
                levelFailed()

            }
        }

    }

    fun levelFailed() {
        startNewLevel()
    }

    private fun levelComplete() {
        startNewLevel()
    }


    override fun resize(width: Int, height: Int) {
        mGigagalHud.mViewport.update(width, height, true)
        mLevel.mViewport.update(width, height, true)
        mVictoryOverlay.mViewport.update(width, height, true)
        mChaseCam.mCamera = mLevel.mViewport.camera
        mGameOverOverlay.mViewport.update(width, height, true)
        mOnScreenControls.mViewport.update(width, height, true)
        mOnScreenControls.recalculateButtonPositions()
    }

    override fun dispose() {
        Assets.dispose()
    }


}