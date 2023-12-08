package com.example.worddictionaryapp.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://www.dictionaryapi.com/api/v3/references/collegiate/json/"
private const val API_KEY = "0bd67b65-284a-454b-9c78-ca33cc745345"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface DictionaryApiService {
    @GET("{searchWord}?key=${API_KEY}")
    suspend fun getWord(@Path("searchWord") searchWord: String): Response<String>
}

object DictionaryApi {
    val retrofitService : DictionaryApiService by lazy {
        retrofit.create(DictionaryApiService::class.java) }
}