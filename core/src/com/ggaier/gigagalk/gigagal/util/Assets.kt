package com.ggaier.gigagalk.gigagal.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetErrorListener
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.utils.Disposable

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 */
object Assets : Disposable, AssetErrorListener {

    inline fun getInstance(): Assets = this

    private var mAssetsManager: AssetManager = AssetManager()
    val mGigagalAssets: GigagalAsset

    init {
        mAssetsManager.setErrorListener(this)
        mAssetsManager.load(TEXTURE_ATLAS, TextureAtlas::class.java)
        mAssetsManager.finishLoading()
        val atlas: TextureAtlas = mAssetsManager.get(TEXTURE_ATLAS)
        mGigagalAssets = GigagalAsset(atlas)
    }

    override fun error(asset: AssetDescriptor<*>?, throwable: Throwable?) {
        Gdx.app.error("Assets", "Couldn't load asset: ${asset?.fileName}", throwable)

    }

    override fun dispose() {
        mAssetsManager.dispose()
    }

    class GigagalAsset(atlas: TextureAtlas) {

        val mStandRight: TextureAtlas.AtlasRegion = atlas.findRegion(STANDING_RIGHT)

    }

}