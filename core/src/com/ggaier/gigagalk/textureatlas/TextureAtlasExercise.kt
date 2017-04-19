package com.ggaier.gigagalk.textureatlas

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetErrorListener
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas

/**
 * Created by ggaier at 19/04/2017 .
 * jwenbo52@gmail.com
 */
class TextureAtlasExercise : ApplicationAdapter(), AssetErrorListener {

    @JvmField
    val TAG = "TextureAtlasExercise"

    companion object {
        @JvmStatic
        private val ATLAS = "images/gigagal.pack.atlas"

        @JvmStatic
        private val STANDING_RIGHT = "standing-right"
    }

    private lateinit var mAssetManager: AssetManager
    private lateinit var mBatch: SpriteBatch
    private lateinit var mStandRight: TextureAtlas.AtlasRegion

    override fun create() {
        mBatch = SpriteBatch()
        mAssetManager = AssetManager()
        mAssetManager.setErrorListener(this)
        mAssetManager.load(ATLAS, TextureAtlas::class.java)
        mAssetManager.finishLoading()

        val atlas: TextureAtlas = mAssetManager.get(ATLAS)
        mStandRight = atlas.findRegion(STANDING_RIGHT)
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        mBatch.begin()
        mBatch.draw(mStandRight.texture, 0f, 0f, 0f, 0f,
                mStandRight.regionWidth.toFloat()
                , mStandRight.regionHeight.toFloat(),
                1f, 1f, 0f,
                mStandRight.regionX, mStandRight.regionY,
                mStandRight.regionWidth, mStandRight.regionHeight,
                false, false)
        mBatch.end()
    }

    override fun error(asset: AssetDescriptor<*>?, throwable: Throwable?) {
        Gdx.app.error(TAG, "Couldn't load asset: " + asset?.fileName, throwable)
    }

    override fun dispose() {
        mBatch.dispose()
        mAssetManager.dispose()
    }

}