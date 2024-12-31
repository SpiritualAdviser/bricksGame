package com.example.bricksGame.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException

data class Cords(
    val x: Int,
    val y: Int,
    val w: Int,
    val h: Int,
)

data class Size(
    val w: Int,
    val h: Int,
)

data class Frame(
    var filename: String,
    var frame: Cords,
    var duration: Int,
)

data class Meta(
    val image: String,
    val size: Size,
    val frameTags: List<FrameTag>,
)

data class FrameTag(
    val name: String,
    val from: Int,
    val to: Int,
    val direction: String
)

data class Sprite(
    var frames: List<Frame>,
    var meta: Meta,
    var imageSheet: ImageBitmap,
    var isRun: Boolean = false
)

object SpriteAnimation {
    var animations = mutableListOf<Sprite>()

    private val gson = Gson()

    fun setAnimationOnGame(context: Context, fileNames: List<String>) {
        fileNames.forEach { nameSprite ->
            val jsonString = getJsonDataFromAsset(context, nameSprite)
            jsonString?.let { nameSpriteString ->
                val sprite = unparseJson(nameSpriteString)
                val bitmap = getImageFromAsset(context, sprite.meta.image)

                bitmap?.let {
                    sprite.imageSheet = it.asImageBitmap()
                }

                animations.add(sprite)
            }
        }
    }

    private fun getImageFromAsset(context: Context, fileName: String): Bitmap? {
        val bitmap: Bitmap?
        try {

            val stream = context.assets.open(fileName)
            bitmap = BitmapFactory.decodeStream(stream);
            println()
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return bitmap
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    private fun unparseJson(jsonString: String): Sprite {
        return gson.fromJson(jsonString, Sprite::class.java)
    }

    fun run(imageName: String) {
        val currentAnimation = animations.find { it.meta.image == imageName }

        currentAnimation?.let {
            it.isRun = true

            CoroutineScope(Dispatchers.Main).launch {
                while (it.isRun) {
                    println("тут чет делаем с анимацией")
                    delay(it.frames[0].duration.toLong())
                }
            }
        }
    }


    fun stop() {

    }

    fun pause() {

    }

}