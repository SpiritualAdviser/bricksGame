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

class BaseModel(var context: Context?) {
    var id = 0L
    var assetImage: Int = R.drawable.bgfielbrickempty
    var alpha: MutableState<Float> = mutableFloatStateOf(1f)
    var zIndex: MutableState<Float> = mutableFloatStateOf(0f)
    var life = 1
    var hoverBorder = Color(0xFF01A817)
    var defaultBorder = Color.Black
    var activeBorderColor: MutableState<Color> = mutableStateOf(defaultBorder)

    private var placeOnField: PlaceOnField? = null
    var needReset: Boolean = false

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
            image = BitmapFactory.decodeResource(context?.resources, assetImage).asImageBitmap(),
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

    fun startAnimation(
        nameAnimation: String,
        isLoop: Boolean = false,
        place: PlaceOnField? = null,
        needReset: Boolean = false
    ) {
        this.needReset = needReset
        placeOnField = if (needReset) {
            place
        } else {
            null
        }

        sprite?.runAnimation(
            nameAnimation,
            isLoop,
            onFrameChangedCallback = ::onFrameChanged,
            onEndAnimationCallback = ::onEndAnimation,
        )
    }

    private fun onEndAnimation() {
        placeOnField?.let {
            if (needReset) {
                it.slot.value = GameObjects.Empty(BaseModel(context), Animation())
                placeOnField = null
                context = null
            }
        }
        needReset = false
    }
}
