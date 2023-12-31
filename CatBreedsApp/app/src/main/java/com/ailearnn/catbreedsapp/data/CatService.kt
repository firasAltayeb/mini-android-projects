package com.ailearnn.catbreedsapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ICatService {

    @GET("breeds?limit=30&page=0")
    suspend fun getBreeds(): List<Breed>
}

/**
 * Find out more information on the Cat API by checking out the documentation here:
 * https://documenter.getpostman.com/view/5578104/RWgqUxxh#intro
 */
object CatService {
    private const val BASE_URL = "https://api.thecatapi.com/v1/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service: ICatService by lazy {
        retrofit.create(ICatService::class.java)
    }
}
