package com.example.bricksGame.components.gameMeny.animation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import com.example.bricksGame.helper.Frame
import com.example.bricksGame.helper.SpriteAnimation

object AnimationLogo : ViewModel() {

    private const val SPRITE_NAME = "explosion_c3.json"
    private val sprite = SpriteAnimation.getSprite(SPRITE_NAME)

    val spriteSheet: MutableState<ImageBitmap>? = sprite?.let { mutableStateOf(it.imageSheet) }
    private val imageFrame: Frame? = sprite?.currentFrame

    val x = mutableIntStateOf(0)
    val y = mutableIntStateOf(0)
    val w = mutableIntStateOf(0)
    val h = mutableIntStateOf(0)

    fun run() {
        if (imageFrame != null && sprite != null) {
            sprite.run("blow") { imageFrameCallback() }
        }
    }

    private fun imageFrameCallback() {
        sprite?.let {
            x.intValue = sprite.currentFrame.frame.x
            y.intValue = sprite.currentFrame.frame.y
            w.intValue = sprite.currentFrame.frame.w
            h.intValue = sprite.currentFrame.frame.h
        }
    }
}