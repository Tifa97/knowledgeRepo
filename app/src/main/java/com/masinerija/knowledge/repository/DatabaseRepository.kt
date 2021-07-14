package com.masinerija.knowledge.repository

import android.content.Context
import com.masinerija.knowledge.database.dao.BreweryDao
import com.masinerija.knowledge.database.entity.Brewery
import kotlinx.coroutines.flow.Flow

class DatabaseRepository(val context: Context, private val breweryDao: BreweryDao) {
    suspend fun insertBrewery(brewery: Brewery){
        breweryDao.insertBrewery(brewery)
    }

    val breweriesFlow: Flow<List<Brewery>>
    get() = breweryDao.getAllBreweriesFlow()
}