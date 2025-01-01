package com.example.bricksGame.components.gameMeny.animation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import com.example.bricksGame.helper.Frame
import com.example.bricksGame.helper.SpriteAnimation

object AnimationLogo : ViewModel() {

    private const val SPRITE_NAME = "explosion_c3.json"
    private val sprite = SpriteAnimation.getSprite(SPRITE_NAME)

    val spriteSheet: MutableState<ImageBitmap>? = sprite?.let { mutableStateOf(it.imageSheet) }
    val imageFrame: MutableState<Frame>? = sprite?.let { mutableStateOf(it.currentFrame) }

    fun run() {
        sprite?.run("blow")
    }

}