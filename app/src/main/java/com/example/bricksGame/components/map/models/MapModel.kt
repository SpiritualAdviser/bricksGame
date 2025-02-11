package com.example.bricksGame.components.map.models

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.bricksGame.components.map.controller.MapController
import com.example.bricksGame.config.Level
import com.example.bricksGame.logic.StartLevelLogic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapModel @Inject constructor(
    private var mapController: MapController,
    private var startLevelLogic: StartLevelLogic,
) : ViewModel() {

    init {
        Log.d("my", "MapModel_init")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("my", "MapModel_onCleared")
    }

    var playerLevels: SnapshotStateList<Level> = mapController.getPlayerLevels()

    fun runLevel(level: Level) {
        startLevelLogic.onStartLevel(level)
    }

    fun goToHome() {
        startLevelLogic.goToHome()
    }
}