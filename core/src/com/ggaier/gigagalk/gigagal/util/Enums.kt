package com.ggaier.gigagalk.gigagal.util

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class Enums {

    enum class Direction {
        LEFT, RIGHT
    }

    enum class JumpState {
        JUMPING,
        FALLING,
        GROUNDED,
        RECOILING
    }

    enum class WalkingState {
        STANDING,
        WALKING
    }

}