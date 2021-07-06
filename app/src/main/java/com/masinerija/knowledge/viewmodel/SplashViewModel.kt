package com.masinerija.knowledge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.masinerija.knowledge.repository.DataStorePreferences
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class SplashViewModel(
    private val dataStorePreferences: DataStorePreferences
): BaseViewModel() {

    val isInitialRunObservable = dataStorePreferences.isInitialRunFlow.asLiveData()

    val errorObservable = MutableLiveData<Boolean>()

    private val handler = CoroutineExceptionHandler { _, _ ->
        errorObservable.postValue(true)
    }

    fun setIsInitialRun(isInitialRun: Boolean) {
        scope.launch(handler) {
            dataStorePreferences.saveInitialRun(isInitialRun)
        }
    }
}