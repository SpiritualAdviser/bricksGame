package com.example.bricksGame.internet

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.example.bricksGame.components.players.data.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerRecordsRepository @Inject constructor() {
    init {
        Log.d("my", "PlayersRecordsRepository")
    }

    private val apiService = RetrofitClient.getClient().create(APIService::class.java)

    private var defaultPlayerRecords: DataPlayerRecords = DataPlayerRecords(
        players = mutableListOf(
            PlayerAchievement(
                id = "111111111",
                name = "0",
                achievements = 0,
                levels = 1
            )
        )
    )

    var playerRecords = mutableStateListOf<PlayerAchievement>()
    private var dataPlayerRecords: DataPlayerRecords = defaultPlayerRecords

    fun getRecords() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = apiService.getData()
            if (result.isSuccessful) {
                result.body()?.let { dataRecords ->
                    val sortRecords = dataRecords.players
                    sortRecords.sortByDescending { it.achievements }

                    playerRecords.clear()
                    playerRecords.addAll(sortRecords)
                    dataPlayerRecords = dataRecords
                }
                println()
            }
        }
    }

    fun setRecords(player: Player) {

        val newPlayerRecords =
            PlayerAchievement(
                id = player.id,
                name = player.playerName,
                achievements = player.achievements,
                levels = player.levels.openLevelList.size
            )
        val isIndexPlayer = dataPlayerRecords.players.indexOfFirst { it.id == player.id }

        if (isIndexPlayer >= 0) {
            dataPlayerRecords.players[isIndexPlayer] = newPlayerRecords
            println()
        } else {
            dataPlayerRecords.players.add(newPlayerRecords)
        }

        CoroutineScope(Dispatchers.IO).launch {
            val result = apiService.postData("application/json", dataModal = dataPlayerRecords)
//            delay(6000)
//            getRecords()
        }
    }
}