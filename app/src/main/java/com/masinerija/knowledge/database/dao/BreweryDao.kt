package com.masinerija.knowledge.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.masinerija.knowledge.database.entity.Brewery
import kotlinx.coroutines.flow.Flow

@Dao
interface BreweryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBrewery(brewery: Brewery)

    @Query("SELECT * FROM brewery_table")
    fun getAllBreweriesFlow(): Flow<List<Brewery>>
}