package com.example.bricksGame.gameObjects

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import com.example.bricksGame.R
import com.example.bricksGame.helper.Sprite

class BaseModel(var context: Context) {
   var assetImage: Int = R.drawable.bgfielbrickempty
    /**
     * animation sprite
     */
    var sprite: Sprite? = null
    val xSrcOffset: MutableIntState = mutableIntStateOf(0)
    val ySrcOffset: MutableIntState = mutableIntStateOf(0)
    val widthSize: MutableIntState = mutableIntStateOf(60)
    val heightSize: MutableIntState = mutableIntStateOf(60)

    fun getBitmapPainter(): BitmapPainter {

        var painter = BitmapPainter(
            image = BitmapFactory.decodeResource(context.resources, assetImage).asImageBitmap(),
        )

        sprite?.let {
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
}
