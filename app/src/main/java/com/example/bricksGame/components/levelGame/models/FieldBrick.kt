package com.example.bricksGame.components.levelGame.models

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import com.example.bricksGame.R
import com.example.bricksGame.components.levelGame.models.FieldViewModel.EMPTY_ID
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.helper.Sprite
import com.example.bricksGame.helper.SpriteAnimation


data class FieldBrick(
    val name: String = "FieldBricks",
    val position: Pair<Int, Int>,
    var id: String = "Color.Transparent",
    var life: Int = 0,
    var onDestroy: Boolean = false,

    var borderColor: MutableState<Color> = mutableStateOf(GameConfig.BRICK_BORDER_COLOR),
    var assetImage: MutableState<Int> = mutableIntStateOf(R.drawable.bgfielbrickempty),

    var hasOwnerId: Int? = null,
    var hasBonusOwnerId: String? = null,

    var globalX: Float = 0f,
    var globalY: Float = 0f,

    var globalWidth: Int = 0,
    var globalHeight: Int = 0,

    /**
     * They were placed in an occupied room ONLY_EMPTY_PLEASES=true
     */
    private val ONLY_EMPTY_PLEASES: Boolean = false,

    /**
     * animation sprite
     */
    var hasSprite: MutableState<Boolean> = mutableStateOf(false),

    var spriteSheet: ImageBitmap? = null,
    var sprite: Sprite? = null,

    val xSrcOffset: MutableIntState = mutableIntStateOf(0),
    val ySrcOffset: MutableIntState = mutableIntStateOf(0),
    val wSrcSize: MutableIntState = mutableIntStateOf(0),
    val hSrcSize: MutableIntState = mutableIntStateOf(0),
) {

    fun setSpriteAnimations(nameSprite: String) {

        sprite = SpriteAnimation.getSprite(nameSprite)
        spriteSheet = sprite?.imageSheet

        sprite?.let {
            onFrameChanged()
        }

        hasSprite.value = true
    }

    fun startAnimation(nameAnimation: String, isLoop: Boolean = false) {
        sprite?.runAnimation(
            nameAnimation,
            isLoop,
            onFrameChangedCallback = ::onFrameChanged,
            onEndAnimationCallback = ::onEndAnimation,
        )
    }

    private fun onFrameChanged() {
        sprite?.let {
            xSrcOffset.intValue = it.currentFrame.frame.x
            ySrcOffset.intValue = it.currentFrame.frame.y
            wSrcSize.intValue = it.currentFrame.frame.w
            hSrcSize.intValue = it.currentFrame.frame.h
        }
    }

    private fun onEndAnimation() {
        if (onDestroy) {
            resetFieldBrick()
        }
    }

    fun setGloballyPosition(coordinates: LayoutCoordinates) {
        this.globalWidth = coordinates.size.width
        this.globalHeight = coordinates.size.height
        this.globalX = coordinates.positionInWindow().x
        this.globalY = coordinates.positionInWindow().y
    }

    private fun changeBorder(color: Color) {
        this.borderColor.value = color
    }

    fun setImageOnStickBrick(image: Int) {
        this.assetImage.value = image
        this.setBorderBlack()
    }

    fun setBorderRed() {
        changeBorder(GameConfig.BRICK_BORDER_HOVER_COLOR)
    }

    fun setBorderBlack() {
        changeBorder(GameConfig.BRICK_BORDER_COLOR)
    }

    fun onDragEnd() {

        if (ONLY_EMPTY_PLEASES) {
            changeBorder(GameConfig.BRICK_BORDER_COLOR)
        } else {
            changeBorder(GameConfig.BRICK_BORDER_COLOR)
            this.resetFieldBrick()
        }
    }

    fun resetFieldBrick() {
        this.hasOwnerId = null
        this.hasBonusOwnerId = null

        hasSprite.value = false
        this.onDestroy = false

        this.setBorderBlack()
        this.assetImage.value = R.drawable.bgfielbrickempty
        this.id = EMPTY_ID
    }
}