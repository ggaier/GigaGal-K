package com.ggaier.gigagalk.gigagal.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Camera
import com.ggaier.gigagalk.gigagal.entity.Gigagal

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class ChaseCam(var mCamera: Camera , var mTarget: Gigagal) {

    private var mFollowing: Boolean = true

    fun update(delta: Float) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            mFollowing = !mFollowing
        }

        if (mFollowing) {
            mCamera.position.x = mTarget.mPosition.x
            mCamera.position.y = mTarget.mPosition.y
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                mCamera.position.x -= delta * CHASE_CAM_MOVING_SPEED
            }

            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                mCamera.position.x += delta * CHASE_CAM_MOVING_SPEED
            }

            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                mCamera.position.y += delta * CHASE_CAM_MOVING_SPEED
            }

            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                mCamera.position.y -= delta * CHASE_CAM_MOVING_SPEED
            }

        }


    }

}