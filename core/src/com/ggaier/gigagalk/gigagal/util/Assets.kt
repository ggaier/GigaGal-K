package com.ggaier.gigagalk.gigagal.util

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetErrorListener
import com.badlogic.gdx.utils.Disposable

/**
 * Created by ggaier at 20/04/2017 .
 * jwenbo52@gmail.com
 */
class Assets:Disposable,AssetErrorListener{


    override fun error(asset: AssetDescriptor<*>?, throwable: Throwable?) {
    }

    override fun dispose() {
    }

}