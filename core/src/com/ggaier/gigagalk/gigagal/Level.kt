package com.ggaier.gigagalk.gigagal

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.ggaier.gigagalk.gigagal.entity.Gigagal
import com.ggaier.gigagalk.gigagal.entity.Platform
import com.ggaier.gigagalk.gigagal.util.Assets
import com.ggaier.gigagalk.gigagal.util.ENEMY_CENTER
import com.ggaier.gigagalk.gigagal.util.Utils

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 */
class Level {

    val mGigagal:Gigagal

    private val mPlatforms = Array<Platform>()

    init {
//        mPlatforms.add(Platform(70f, 30f, 20f, 20f))
        addDebugPlatforms()
        mGigagal= Gigagal(Vector2(15f,40f))
    }

    private fun addDebugPlatforms() {
        mPlatforms.add(Platform(15f, 100f, 30f, 20f))
        mPlatforms.add(Platform(75f, 90f, 100f, 65f))
        mPlatforms.add(Platform(35f, 55f, 50f, 20f))
        mPlatforms.add(Platform(10f, 20f, 20f, 9f))

        mPlatforms.add(Platform(100f,110f,30f,9f))
        mPlatforms.add(Platform(200f,130f,30f,40f))
        mPlatforms.add(Platform(150f,150f,30f,9f))
        mPlatforms.add(Platform(150f,180f,30f,9f))
        mPlatforms.add(Platform(200f,200f,9f,9f))
        mPlatforms.add(Platform(280f,100f,30f,9f))

    }

    fun update(delta: Float) {
        mGigagal.update(delta, mPlatforms)
    }

    fun render(batch: SpriteBatch, renderer: ShapeRenderer) {
        for (platform in mPlatforms) {
            platform.render(batch)
        }
        Utils.drawTextureRegions(batch, Assets.mEnemyAssets.mEnemy, Vector2(100f,100f),
                ENEMY_CENTER)
        mGigagal.render(batch)
    }

}