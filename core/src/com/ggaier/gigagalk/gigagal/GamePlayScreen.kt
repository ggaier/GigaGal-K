package com.ggaier.gigagalk.gigagal

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.ggaier.gigagalk.gigagal.util.*

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 */
class GamePlayScreen : ScreenAdapter() {

    private lateinit var mBatch: SpriteBatch
    private lateinit var mGamePlayViewport: ExtendViewport
    private lateinit var mLevel: Level
    private lateinit var mShapeRenderer: ShapeRenderer

    private lateinit var mChaseCam: ChaseCam

    override fun show() {
        mBatch = SpriteBatch()
        mGamePlayViewport = ExtendViewport(WORLD_SIZE, WORLD_SIZE)
        mLevel = LevelLoader.load("Level1", mGamePlayViewport)
        mShapeRenderer = ShapeRenderer()
        mShapeRenderer.setAutoShapeType(true)
        mChaseCam = ChaseCam(mGamePlayViewport.camera, mLevel.mGigagal)
    }

    override fun render(delta: Float) {
        mLevel.update(delta)
        mChaseCam.update(delta)

        mGamePlayViewport.apply()
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r,
                BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b,
                BACKGROUND_COLOR.a)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mBatch.projectionMatrix = mGamePlayViewport.camera.combined
        mShapeRenderer.projectionMatrix = mGamePlayViewport.camera.combined
        mBatch.begin()
        mLevel.render(mBatch)
        mBatch.end()
    }


    override fun resize(width: Int, height: Int) {
        mGamePlayViewport.update(width, height, true)
    }

    override fun dispose() {
        Assets.dispose()
        mBatch.dispose()
    }


}