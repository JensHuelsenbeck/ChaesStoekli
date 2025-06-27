package com.example.cheas_stoeckli.data.remote.GoogleDirections

import com.cheas_stoeckli.app.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://maps.googleapis.com/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BASIC
}

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()


private val apiKey = BuildConfig.maps_api_key

interface GoogleApiService {

    @GET("maps/api/directions/json")
    suspend fun getDirections(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("key") apiKey: String
    ): Response<DirectionsResponse>
}

object GoogleAPI {
    val retrofitService: GoogleApiService by lazy {
        retrofit.create(GoogleApiService::class.java)
    }
}

fun staticMapsCall(polyline: String): String {
   return "https://maps.googleapis.com/maps/api/staticmap" +
            "?size=600x400" +
            "&path=enc:$polyline" +
            "&key=$apiKey"
}

fun main() = runBlocking {
    val api = GoogleAPI.retrofitService

    val directionsResponse = api.getDirections(
        origin = "Wismar",
        destination = "Rostock",
        apiKey = apiKey
    )
    val polyline = directionsResponse.body()?.routes?.firstOrNull()?.overviewPolyline?.points

    val staticUrl = staticMapsCall(polyline ?: "")

    println(" Polyline: $polyline")
    println(" StaticUrl: $staticUrl")
}


