package com.masinerija.knowledge.repository.mapper

import com.masinerija.knowledge.database.entity.Brewery
import com.masinerija.knowledge.model.restapi.BreweriesResponse

class ResponseMapper {
    fun mapBreweries(breweries: BreweriesResponse): MutableList<Brewery> {
        val list = mutableListOf<Brewery>()
        breweries.forEach{ responseBrewery ->
            list.add(
                Brewery(
                    breweryId = responseBrewery.id,
                    name = responseBrewery.name,
                    city = responseBrewery.city,
                    street = responseBrewery.street,
                    phone = responseBrewery.phone,
                    websiteUrl = responseBrewery.website_url
                )
            )
        }
        return list
    }
}