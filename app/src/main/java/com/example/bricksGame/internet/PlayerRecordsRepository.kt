package com.example.bricksGame.internet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.example.bricksGame.components.players.data.Player
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

    private val apiService = RetrofitClient.getClient().create(APIService::class.java)

    var playerRecords = mutableStateListOf<PlayerAchievement>()
    private var serverPlayerRecords: DataPlayerRecords? = null

    private var activePlayer: Player? = null

    fun getRecords(nedSendToServer: Boolean = false) {
        if (!isOnline(context)) {
            return
        }
        CoroutineScope(Dispatchers.IO).launch {
            val result = apiService.getData()
            if (result.isSuccessful) {
                result.body()?.let { serverRecords ->
                    val sortRecords = serverRecords.players
                    sortRecords.sortByDescending { it.achievements }

                    playerRecords.clear()
                    playerRecords.addAll(sortRecords)

                    activePlayer?.let { player ->
                        playerRecords.find { it.id == player.id }?.active = true
                        if (nedSendToServer) {
                            setPlayer(player)
                        }
                    }
                    serverPlayerRecords = serverRecords
                }
            }
        }
    }

    fun setRecords(player: Player) {
        activePlayer = player

        if (serverPlayerRecords != null) {
            setPlayer(player)
        } else {
            getRecords(true)
        }
    }

    private fun setPlayer(player: Player) {
        if (!isOnline(context)) {
            return
        }

        serverPlayerRecords?.let { serverRecords ->
            val newPlayerRecords =
                PlayerAchievement(
                    id = player.id,
                    name = player.playerName,
                    achievements = player.achievements,
                    levels = player.levels.openLevelList.size
                )
            val isIndexPlayer = serverRecords.players.indexOfFirst { it.id == player.id }

            if (isIndexPlayer >= 0) {
                serverRecords.players[isIndexPlayer] = newPlayerRecords
            } else {
                serverRecords.players.add(newPlayerRecords)
            }
            serverRecords.players.forEach { it.active = false }

            CoroutineScope(Dispatchers.IO).launch {
                apiService.postData("application/json", dataModal = serverRecords)
            }
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