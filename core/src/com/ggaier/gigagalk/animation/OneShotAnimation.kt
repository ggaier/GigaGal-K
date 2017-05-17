package com.ggaier.gigagalk.animation

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.TimeUtils

/**
 * Created by ggaier at 11/04/2017 .
 * jwenbo52@gmail.com
 */
class OneShotAnimation(val mAnimation: Animation<TextureRegion>, val mPosition:Vector2,
                       val mStartTimeNanos:Long){

    fun elapsedTime():Float=MathUtils.nanoToSec*(TimeUtils.nanoTime()-
            mStartTimeNanos)

    fun getFrame():TextureRegion=mAnimation.getKeyFrame(elapsedTime())

    fun isAnimationFinished():Boolean=mAnimation.isAnimationFinished(elapsedTime())


}