package com.ggaier.gigagalk.gigagal

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.ggaier.gigagalk.gigagal.overlays.GigagalHud
import com.ggaier.gigagalk.gigagal.util.Assets
import com.ggaier.gigagalk.gigagal.util.BACKGROUND_COLOR
import com.ggaier.gigagalk.gigagal.util.ChaseCam

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 */
class GamePlayScreen : ScreenAdapter() {

    private val mBatch: SpriteBatch = SpriteBatch()
    private val mGigagalHud = GigagalHud()

    private lateinit var mChaseCam: ChaseCam
    private lateinit var mLevel: Level

    override fun show() {
        startNewLevel()
    }

    private fun startNewLevel() {
        mLevel = Level.debugLevel()
        mChaseCam = ChaseCam(mLevel.mViewport.camera, mLevel.mGigagal)
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
        mGigagalHud.render(mBatch, mLevel.mGigagal.mLives, mLevel.mGigagal.mAmmo, mLevel.mScore)
    }


    override fun resize(width: Int, height: Int) {
        mGigagalHud.mViewport.update(width, height, true)
        mLevel.mViewport.update(width, height, true)
        mChaseCam.mCamera = mLevel.mViewport.camera
    }

    override fun dispose() {
        Assets.dispose()
        mBatch.dispose()
    }


}