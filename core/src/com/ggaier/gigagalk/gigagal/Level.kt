package com.ggaier.gigagalk.gigagal

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.DelayedRemovalArray
import com.ggaier.gigagalk.gigagal.entity.Enemy
import com.ggaier.gigagalk.gigagal.entity.Gigagal
import com.ggaier.gigagalk.gigagal.entity.Platform
import com.ggaier.gigagalk.gigagal.util.*

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 * Level 类是一个管理游戏角色，游戏中的敌人，和平台，以及子弹等等的类。负责他们的更新和渲染。
 */
class Level {

    val mGigagal: Gigagal
    val mEnemies: DelayedRemovalArray<Enemy> = DelayedRemovalArray()

    private val mPlatforms = Array<Platform>()

    init {
        //        mPlatforms.add(Platform(70f, 30f, 20f, 20f))
        addDebugPlatforms()
        mGigagal = Gigagal(Vector2(15f, 40f),this)
    }

    private fun addDebugPlatforms() {
        mPlatforms.add(Platform(15f, 100f, 30f, 20f))
        val platform = Platform(75f, 90f, 100f, 65f)
        mEnemies.add(Enemy(platform))
        mPlatforms.add(platform)
        mPlatforms.add(Platform(35f, 55f, 50f, 20f))
        mPlatforms.add(Platform(10f, 20f, 20f, 9f))
        //        mPlatforms.add(Platform(100f, 110f, 30f, 9f))
        //        mPlatforms.add(Platform(200f, 130f, 30f, 40f))
        //        mPlatforms.add(Platform(150f, 150f, 30f, 9f))
        //        mPlatforms.add(Platform(150f, 180f, 30f, 9f))
        //        mPlatforms.add(Platform(200f, 200f, 9f, 9f))
        //        mPlatforms.add(Platform(280f, 100f, 30f, 9f))
    }

    fun update(delta: Float) {
        mGigagal.update(delta, mPlatforms)
        for (enemy in mEnemies) {
            enemy.update(delta)
        }
    }

    fun render(batch: SpriteBatch, renderer: ShapeRenderer) {
        for (platform in mPlatforms) {
            platform.render(batch)
        }
        for (enemy in mEnemies) {
            enemy.render(batch)
        }
        mGigagal.render(batch)

        Utils.drawTextureRegions(batch,Assets.mBulletAssets.mBullet,
                Vector2(0f,0f),
                BULLET_CENTER)

        Utils.drawTextureRegions(batch,Assets.mPowerupAssets.mPowerup,
                Vector2(20f,0f), POWERUP_CENTER)

        Utils.drawTextureRegions(batch,Assets.mExplosionAssets.mExplosion.getKeyFrame(0f),
                Vector2(40f,0f),
                EXPLOSION_CENTER)

        Utils.drawTextureRegions(batch,Assets.mExplosionAssets.mExplosion.getKeyFrame(
                EXPLOSION_DURATION*0.5f),
                Vector2(60f,0f),
                EXPLOSION_CENTER)

        Utils.drawTextureRegions(batch,Assets.mExplosionAssets.mExplosion.getKeyFrame(
                EXPLOSION_DURATION*0.75f),
                Vector2(80f,0f),
                EXPLOSION_CENTER)

    }
}