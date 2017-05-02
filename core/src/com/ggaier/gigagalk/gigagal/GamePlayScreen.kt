package com.ggaier.gigagalk.gigagal

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.ggaier.gigagalk.gigagal.util.Assets
import com.ggaier.gigagalk.gigagal.util.BACKGROUND_COLOR
import com.ggaier.gigagalk.gigagal.util.ChaseCam
import com.ggaier.gigagalk.gigagal.util.WORLD_SIZE

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 */
class GamePlayScreen : ScreenAdapter() {

    private lateinit var mBatch: SpriteBatch
    private lateinit var mViewport: ExtendViewport
    private lateinit var mLevel: Level
    private lateinit var mShapeRenderer: ShapeRenderer

    private lateinit var mChaseCam: ChaseCam

    override fun show() {
        mBatch = SpriteBatch()
        mViewport = ExtendViewport(WORLD_SIZE, WORLD_SIZE)
        mLevel = Level()
        mShapeRenderer = ShapeRenderer()
        mShapeRenderer.setAutoShapeType(true)
        mChaseCam = ChaseCam(mViewport.camera, mLevel.mGigagal)
    }

    override fun render(delta: Float) {
        mLevel.update(delta)
        mChaseCam.update(delta)

        mViewport.apply()
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r,
                BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b,
                BACKGROUND_COLOR.a)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mBatch.projectionMatrix = mViewport.camera.combined
        mShapeRenderer.projectionMatrix = mViewport.camera.combined
        mBatch.begin()
        mLevel.render(mBatch, mShapeRenderer)
        mBatch.end()
    }


    override fun resize(width: Int, height: Int) {
        mViewport.update(width, height, true)
    }

    override fun dispose() {
        Assets.dispose()
        mBatch.dispose()
    }


}