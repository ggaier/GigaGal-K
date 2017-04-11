package com.ggaier.gigagalk.animation

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.DelayedRemovalArray
import com.badlogic.gdx.utils.TimeUtils
import com.badlogic.gdx.utils.viewport.ExtendViewport

/**
 * Created by ggaier at 11/04/2017 .
 * jwenbo52@gmail.com
 */
private const val EXPLOSION_SPAWN_RATE = 20f
private const val EXPLOSION_FRAME_DURATION = 0.1f
private const val WALK_LOOP_FRAME_DURATION = 0.1f

class Animations : ApplicationAdapter() {

    lateinit var mBatch: SpriteBatch
    lateinit var mViewport: ExtendViewport

    lateinit var mWalkLoop: Animation<Any>
    var mStartTime = Long.MIN_VALUE

    lateinit var mExplosion:Animation<Any>
    lateinit var mExplosions: DelayedRemovalArray<OneShotAnimation>

    override fun create() {
        mBatch = SpriteBatch()
        mViewport = ExtendViewport(100f, 100f)
        mStartTime = TimeUtils.nanoTime()
        val walkLoopTextures:Array<TextureRegion> = Array(3, { i ->
            TextureRegion(Texture("walk-$i-right.png")
            )
        })
        mWalkLoop = Animation(WALK_LOOP_FRAME_DURATION,walkLoopTextures,
                Animation.PlayMode.LOOP_PINGPONG)
        val explosionTextures=Array(3,{i->{
            TextureRegion(Texture("explosion-$i.png"))
        }})
        mExplosion=Animation(EXPLOSION_FRAME_DURATION,explosionTextures,Animation.PlayMode.NORMAL)
        mExplosions=DelayedRemovalArray()
    }


}