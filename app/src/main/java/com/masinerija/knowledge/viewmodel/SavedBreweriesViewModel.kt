package com.masinerija.knowledge.viewmodel

import androidx.lifecycle.asLiveData
import com.masinerija.knowledge.repository.DatabaseRepository

class SavedBreweriesViewModel(
    private val databaseRepository: DatabaseRepository
) : BaseViewModel() {
    val breweriesObservable = databaseRepository.breweriesFlow.asLiveData()
}