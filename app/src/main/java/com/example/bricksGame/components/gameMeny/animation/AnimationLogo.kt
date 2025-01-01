package com.example.bricksGame.components.gameMeny.animation

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import com.example.bricksGame.helper.Frame
import com.example.bricksGame.helper.SpriteAnimation

object AnimationLogo : ViewModel() {

    private const val SPRITE_NAME = "explosion_c3.json"
    private val sprite = SpriteAnimation.getSprite(SPRITE_NAME)

    val spriteSheet: ImageBitmap? = sprite?.imageSheet
    private val imageFrame: Frame? = sprite?.currentFrame

    val x = if (imageFrame != null) mutableIntStateOf(imageFrame.frame.x) else mutableIntStateOf(0)
    val y = if (imageFrame != null) mutableIntStateOf(imageFrame.frame.y) else mutableIntStateOf(0)
    val w = if (imageFrame != null) mutableIntStateOf(imageFrame.frame.w) else mutableIntStateOf(0)
    val h = if (imageFrame != null) mutableIntStateOf(imageFrame.frame.h) else mutableIntStateOf(0)

    fun run() {
        sprite?.run("blow", true) { onFrameChanged() }
    }

    fun stop() {
        sprite?.stop()
    }

    private fun onFrameChanged() {
        sprite?.let {
            x.intValue = sprite.currentFrame.frame.x
            y.intValue = sprite.currentFrame.frame.y
            w.intValue = sprite.currentFrame.frame.w
            h.intValue = sprite.currentFrame.frame.h
        }
    }
}