package com.ggaier.gigagalk.gigagal

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.DelayedRemovalArray
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.ggaier.gigagalk.gigagal.entity.*
import com.ggaier.gigagalk.gigagal.util.ENEMY_KILL_SCORE
import com.ggaier.gigagalk.gigagal.util.EXIT_PORTAL_DEFAULT_LOCATION
import com.ggaier.gigagalk.gigagal.util.Enums
import com.ggaier.gigagalk.gigagal.util.WORLD_SIZE

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 * Level 类是一个管理游戏角色，游戏中的敌人，和平台，以及子弹等等的类。负责他们的更新和渲染。
 */
class Level() {

    val mEnemies: DelayedRemovalArray<Enemy> = DelayedRemovalArray()
    val mBullets: DelayedRemovalArray<Bullet> = DelayedRemovalArray()
    val mExplosions: DelayedRemovalArray<Explosion> = DelayedRemovalArray()
    val mPowerups: DelayedRemovalArray<Powerup> = DelayedRemovalArray()
    val mPlatforms = Array<Platform>()
    val mViewport = ExtendViewport(WORLD_SIZE, WORLD_SIZE)

    var mGigagal: Gigagal = Gigagal(Vector2(50f, 50f), this)
    var mExitPortal: ExitPortal = ExitPortal(EXIT_PORTAL_DEFAULT_LOCATION)

    var mGameOver = false
        private set
    var mScore = 0
        private set
    var mVictory = false
        private set

    companion object {

        @JvmStatic
        fun debugLevel(): Level {
            val level = Level()
            level.initDebugLevel()
            return level
        }

    }

    private fun initDebugLevel() {
        mGigagal = Gigagal(Vector2(15f, 40f), this)
        mExitPortal = ExitPortal(Vector2(150f, 150f))

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
        mPowerups.add(Powerup(Vector2(20f, 110f)))
    }

    fun update(delta: Float) {
        mGigagal.update(delta, mPlatforms)
        mBullets.begin()
        mBullets.forEach {
            it.update(delta)
            if (!it.mActive) {
                mBullets.removeValue(it, false)
            }
        }
        mBullets.end()
        mEnemies.begin()
        mEnemies.forEach {
            it.update(delta)
            if (it.mHealth < 1) {
                mEnemies.removeValue(it, false)
                mScore += ENEMY_KILL_SCORE
                spawnExplosion(it.mPosition)
            }
        }
        mEnemies.end()

        mExplosions.begin()
        mExplosions.forEach {
            if (it.isFinished()) {
                mExplosions.removeValue(it, false)
            }
        }
        mExplosions.end()
    }

    fun spawnBullet(position: Vector2, direction: Enums.Direction) {
        mBullets.add(Bullet(this, position, direction))
    }

    fun render(batch: SpriteBatch) {
        mViewport.apply()
        batch.projectionMatrix = mViewport.camera.combined
        batch.begin()
        for (platform in mPlatforms) {
            platform.render(batch)
        }
        for (enemy in mEnemies) {
            enemy.render(batch)
        }
        mGigagal.render(batch)
        mBullets.forEach { it.render(batch) }
        mEnemies.forEach { it.render(batch) }
        mExplosions.forEach { it.render(batch) }
        mPowerups.forEach { it.render(batch) }
        mExitPortal.render(batch)
        batch.end()
    }

    fun spawnExplosion(position: Vector2) {
        mExplosions.add(Explosion(position))
    }
}