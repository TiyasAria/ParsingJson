package com.dicoding.parsingjson.network

import com.dicoding.parsingjson.model.ResponseUser
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

//    get user with query

    @GET("api/users")
    fun getListUsers(
        @Query("page") page : String
    ) : Call<ResponseUser>

}