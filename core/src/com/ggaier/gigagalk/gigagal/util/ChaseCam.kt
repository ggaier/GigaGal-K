package com.ggaier.gigagalk.gigagal.util

import com.badlogic.gdx.graphics.Camera
import com.ggaier.gigagalk.gigagal.entity.Gigagal

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class ChaseCam(val mCamera: Camera, val mTarget: Gigagal) {

    fun update() {
        mCamera.position.x = mTarget.mPosition.x
        mCamera.position.y = mTarget.mPosition.y
    }

}