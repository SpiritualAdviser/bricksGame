package com.example.bricksGame.internet

import com.example.bricksGame.BuildConfig
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

const val apiKey = BuildConfig.apiKey

interface APIService {

    @GET("${apiKey}/basket/PLAYERS_RECORDS")
    suspend fun getData(
    ): Response<DataPlayerRecords>

    @POST("${apiKey}/basket/PLAYERS_RECORDS")
    suspend fun postData(
        @Header("Content-Type") json: String = "application/json",
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


