package com.masinerija.knowledge.repository

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStorePreferences(context: Context) {

    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences>

    init {
        dataStore = applicationContext.createDataStore(
            name = DATA_STORE_NAME
        )
    }

    suspend fun saveInitialRun(isFirstRun: Boolean) {
        dataStore.edit {
            it[IS_INITIAL_RUN_KEY] = isFirstRun
        }
    }

    val isInitialRunFlow: Flow<Boolean>
        get() = dataStore.data.map {
            it[IS_INITIAL_RUN_KEY] ?: true
        }

    companion object{
        private const val DATA_STORE_NAME = "knowledge_preferences"

        val IS_INITIAL_RUN_KEY = preferencesKey<Boolean>("is_initial_run_key")
    }
}