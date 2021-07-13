package com.masinerija.knowledge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.masinerija.knowledge.database.entity.Brewery
import com.masinerija.knowledge.repository.BackendRepository
import com.masinerija.knowledge.repository.DatabaseRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BreweriesViewModel(
    private val backendRepository: BackendRepository,
    private val databaseRepository: DatabaseRepository
): BaseViewModel(){
    private val _breweriesObservable = MutableLiveData<List<Brewery>>()
    val breweriesObservable: LiveData<List<Brewery>> = _breweriesObservable

    val errorObservable = MutableLiveData<Boolean>()
    private val handler = CoroutineExceptionHandler { _, d ->
        errorObservable.postValue(true)
    }

    init {
        fetchBreweries()
    }

    private fun fetchBreweries() {
        scope.launch (handler) {
            val breweries = backendRepository.getBreweries()
            withContext(Dispatchers.Main){
                _breweriesObservable.value = breweries
            }
        }
    }

    fun insertBrewery(brewery: Brewery) {
        scope.launch (handler) {
            databaseRepository.insertBrewery(brewery)
        }
    }
}