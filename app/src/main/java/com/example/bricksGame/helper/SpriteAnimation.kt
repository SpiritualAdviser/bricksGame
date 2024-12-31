package com.example.bricksGame.helper

import android.content.Context
import com.google.gson.Gson
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
    var meta: Meta
)

class SpriteAnimation {
    private var animations = mutableListOf<Sprite>()

    private val gson = Gson()

    fun setAnimationOnGame(context: Context, fileNames: List<String>) {
        fileNames.forEach { nameSprite ->
            val jsonString = getJsonDataFromAsset(context, nameSprite)
            jsonString?.let {
                val sprite = unparseJson(it)
                animations.add(sprite)
            }
        }
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


    fun run() {

    }

    fun stop() {

    }

    fun pause() {

    }

}