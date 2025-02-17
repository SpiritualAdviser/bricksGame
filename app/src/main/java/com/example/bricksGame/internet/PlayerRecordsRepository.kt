package com.example.bricksGame.internet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.example.bricksGame.components.players.data.Player
import com.example.bricksGame.components.players.repository.PlayerRepository
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerRecordsRepository @Inject constructor(
    @ApplicationContext val context: Context

) {
    init {
        Log.d("my", "PlayersRecordsRepository")
    }

    private var activePlayer: Player? = null

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
        if (!isOnline(context)) {
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            val result = apiService.getData()
            if (result.isSuccessful) {
                result.body()?.let { dataRecords ->
                    val sortRecords = dataRecords.players
                    sortRecords.sortByDescending { it.achievements }

                    playerRecords.clear()
                    playerRecords.addAll(sortRecords)

                    activePlayer?.let { player ->
                        playerRecords.find { it.id == player.id }?.active = true
                    }
                    dataPlayerRecords = dataRecords
                }
                println()
            }
        }
    }

    fun setRecords(player: Player) {
        if (!isOnline(context)) {
            return
        }

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

        dataPlayerRecords.players.forEach { it.active = false }
        CoroutineScope(Dispatchers.IO).launch {
            val result = apiService.postData("application/json", dataModal = dataPlayerRecords)
//            delay(6000)
//            getRecords()
        }
    }

    fun updateTypeOfBase() {
        CoroutineScope(Dispatchers.IO).launch {
            apiService.postData("application/json", dataModal = defaultPlayerRecords)
        }
    }

    fun setActivePlayer(player: Player) {
        activePlayer = player
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }
}