package com.example.bricksGame.components.tableRecords.model

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.bricksGame.internet.PlayerAchievement
import com.example.bricksGame.internet.PlayerRecordsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordsViewModel @Inject constructor(
    private var playerRecordsRepository: PlayerRecordsRepository,
) : ViewModel() {

    init {
        Log.d("my", "RecordsViewModel_init")
    }

    var playerRecords = getPlayers()

    private fun getPlayers(): SnapshotStateList<PlayerAchievement> {
        playerRecordsRepository.getRecords()
        return playerRecordsRepository.playerRecords
    }
}