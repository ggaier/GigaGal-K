package com.ggaier.gigagalk.fileloading

import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import org.apache.commons.codec.binary.Base64
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

/**
 * Created by ggaier at 30/03/2017 .
 * jwenbo52@gmail.com
 */
class FileLoading : ApplicationAdapter() {


    lateinit var mBatch: SpriteBatch
    lateinit var mImg: Texture

    companion object {
        val TAG = "FileLoading"
        val key: Key = SecretKeySpec("secretKey1234567".toByteArray(), "AES")

        @JvmStatic
        fun encrypt(message: String): String {
            val encryptedBytes: ByteArray?
            try {
                val cipher = Cipher.getInstance("AES")
                cipher.init(Cipher.ENCRYPT_MODE, key)
                encryptedBytes = cipher.doFinal(message.toByteArray())
                if (Gdx.app.type == Application.ApplicationType.Android) {
                    throw  Exception("this demo works only with desktop backend")
                }
                return Base64.encodeBase64String(encryptedBytes)
            } catch(e: Exception) {
                Gdx.app.error(TAG, "Couldn't encrypt message $message", e)
            }
            return "Failed"
        }

        @JvmStatic
        fun decrypt(encrypted: String): String {
            try {
                val cipher = Cipher.getInstance("AES")
                cipher.init(Cipher.DECRYPT_MODE, key)
                if (Gdx.app.type == Application.ApplicationType.Android) {
                    throw Exception("This demo works only with the desktop backend")
                }
                val encryptedBytes = Base64.decodeBase64(encrypted)
                return String(cipher.doFinal(encryptedBytes))
            } catch (e: Exception) {
                Gdx.app.error(TAG, "Couldn't decrypt message $encrypted ", e)
            }
            return "Failed"
        }

    }

    override fun create() {
        mBatch = SpriteBatch()
        mImg = Texture("badlogic.jpg")
        val encryptedPunchline = encrypt("Very Carefully")
        Gdx.app.log(TAG, encryptedPunchline)
        Gdx.app.log(TAG, "How does GigaGal tie her shoelaces when her arms are cannons?")
        val file = Gdx.files.internal("punchline")
        val encrypted = file.readString()
        val punchline = decrypt(encrypted)
        Gdx.app.log(TAG, punchline)
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mBatch.begin()
        mBatch.draw(mImg, 0f, 0f)
        mBatch.end()
    }


}