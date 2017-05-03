package com.ggaier.gigagalk.rectanglecirclecollision

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.MathUtils

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class OscillatingCircle(val mOriginX: Float, val mOriginY: Float,
                        val mRadius: Float, val mAngle: Float,
                        val mMagnitude: Float, val mPeriod: Float) {

    fun getCurrentCircle(elapsedTime :Float):Circle {
        val x=mOriginX+mMagnitude*MathUtils.cos(mAngle)*MathUtils.sin(MathUtils
                .PI2*elapsedTime/mPeriod)
        val y=mOriginY+mMagnitude*MathUtils.sin(MathUtils.PI2*elapsedTime/mPeriod)
        return Circle(x,y,mRadius)
    }


}