package com.example.bricksGame.internet


import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

private const val apiKey = "3425430f-ec41-11ef-b7ef-0242ac110009"

interface APIService {

    @GET("228b2d7e-1e59-4219-8914-5eb66d772135/basket/PLAYERS_RECORDS")
    suspend fun getData(
    ): Response<DataPlayerRecords>

    @POST("228b2d7e-1e59-4219-8914-5eb66d772135/basket/PLAYERS_RECORDS")
    suspend fun postData(
        @Header("Content-Type") na: String = "application/json",
        @Body dataModal: DataPlayerRecords
    )
}

object RetrofitClient {
    private var retrofit: Retrofit? = null

    private const val baseUrl = "https://getpantry.cloud/apiv1/pantry/"

    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}


