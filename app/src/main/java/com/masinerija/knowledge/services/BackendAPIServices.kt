package com.masinerija.knowledge.services

import com.masinerija.knowledge.model.restapi.BreweriesResponse
import retrofit2.Response
import retrofit2.http.*

interface BackendAPIServices {
    @GET("/breweries")
    suspend fun getBreweries(): Response<BreweriesResponse>
}