package com.masinerija.knowledge.repository

import com.masinerija.knowledge.database.entity.Brewery
import com.masinerija.knowledge.model.restapi.BreweriesResponse
import com.masinerija.knowledge.repository.mapper.ResponseMapper
import com.masinerija.knowledge.services.ServiceBuilder

class BackendRepository {
    private val service by lazy {
        ServiceBuilder.buildService()
    }

    suspend fun getBreweries(breweries: BreweriesResponse): List<Brewery>{
        val result = service.getBreweries()

        var returnList = mutableListOf<Brewery>()
        if (result.isSuccessful){
            result.body()?.let { list ->
                returnList = ResponseMapper().mapBreweries(list)
            }
        }

        return returnList.toList()
    }
}