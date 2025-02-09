package com.example.bricksGame.gameObjects

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import com.example.bricksGame.R
import com.example.bricksGame.helper.Sprite

class BaseModel(var context: Context) {
    var id = 0
    var assetImage: Int = R.drawable.bgfielbrickempty
    var alpha: MutableState<Float> = mutableFloatStateOf(1f)
    var zIndex: MutableState<Float> = mutableFloatStateOf(0f)

    var activeBorderColor: MutableState<Color> = mutableStateOf(Color.Black)

    /**
     * animation sprite
     */
    var sprite: Sprite? = null
    val xSrcOffset: MutableIntState = mutableIntStateOf(0)
    val ySrcOffset: MutableIntState = mutableIntStateOf(0)
    val widthSize: MutableIntState = mutableIntStateOf(10)
    val heightSize: MutableIntState = mutableIntStateOf(10)

    fun getBitmapPainter(): BitmapPainter {

        var painter = BitmapPainter(
            image = BitmapFactory.decodeResource(context.resources, assetImage).asImageBitmap(),
        )

        sprite?.let {
            onFrameChanged()

            painter = BitmapPainter(
                image = it.imageSheet,
                srcOffset = IntOffset(
                    x = xSrcOffset.intValue,
                    y = ySrcOffset.intValue
                ),
                srcSize = IntSize(
                    width = widthSize.intValue,
                    height = heightSize.intValue
                )
            )
        }

        return painter
    }

    private fun onFrameChanged() {
        sprite?.let {
            xSrcOffset.intValue = it.currentFrame.frame.x
            ySrcOffset.intValue = it.currentFrame.frame.y
            widthSize.intValue = it.currentFrame.frame.w
            heightSize.intValue = it.currentFrame.frame.h
        }
    }

    fun startAnimation(nameAnimation: String, isLoop: Boolean = false) {
        sprite?.runAnimation(
            nameAnimation,
            isLoop,
            onFrameChangedCallback = ::onFrameChanged,
            onEndAnimationCallback = ::onEndAnimation,
        )
    }

    private fun onEndAnimation() {

    }

    private fun resetSprite() {

    }
}
