package com.ggaier.gigagalk.gigagal.entity

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 */
class Platform(val mLeft:Float,val mTop:Float,val mWidth:Float,val mHeight:Float){

    private val mBottom=mTop-mHeight
    private val mRright=mLeft+mWidth

    fun render(renderer:ShapeRenderer){
        renderer.color=Color.BLUE
        renderer.rect(mLeft,mBottom, mWidth,mHeight)
    }

}