package com.ggaier.gigagalk.animation

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
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

    lateinit var mWalkLoop: Animation<TextureRegion>
    var mStartTime = Long.MIN_VALUE

    lateinit var mExplosion: Animation<TextureRegion>
    lateinit var mExplosions: DelayedRemovalArray<OneShotAnimation>

    override fun create() {
        mBatch = SpriteBatch()
        mViewport = ExtendViewport(100f, 100f)
        mStartTime = TimeUtils.nanoTime()
        val walkLoopTextures: com.badlogic.gdx.utils.Array<TextureRegion> =
                com.badlogic.gdx.utils.Array()
        walkLoopTextures.add(TextureRegion(Texture("walk-1-right.png")))
        walkLoopTextures.add(TextureRegion(Texture("walk-2-right.png")))
        walkLoopTextures.add(TextureRegion(Texture("walk-3-right.png")))

        mWalkLoop = Animation<TextureRegion>(WALK_LOOP_FRAME_DURATION, walkLoopTextures,
                Animation.PlayMode.LOOP_PINGPONG)

        val explosionTextures: Array<TextureRegion> = Array()
        explosionTextures.add(TextureRegion(Texture("explosion-1.png")))
        explosionTextures.add(TextureRegion(Texture("explosion-2.png")))
        explosionTextures.add(TextureRegion(Texture("explosion-3.png")))

        mExplosion = Animation(EXPLOSION_FRAME_DURATION, explosionTextures,
                Animation.PlayMode.NORMAL)
        mExplosions = DelayedRemovalArray()
    }

    override fun resize(width: Int, height: Int) {
        mViewport.update(width, height, true)
    }


    override fun render() {
        //移除或者新增爆炸动画
        updateExplosions()
        mViewport.apply()
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mBatch.projectionMatrix=mViewport.camera.combined
        mBatch.begin()
        
    }


    private fun updateExplosions() {
        mExplosions.begin()
        for ((index, e) in mExplosions.withIndex()) {
            if (e.isAnimationFinished()) {
                mExplosions.removeIndex(index)
            }
        }
        mExplosions.end()

        if(MathUtils.random() < Gdx.graphics.deltaTime* EXPLOSION_SPAWN_RATE){
            val position=Vector2(MathUtils.random(mViewport.worldWidth),
                    MathUtils.random(mViewport.worldHeight))
            mExplosions.add(OneShotAnimation(mExplosion,position,TimeUtils.nanoTime()))
        }
    }


}









