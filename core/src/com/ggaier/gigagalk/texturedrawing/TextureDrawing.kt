package com.ggaier.gigagalk.texturedrawing

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.ExtendViewport

/**
 * Created by ggaier at 30/03/2017 .
 * jwenbo52@gmail.com
 */
const val WORLD_SIZE = 100.0f
const val LOGO_SIZE = 0.5f * WORLD_SIZE

class TextureDrawing : ApplicationAdapter() {
    val UDACITY_ORANGE: Color = Color(228.0f / 225.0f, 127.0f / 225.0f, 57.0f / 225.0f, 1.0f)

    lateinit var mViewport: ExtendViewport
    lateinit var mBatch: SpriteBatch
    lateinit var mUdacityLogo: Texture

    override fun create() {
        mViewport = ExtendViewport(WORLD_SIZE, WORLD_SIZE)
        mBatch = SpriteBatch()
        //此处的internal路径指的是相对android/assets的路径
        mUdacityLogo = Texture("udacity_logo_white.png")
    }

    override fun render() {
        mViewport.apply()
        Gdx.gl.glClearColor(UDACITY_ORANGE.r, UDACITY_ORANGE.g, UDACITY_ORANGE.b, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mBatch.projectionMatrix = mViewport.camera.combined
        mBatch.begin()
        mBatch.draw(mUdacityLogo, (mViewport.worldWidth - LOGO_SIZE) / 2,
                (mViewport.worldHeight - LOGO_SIZE) / 2, LOGO_SIZE, LOGO_SIZE)
        mBatch.end()
    }

    override fun resize(width: Int, height: Int) {
        mViewport.update(width, height, true)
    }


}