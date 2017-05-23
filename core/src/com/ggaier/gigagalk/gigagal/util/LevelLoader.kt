package com.ggaier.gigagalk.gigagal.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport
import com.ggaier.gigagalk.gigagal.Level
import com.ggaier.gigagalk.gigagal.entity.*
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.File


/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class LevelLoader {


    companion object {

        @JvmStatic
        private val TAG = "LevelLoader"

        @JvmStatic
        fun load(levelName: String, viewport: Viewport): Level {
            val level = Level()
            val path = "levels${File.separator}$levelName.json"
            Gdx.app.log(TAG, "path $path")
            val file = Gdx.files.internal(path)
            val parser = JSONParser()
            try {
                val rootJsonObject: JSONObject = parser.parse(file.reader()) as JSONObject
                Gdx.app.log(TAG, rootJsonObject.keys.toString())

                val composite: JSONObject = rootJsonObject[LEVEL_COMPOSITE] as JSONObject
                Gdx.app.log(TAG, composite.keys.toString())

                val platforms: JSONArray = composite[LEVEL_9PATCHES] as JSONArray

                loadPlatForms(platforms, level)

                val nonPlatformObjects = composite[LEVEL_IMAGES] as JSONArray

                loadNonPlatformObjects(level, nonPlatformObjects)

                val firstPlatform: JSONObject = platforms[0] as JSONObject
                Gdx.app.log(TAG, firstPlatform.keys.toString())
            } catch(e: Exception) {
                Gdx.app.error(TAG, e.message)
                Gdx.app.error(TAG, LEVEL_ERROR_MESSAGE)
            }
            return level
        }

        private fun loadNonPlatformObjects(level: Level, nonPlatformObjects: JSONArray) {
            nonPlatformObjects.forEach {
                val item = it as JSONObject
                val lowerLeftCorner = Vector2()
                val x = safeGetFloat(item, LEVEL_Y_KEY)
                val y = safeGetFloat(item, LEVEL_Y_KEY)
                lowerLeftCorner.set(x, y)
                val imageName = item[LEVEL_IMAGENAME_KEY] as String
                if (imageName.equals(STANDING_RIGHT)) {
                    val gigagalPosition = lowerLeftCorner.add(GIGAGAL_EYE_POSITION)
                    Gdx.app.log(TAG, "loaded gigagal at $gigagalPosition")
                    level.mGigagal = Gigagal(gigagalPosition, level)
                } else if (imageName.equals(EXIT_PORTAL_SPRITE_1)) {
                    val exitPortalPosition = lowerLeftCorner.add(EXIT_PORTAL_CENTER)
                    Gdx.app.log(TAG, "Loaded the exit portal at $exitPortalPosition")
                    level.mExitPortal = ExitPortal(exitPortalPosition)
                } else if (imageName.equals(POWERUP_SPRITE)) {
                    val powerupPosition = lowerLeftCorner.add(POWERUP_CENTER)
                    Gdx.app.log(TAG, "Loaded a powerup at $powerupPosition")
                    level.mPowerups.add(Powerup(powerupPosition))
                }
            }
        }

        private fun loadPlatForms(platforms: JSONArray, level: Level) {
            //* A lambda expression is always surrounded by curly braces
            //* Its parameter(if any) are declared before '->'(parameter types may be omitted).
            //* The body goes after '->' (when present)
            val platformAry = com.badlogic.gdx.utils.Array<Platform>()
            platforms.forEach {
                val platformObject = it as JSONObject
                val x = safeGetFloat(platformObject, LEVEL_X_KEY)
                val y = safeGetFloat(platformObject, LEVEL_Y_KEY)
                val width = (platformObject[LEVEL_WIDTH_KEY] as? Number)?.toFloat() ?: 0f
                val height = (platformObject[LEVEL_HEIGHT_KEY] as? Number)?.toFloat() ?: 0f

                Gdx.app.log(TAG,
                        "Loaded pa platform at x= $x, y=${y + height}, w= $width, h= " +
                                "$height")
                val platform = Platform(x, y + height, width, height)
                platformAry.add(platform)

                val identifier = platformObject[LEVEL_IDENTIFIER_KEY] as? String?
                if (identifier?.equals(LEVEL_ENEMY_TAG) ?: false) {
                    level.mEnemies.add(Enemy(platform))
                }
            }
            platformAry.sort { o1, o2 ->
                if (o1.mTop < o2.mTop) 1 else if (o1.mTop > o2.mTop) -1 else 0
            }
            level.mPlatforms.addAll(platformAry)
        }

        private fun safeGetFloat(jsonObject: JSONObject, key: String): Float {
            val number: Number? = jsonObject[key] as? Number
            return number?.toFloat() ?: 0f
        }


    }

}