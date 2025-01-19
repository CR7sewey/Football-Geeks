package com.example.mycinema.common.data.remote

import com.example.footballgeeks.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//https://square.github.io/retrofit/
object RetroFitClient {

    private val httpClient: OkHttpClient
        get() {
            val clientBuilder = OkHttpClient.Builder()
            val token = BuildConfig.API_KEY

            clientBuilder.addInterceptor { it ->
                val original: Request = it.request()
                val requestBuilder: Request.Builder = original.newBuilder().header("X-Auth-Token",
                    token
                )
                val request: Request = requestBuilder.build()
                it.proceed(request)
            }
            return  clientBuilder.build()
        }
    private const val BASE_URL = "https://api.football-data.org/v4/"
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create()) // from text to kotlin list4
            .build()
    }

}