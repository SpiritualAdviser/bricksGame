package com.example.bricksGame.components.popups.models

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
//import com.example.bricksGame.gameData.FieldBrick
import com.example.bricksGame.config.GameConfig
import com.example.bricksGame.helper.ScreenSize
import com.example.bricksGame.helper.SoundController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopupsViewModel @Inject constructor(
    private var screenSize: ScreenSize,
    val gameConfig: GameConfig,
    var soundController: SoundController,
) : ViewModel() {

    var showPopupOnFinishGame = mutableStateOf(false)
    var showPopupWinLine = mutableStateOf(false)

    var imageDefaultWinLine = gameConfig.imagesWinLine[0]
    var imageMegaWinLine = gameConfig.imagesWinLine[1]

    var imageAsset = mutableIntStateOf(imageDefaultWinLine)

    var xPopupWinLine = mutableIntStateOf(0)
    var yPopupWinLine = mutableIntStateOf(0)

    var scalePopupWinLine = Animatable(initialValue = 1F)

    private val winImage = gameConfig.imagesWinLevel[0]
    private val loseImage = gameConfig.imagesWinLevel[1]

    var imageAssetOnWinLevel = winImage

    fun onWinLine(
//        megaWin: Boolean,
////        fieldBrick: FieldBrick?,
//        winLineSize: Int,
//        rowDirection: Boolean
    ) {
//        imageAsset.intValue = if (megaWin) imageMegaWinLine else imageDefaultWinLine
//        fieldBrick?.let {
////            fieldBrick.borderColor.value = Color.Magenta
//            val offset = getOffsetAmount(fieldBrick, winLineSize, rowDirection)
//
//            xPopupWinLine.intValue = offset.getValue("x").toInt()
//            yPopupWinLine.intValue = offset.getValue("y").toInt()
//        }
//        showPopupWinLine.value = true
    }

//    private fun getOffsetAmount(
//        fieldBrick: FieldBrick,
//        winLineSize: Int,
//        rowDirection: Boolean
//    ): Map<String, Float> {
//        val xPadding: Int = (screenSize.screenWidthPx / 2)
//        val yPadding: Int = (screenSize.screenHeightPx / 2)
//
//        var centerXWnLine = fieldBrick.globalWidth / 2
//        var centerYWnLine = 0
//
//        if (rowDirection) {
//            if (winLineSize % 2 == 0) {
//                centerXWnLine = 0
////                centerYWnLine = fieldBrick.globalHeight / 2
//            } else {
////                centerXWnLine = fieldBrick.globalWidth / 2
////                centerYWnLine = 0
//            }
//
//        } else {
//            if (winLineSize % 2 == 0) {
////                centerXWnLine = fieldBrick.globalWidth / 2
//                centerYWnLine = -fieldBrick.globalHeight / 2
//            } else {
////                centerXWnLine = fieldBrick.globalWidth / 2
////                centerYWnLine = 0
//            }
//        }
//
//        val globalX = (fieldBrick.globalX - xPadding) + centerXWnLine
//        val globalY = (fieldBrick.globalY - yPadding) + centerYWnLine
//
//        val dragAmount = mapOf(
//            "x" to globalX,
//            "y" to globalY
//        )
//        return dragAmount
//    }

    fun setImageOnLevelEnd(wasWin: Boolean) {
        when {
            wasWin -> imageAssetOnWinLevel = winImage
            else -> imageAssetOnWinLevel = loseImage
        }
    }

    fun closePopupOnWinLine() {
        showPopupWinLine.value = false
        imageAsset.intValue = imageDefaultWinLine
        scalePopupWinLine = Animatable(initialValue = 1F)
    }

    fun closePopupOnFinishGame() {
        showPopupOnFinishGame.value = false
    }

    fun showPopupOnFinishGame() {
        showPopupOnFinishGame.value = true
    }
}