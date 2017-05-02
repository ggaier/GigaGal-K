package com.ggaier.gigagalk.gigagal.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetErrorListener
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Disposable

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 */
object Assets : Disposable, AssetErrorListener {

    private var mAssetsManager: AssetManager = AssetManager()
    val mGigagalAssets: GigagalAsset
    val mPlatformAssets: PlatformAssets
    val mEnemyAssets: EnemyAssets

    init {
        mAssetsManager.setErrorListener(this)
        mAssetsManager.load(TEXTURE_ATLAS, TextureAtlas::class.java)
        mAssetsManager.finishLoading()
        val atlas: TextureAtlas = mAssetsManager.get(TEXTURE_ATLAS)
        mGigagalAssets = GigagalAsset(atlas)
        mPlatformAssets = PlatformAssets(atlas)
        mEnemyAssets = EnemyAssets(atlas)
    }

    override fun error(asset: AssetDescriptor<*>?, throwable: Throwable?) {
        Gdx.app.error("Assets", "Couldn't load asset: ${asset?.fileName}", throwable)

    }

    override fun dispose() {
        mAssetsManager.dispose()
    }

    class GigagalAsset(atlas: TextureAtlas) {

        val mStandRight: TextureAtlas.AtlasRegion = atlas.findRegion(STANDING_RIGHT)
        val mStandLeft: TextureAtlas.AtlasRegion = atlas.findRegion(STANDING_LEFT)
        val mJumpingLeft: TextureAtlas.AtlasRegion = atlas.findRegion(JUMPING_LEFT)
        val mJumpingRight: TextureAtlas.AtlasRegion = atlas.findRegion(JUMPING_RIGHT)
        val mWalkingRight: TextureAtlas.AtlasRegion = atlas.findRegion(WALKING_RIGHT_2)
        val mWalkingLeft: TextureAtlas.AtlasRegion = atlas.findRegion(WALKING_LEFT_2)
        val mWalkingLeftAnimation: Animation<TextureAtlas.AtlasRegion>
        val mWalkingRightAnimation: Animation<TextureAtlas.AtlasRegion>

        init {
            val walkingLeftFrames = Array<TextureAtlas.AtlasRegion>()
            walkingLeftFrames.add(atlas.findRegion(WALKING_LEFT_2))
            walkingLeftFrames.add(atlas.findRegion(WALKING_LEFT_1))
            walkingLeftFrames.add(atlas.findRegion(WALKING_LEFT_2))
            walkingLeftFrames.add(atlas.findRegion(WALKING_LEFT_3))
            mWalkingLeftAnimation = Animation(WALKING_LOOP_DURATION, walkingLeftFrames,
                    Animation.PlayMode.LOOP)

            val walkingRightFrames = Array<TextureAtlas.AtlasRegion>()
            walkingRightFrames.add(atlas.findRegion(WALKING_RIGHT_2))
            walkingRightFrames.add(atlas.findRegion(WALKING_RIGHT_1))
            walkingRightFrames.add(atlas.findRegion(WALKING_RIGHT_2))
            walkingRightFrames.add(atlas.findRegion(WALKING_RIGHT_3))
            mWalkingRightAnimation = Animation(WALKING_LOOP_DURATION, walkingRightFrames,
                    Animation.PlayMode.LOOP)
        }

    }

    class PlatformAssets(atlas: TextureAtlas) {

        val mPlatformNinePatch: NinePatch

        init {
            val region = atlas.findRegion(PLATFORM_SPRITE)
            val edge = PLATFORM_EDGE
            mPlatformNinePatch = NinePatch(region, edge, edge, edge, edge)
        }
    }

    class EnemyAssets(atlas: TextureAtlas) {

        val mEnemy: TextureAtlas.AtlasRegion = atlas.findRegion(ENEMY_SPRITE)


    }

}