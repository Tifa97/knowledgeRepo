package com.masinerija.knowledge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.masinerija.knowledge.repository.DataStorePreferences
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val dataStorePreferences: DataStorePreferences
): BaseViewModel() {

    val errorObservable = MutableLiveData<Boolean>()

    private val handler = CoroutineExceptionHandler { _, _ ->
        errorObservable.postValue(true)
    }

    fun setIsInitialRun(isInitialRun: Boolean) {
        scope.launch(handler) {
            dataStorePreferences.saveInitialRun(isInitialRun)
        }
    }

    fun getIsInitialRun(): Flow<Boolean>{
        return dataStorePreferences.isInitialRunFlow
    }
}
