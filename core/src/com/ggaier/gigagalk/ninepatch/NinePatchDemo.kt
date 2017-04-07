package com.ggaier.gigagalk.ninepatch

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport

/**
 * Created by ggaier at 07/04/2017 .
 * jwenbo52@gmail.com
 */
private const val WORLD_SIZE = 100f
private const val EDGE = 8
private const val TEST_SIZE_1 = 20f
private const val TEST_SIZE_2 = 40f

class NinePatchDemo : ApplicationAdapter() {

    /**
     * Sprite 表示的是一个持有Texture的对象，它包含了这个texture对象应该在游戏世界的
     * 哪个地方绘制。
     */
    lateinit var mBatch: SpriteBatch
    lateinit var mViewport: FitViewport
    lateinit var mPlatformTexture: Texture
    lateinit var mPlatformNinePatch: NinePatch

    override fun create() {
        mBatch = SpriteBatch()
        mViewport = FitViewport(WORLD_SIZE, WORLD_SIZE)
        mPlatformTexture = Texture("platform.png")

        mPlatformNinePatch = NinePatch(mPlatformTexture, EDGE, EDGE, EDGE, EDGE)
    }

    override fun resize(width: Int, height: Int) {
        mViewport.update(width, height, true)

    }


    override fun render() {
        mViewport.apply()
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mBatch.projectionMatrix = mViewport.camera.combined
        mBatch.begin()
        mBatch.draw(mPlatformTexture, WORLD_SIZE * 1 / 4 - TEST_SIZE_1 / 2,
                WORLD_SIZE * 1 / 4 - TEST_SIZE_1 / 2,
                TEST_SIZE_1, TEST_SIZE_1)

        mPlatformNinePatch.draw(mBatch, WORLD_SIZE * 3 / 4 - TEST_SIZE_2 / 2, WORLD_SIZE * 3 / 4 -
                TEST_SIZE_2 / 2, TEST_SIZE_2, TEST_SIZE_2)
        mBatch.end()
    }


    override fun dispose() {
        mBatch.dispose()
        mPlatformTexture.dispose()
    }



}
