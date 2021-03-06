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
import java.awt.TextArea

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 */
object Assets : Disposable, AssetErrorListener {

    private var mAssetsManager: AssetManager = AssetManager()
    val mGigagalAssets: GigagalAsset
    val mPlatformAssets: PlatformAssets
    val mEnemyAssets: EnemyAssets
    val mBulletAssets: BulletAssets
    val mExplosionAssets: ExplosionAssets
    val mPowerupAssets: PowerupAssets
    val mExitPortalAssets: ExitPortAssets
    val mOnScreenControlsAssets: OnScreenControlsAssets

    init {
        mAssetsManager.setErrorListener(this)
        mAssetsManager.load(TEXTURE_ATLAS, TextureAtlas::class.java)
        mAssetsManager.finishLoading()
        val atlas: TextureAtlas = mAssetsManager.get(TEXTURE_ATLAS)
        mGigagalAssets = GigagalAsset(atlas)
        mPlatformAssets = PlatformAssets(atlas)
        mEnemyAssets = EnemyAssets(atlas)
        mBulletAssets = BulletAssets(atlas)
        mExplosionAssets = ExplosionAssets(atlas)
        mPowerupAssets = PowerupAssets(atlas)
        mExitPortalAssets = ExitPortAssets(atlas)
        mOnScreenControlsAssets = OnScreenControlsAssets(atlas)
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

    class BulletAssets(atlas: TextureAtlas) {
        val mBullet = atlas.findRegion(BULLET_SPRITE)
    }

    class ExplosionAssets(atlas: TextureAtlas) {
        val mExplosion: Animation<TextureAtlas.AtlasRegion>

        init {
            val explosionRegions = Array<TextureAtlas.AtlasRegion>()
            explosionRegions.add(atlas.findRegion(EXPLOSION_LARGE))
            explosionRegions.add(atlas.findRegion(EXPLOSION_MEDIUM))
            explosionRegions.add(atlas.findRegion(EXPLOSION_SMALL))
            mExplosion = Animation(EXPLOSION_DURATION / explosionRegions.size, explosionRegions,
                    Animation.PlayMode.NORMAL)
        }

    }

    class PowerupAssets(atlas: TextureAtlas) {
        val mPowerup = atlas.findRegion(POWERUP_SPRITE)
    }

    class ExitPortAssets(atlas: TextureAtlas) {

        val mExitPortalAnimation: Animation<TextureAtlas.AtlasRegion>

        init {
            val exportFrames = Array<TextureAtlas.AtlasRegion>()
            val ep1 = atlas.findRegion(EXIT_PORTAL_SPRITE_1)
            val ep2 = atlas.findRegion(EXIT_PORTAL_SPRITE_2)
            val ep3 = atlas.findRegion(EXIT_PORTAL_SPRITE_3)
            val ep4 = atlas.findRegion(EXIT_PORTAL_SPRITE_4)
            val ep5 = atlas.findRegion(EXIT_PORTAL_SPRITE_5)
            val ep6 = atlas.findRegion(EXIT_PORTAL_SPRITE_6)
            exportFrames.addAll(ep1, ep2, ep3, ep4, ep5, ep6)
            mExitPortalAnimation = Animation(EXIT_PORTAL_FRAME_DURATION, exportFrames)
        }
    }

    class OnScreenControlsAssets(val mAtlas: TextureAtlas) {

        val mMoveRight: TextureAtlas.AtlasRegion = mAtlas.findRegion(MOVE_RIGHT_BUTTON)
        val mMoveLeft: TextureAtlas.AtlasRegion = mAtlas.findRegion(MOVE_LEFT_BUTTON)
        val mShoot: TextureAtlas.AtlasRegion = mAtlas.findRegion(SHOOT_BUTTON)
        val mJump: TextureAtlas.AtlasRegion = mAtlas.findRegion(JUMP_BUTTON)
    }

}