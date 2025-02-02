package com.example.bricksGame.components.map.models

import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.map.controller.MapRepository
import com.example.bricksGame.config.Level
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapModel @Inject constructor(private var mapRepository: MapRepository) : ViewModel() {

    val visibility = mapRepository.visibility

    val levelList = mapRepository.levelList
    var currentLevel: Level? = null

    var levelTarget = mapRepository.levelTarget
    var levelWinLine: String = mapRepository.levelWinLine
    var levelStep = mapRepository.levelStep

    fun runLevel(level: Level) {
        mapRepository.runLevel(level)
    }

    fun goToHome() {
//        mapRepository.buttonController.navigateToHome()
    }
}