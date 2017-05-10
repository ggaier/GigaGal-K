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
internal const val TEXTURE_ATLAS: String = "images/gigagal.pack.atlas"
internal const val WALKING_LOOP_DURATION: Float = .25f

internal const val WORLD_SIZE: Float = 160f
internal val BACKGROUND_COLOR: Color = Color.SKY

//Gigagal
internal const val GIGAGAL_EYE_HEIGHT = 16.0f
internal const val GIGAGAL_HEIGHT = 23.0f
internal val GIGAGAL_EYE_POSITION = Vector2(16f, 24f)
internal const val GIGAGAL_MOVING_SPEED = 64f
internal const val GIGAGAL_STANCE_WIDTH = 21.0f
internal val GIGAGAL_CANNON_OFFSET=Vector2(12f,-7f)
internal const val INTIAL_AMMO=10

const val GRAVITY: Float = 1000f
const val MAX_JUMP_DURATION: Float = .15f
const val JUMP_SPEED: Float = 250f
val KNOCK_BACK_VELOCITY=Vector2(200f,200f)
internal val KILLING_PANE = -100f
internal const val CHASE_CAM_MOVING_SPEED = 128f

//Platform
const val PLATFORM_EDGE = 8
const val PLATFORM_SPRITE = "platform"

//Enemy
internal const val ENEMY_SPRITE = "enemy"
internal val ENEMY_CENTER = Vector2(14f, 22f)
internal const val ENEMY_MOVING_SPEED=10f
internal const val ENEMY_BOB_PERIOD=3.0f
internal const val ENEMY_BOB_AMPLITUDE=2f
internal const val ENEMY_COLLISION_RADIUS=15f
internal const val ENEMY_SHOT_RADIUS=17f
internal const val ENEMY_HEALTH=5

//Bullets
internal const val BULLET_SPRITE="bullet"
internal val BULLET_CENTER=Vector2(3f,2f)
internal val BULLET_MOVE_SPEED=150f

//Explosions
internal const val EXPLOSION_LARGE="explosion-large"
internal const val EXPLOSION_MEDIUM="explosion-medium"
internal const val EXPLOSION_SMALL="explosion-small"
internal const val EXPLOSION_DURATION=0.5f
internal val EXPLOSION_CENTER=Vector2(8f,8f)

//Powerups
internal const val POWERUP_SPRITE="powerup"
internal val POWERUP_CENTER=Vector2(7f,5f)
internal const val POWERUP_AMMO=10


