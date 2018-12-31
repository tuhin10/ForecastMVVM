package com.tuhin.forecastmvvm.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tuhin.forecastmvvm.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "a38f24718dca4b64a2442232183210"
const val BASE_URL = "https://api.apixu.com/v1/"


interface ApiuxWeatherApiService {

    @GET("current.json")
    fun getCurrentWeather(
        @Query("q") location : String,
        @Query("lang") languageCode : String = "en"
    ) : Deferred<CurrentWeatherResponse>

    companion object {
        operator fun invoke () : ApiuxWeatherApiService {
            val requestInterceptor = Interceptor {
                val url = it.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key" , API_KEY)
                    .build()

                val request = it.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor it.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiuxWeatherApiService::class.java)
        }
    }

}