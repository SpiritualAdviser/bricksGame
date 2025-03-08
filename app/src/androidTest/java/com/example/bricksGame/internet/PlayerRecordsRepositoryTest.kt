package com.example.bricksGame.internet

import androidx.test.platform.app.InstrumentationRegistry
import com.example.bricksGame.components.players.data.Player
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(MockitoJUnitRunner::class)
class PlayerRecordsRepositoryTest {

    private lateinit var server: MockWebServer
    private lateinit var playerRecordsRepository: PlayerRecordsRepository
    private lateinit var retrofitClient: RetrofitClient
    private var activePlayer: Player = Player(
        playerName = "Player"
    )

    @Before
    fun set() {
        server = MockWebServer()
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        retrofitClient = RetrofitClient()

        retrofitClient.apiService = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)

        playerRecordsRepository = PlayerRecordsRepository(appContext, retrofitClient)
    }
    @After
    fun stopServer(){
        server.shutdown()
    }

    @Test
    fun getPlayerRecords() {
        runBlocking {

            val response = MockResponse().setResponseCode(200).setBody(
                """
               {
                 "players": [              
                   {
                     "achievements": 108,
                     "active": false,
                     "id": "81e0498f-9e7a-4d7f-be0e-396518b12826",
                     "levels": 8,
                     "name": "masterCHIFU"
                   },
                   {
                     "achievements": 100,
                     "active": false,
                     "id": "f4f25827-f597-4a11-bd5b-0df6e5fb3a46",
                     "levels": 11,
                     "name": "Elektra"
                   },
                   {
                     "achievements": 61,
                     "active": false,
                     "id": "7f30b821-9bc4-48d6-83ea-cd04fd5eeb52",
                     "levels": 4,
                     "name": "Player"
                   }
                 ]
               }
            """.trimIndent()
            )
            server.enqueue(response)
            playerRecordsRepository.getRecords(false)

            val result = playerRecordsRepository.playerRecords.isEmpty()
            val exceptedValue = true
            assertEquals(exceptedValue, result)
        }
    }
}