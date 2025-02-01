//package com.example.bricksGame.helper
//
//import android.content.Context
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import androidx.compose.ui.graphics.ImageBitmap
//import androidx.compose.ui.graphics.asImageBitmap
//import com.google.gson.Gson
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//import java.io.IOException
//
//data class Cords(
//    val x: Int,
//    val y: Int,
//    val w: Int,
//    val h: Int,
//)
//
//data class Size(
//    val w: Int,
//    val h: Int,
//)
//
//data class Frame(
//    var filename: String,
//    var frame: Cords,
//    var duration: Int,
//)
//
//data class Meta(
//    val image: String,
//    val size: Size,
//    val frameTags: List<FrameTag>,
//)
//
//data class FrameTag(
//    val name: String,
//    val from: Int,
//    val to: Int,
//    val direction: String
//)
//
//class Sprite(
//    var nameSprite: String = "",
//    var frames: List<Frame>,
//
//    var meta: Meta,
//    var imageSheet: ImageBitmap,
//    var isRun: Boolean = false,
//    var currentFrame: Frame = frames.first()
//) {
//
//    fun runAnimation(
//        animationName: String,
//        isLoop: Boolean = false,
//        onStartAnimationCallback: () -> Unit = ::onStartAnimationCallback,
//        onFrameChangedCallback: () -> Unit = ::onFrameChangedCallback,
//        onEndAnimationCallback: () -> Unit = ::onEndAnimationCallback,
//
//        ) {
//        val currentAnimation = meta.frameTags.find { it.name == animationName }
//
//        currentAnimation?.let {
//            val startIndex = currentAnimation.from
//            val endIndex = currentAnimation.to + 1
//            val framesAnimation = frames.subList(startIndex, endIndex)
//            val delayFrame = frames.first().duration.toLong()
//
//            loopAnimation(
//                framesAnimation,
//                delayFrame,
//                isLoop,
//                onStartAnimationCallback,
//                onFrameChangedCallback,
//                onEndAnimationCallback,
//            )
//        }
//    }
//
//    private fun loopAnimation(
//        framesAnimation: List<Frame>,
//        delayFrame: Long,
//        isLoop: Boolean,
//        onStartAnimationCallback: () -> Unit,
//        onFrameChangedCallback: () -> Unit,
//        onEndAnimationCallback: () -> Unit,
//    ) {
//        isRun = true
//
//        CoroutineScope(Dispatchers.Main).launch {
//            onStartAnimationCallback()
//
//            while (this@Sprite.isRun) {
//
//                framesAnimation.forEach { frame ->
//                    currentFrame = frame
//                    onFrameChangedCallback()
//                    delay(delayFrame)
//                }
//                if (!isLoop) {
//                    stopAnimation(onEndAnimationCallback)
//                }
//            }
//        }
//    }
//
//    fun stopAnimation(onEndAnimationCallback: () -> Unit = ::onEndAnimationCallback) {
//        isRun = false
//        onEndAnimationCallback()
//    }
//
//    fun pause() {
//
//    }
//
//    fun onStartAnimationCallback() {
//
//    }
//
//    fun onFrameChangedCallback() {
//
//    }
//
//    fun onEndAnimationCallback() {
//
//    }
//
//}
//
//object SpriteAnimation {
//    private var animations = mutableListOf<Sprite>()
//
//    private val gson = Gson()
//
//    fun setAnimationOnGame(context: Context, fileNames: List<String>) {
//        fileNames.forEach { nameSprite ->
//            val jsonString = getJsonDataFromAsset(context, nameSprite)
//            jsonString?.let { nameSpriteString ->
//                val sprite = unparseJson(nameSpriteString)
//                val bitmap = getImageFromAsset(context, sprite.meta.image)
//
//                bitmap?.let {
//                    sprite.imageSheet = it.asImageBitmap()
//                    sprite.nameSprite = nameSprite
//                    sprite.currentFrame = sprite.frames.first()
//                    println()
//                }
//
//                animations.add(sprite)
//            }
//        }
//    }
//
//    private fun getImageFromAsset(context: Context, fileName: String): Bitmap? {
//        val bitmap: Bitmap?
//        try {
//
//            val stream = context.assets.open(fileName)
//            bitmap = BitmapFactory.decodeStream(stream);
//            println()
//        } catch (ioException: IOException) {
//            ioException.printStackTrace()
//            return null
//        }
//        return bitmap
//    }
//
//    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
//        val jsonString: String
//        try {
//            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
//        } catch (ioException: IOException) {
//            ioException.printStackTrace()
//            return null
//        }
//        return jsonString
//    }
//
//    private fun unparseJson(jsonString: String): Sprite {
//        return gson.fromJson(jsonString, Sprite::class.java)
//    }
//
//    fun getSprite(spriteName: String): Sprite? {
//        var newSprite: Sprite? = null
//
//        val currentAnimation = animations.find { it.nameSprite == spriteName }
//
//        currentAnimation?.let {
//            newSprite = Sprite(
//                nameSprite = currentAnimation.nameSprite,
//                frames = currentAnimation.frames,
//                meta = currentAnimation.meta,
//                imageSheet = currentAnimation.imageSheet,
//                isRun = false,
//                currentFrame = currentAnimation.frames.first()
//            )
//        }
//
//        return newSprite
//    }
//}