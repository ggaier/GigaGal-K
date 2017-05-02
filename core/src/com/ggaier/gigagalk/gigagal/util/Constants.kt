package com.ggaier.gigagalk.gigagal.util

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 */
internal const val STANDING_RIGHT: String = "standing-right"
internal const val STANDING_LEFT: String = "standing-left"
internal const val JUMPING_LEFT: String = "jumping-left"
internal const val JUMPING_RIGHT: String = "jumping-right"
internal const val WALKING_RIGHT_2: String = "walk-2-right"
internal const val WALKING_LEFT_2: String = "walk-2-left"

internal const val WALKING_LEFT_1: String = "walk-1-left"
internal const val WALKING_LEFT_3: String = "walk-3-left"
internal const val WALKING_RIGHT_1: String = "walk-1-right"
internal const val WALKING_RIGHT_3: String = "walk-3-right"
internal const val WALKING_LOOP_DURATION: Float= .25f

internal const val TEXTURE_ATLAS: String = "images/gigagal.pack.atlas"
internal const val WORLD_SIZE: Float = 128f
internal val BACKGROUND_COLOR: Color = Color.SKY
const val GIGAGAL_EYE_HEIGHT=16.0f
internal val GIGAGAL_EYE_POSITION=Vector2(16f,24f)
const val GIGAGAL_MOVING_SPEED=64f
const val GIGAGAL_STANCE_WIDTH=21.0f

const val GRAVITY:Float=1000f
const val MAX_JUMP_DURATION:Float=.15f
const val JUMP_SPEED:Float=250f


const val PLATFORM_SPRITE="platform"
const val PLATFORM_EDGE=8

internal val KILLING_PANE=-100f

internal val CHASE_CAM_MOVING_SPEED=128f
