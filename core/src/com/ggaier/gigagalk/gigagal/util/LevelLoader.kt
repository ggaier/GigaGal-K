package com.ggaier.gigagalk.gigagal.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.viewport.Viewport
import com.ggaier.gigagalk.gigagal.Level
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
            val level = Level(viewport)
            val path = "levels${File.separator}$levelName.json"
            Gdx.app.log(TAG, "path $path")
            val file = Gdx.files.internal(path)
            try {
                val parser = JSONParser()
                val rootJsonObject: JSONObject = parser.parse(file.reader()) as JSONObject
                Gdx.app.log(TAG, rootJsonObject.keys.toString())

                val composite: JSONObject = rootJsonObject[LEVEL_COMPOSITE] as JSONObject
                Gdx.app.log(TAG, composite.keys.toString())

                val platforms: JSONArray = composite[LEVEL_9PATCHES] as JSONArray
                val firstPlatform: JSONObject = platforms[0] as JSONObject
                Gdx.app.log(TAG, firstPlatform.keys.toString())
            } catch(e: Exception) {
                Gdx.app.error(TAG, e.message)
                Gdx.app.error(TAG, LEVEL_ERROR_MESSAGE)
            }
            return level
        }


    }

}