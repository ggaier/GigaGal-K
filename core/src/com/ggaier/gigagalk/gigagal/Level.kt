package com.ggaier.gigagalk.gigagal

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.DelayedRemovalArray
import com.badlogic.gdx.utils.viewport.Viewport
import com.ggaier.gigagalk.gigagal.entity.Bullet
import com.ggaier.gigagalk.gigagal.entity.Enemy
import com.ggaier.gigagalk.gigagal.entity.Gigagal
import com.ggaier.gigagalk.gigagal.entity.Platform
import com.ggaier.gigagalk.gigagal.util.Enums

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 * Level 类是一个管理游戏角色，游戏中的敌人，和平台，以及子弹等等的类。负责他们的更新和渲染。
 */
class Level(val mViewport: Viewport) {

    val mGigagal: Gigagal
    val mEnemies: DelayedRemovalArray<Enemy> = DelayedRemovalArray()
    val mBullets: DelayedRemovalArray<Bullet> = DelayedRemovalArray()

    private val mPlatforms = Array<Platform>()

    init {
        //        mPlatforms.add(Platform(70f, 30f, 20f, 20f))
        initDebugLevel()
        mGigagal = Gigagal(Vector2(15f, 40f), this)
    }

    private fun initDebugLevel() {
        mPlatforms.add(Platform(15f, 100f, 30f, 20f))
        val enemyPlatform = Platform(75f, 90f, 100f, 65f)
        mEnemies.add(Enemy(enemyPlatform))
        mPlatforms.add(enemyPlatform)
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
        mBullets.begin()
        mBullets.forEach {
            it.update(delta)
            if(!it.mActive){
                mBullets.removeValue(it,false)
            }
        }
        mBullets.end()
        mEnemies.forEach { it.update(delta) }
    }

    fun spawnBullet(position: Vector2, direction: Enums.Direction) {
        mBullets.add(Bullet(position,direction))
    }

    fun render(batch: SpriteBatch) {
        for (platform in mPlatforms) {
            platform.render(batch)
        }
        for (enemy in mEnemies) {
            enemy.render(batch)
        }
        mGigagal.render(batch)
        mBullets.forEach { it.render(batch) }
    }
}