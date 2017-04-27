package com.ggaier.gigagalk.gigagal

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.Array
import com.ggaier.gigagalk.gigagal.entity.Gigagal
import com.ggaier.gigagalk.gigagal.entity.Platform

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 */
class Level {

    private val mGigagal = Gigagal()

    private val mPlatforms = Array<Platform>()

    init {
        mPlatforms.add(Platform(70f, 30f, 20f, 20f))
        addDebugPlatforms()
    }

    private fun addDebugPlatforms() {
        mPlatforms.add(Platform(15f, 100f, 30f, 20f))
        mPlatforms.add(Platform(75f, 90f, 100f, 65f))
        mPlatforms.add(Platform(35f, 55f, 50f, 20f))
        mPlatforms.add(Platform(10f, 20f, 20f, 9f))
    }

    fun update(delta: Float) {
        mGigagal.update(delta, mPlatforms)
    }

    fun render(batch: SpriteBatch, renderer: ShapeRenderer) {
        renderer.begin(ShapeRenderer.ShapeType.Filled)
        renderer.end()

        batch.begin()
        for (platform in mPlatforms) {
            platform.render(batch)
        }
        mGigagal.render(batch)
        batch.end()
    }

}