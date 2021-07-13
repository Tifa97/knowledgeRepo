package com.masinerija.knowledge.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.masinerija.knowledge.database.dao.BreweryDao
import com.masinerija.knowledge.database.entity.Brewery

@Database(
    entities = arrayOf(Brewery::class),
    version = 1,
    exportSchema = false
)
abstract class BreweryRoom: RoomDatabase() {
    abstract val breweryDao: BreweryDao

    companion object {

        @Volatile
        private var INSTANCE: BreweryRoom? = null

        fun getInstance(context: Context): BreweryRoom {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BreweryRoom::class.java,
                        "brewery_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}