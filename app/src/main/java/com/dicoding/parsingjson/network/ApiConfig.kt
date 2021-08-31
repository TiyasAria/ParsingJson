package com.dicoding.parsingjson.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {
    //buat akses portnya (itu http logging) ibaratnya kamu udah ke server tapi kamu mau lewat pintu\jendela mana
    private val loggingInterceptor =
         HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    //untuk menghubungkan ke client nya
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .retryOnConnectionFailure(true)
        //ketika prosesnya lebih dr 30 detik , berarti datanya gagall bisa karena ga ada koneksi internet
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    //untuk convert menjadi gson ,
    private val gson = GsonBuilder()
        .setLenient()
        .create()


    //entry data
    //buat atur url mana yang mau dieksekusi
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://reqres.in/")
        .client(client)
        //buat convert ke gson
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    //function untuk menyambung end point dari class api service
    fun getApiService() : ApiService = retrofit.create(ApiService::class.java)

}