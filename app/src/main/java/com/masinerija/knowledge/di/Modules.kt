package com.masinerija.knowledge.di

import com.masinerija.knowledge.database.BreweryRoom
import com.masinerija.knowledge.repository.BackendRepository
import com.masinerija.knowledge.repository.DataStorePreferences
import com.masinerija.knowledge.repository.DatabaseRepository
import com.masinerija.knowledge.viewmodel.BreweriesViewModel
import com.masinerija.knowledge.viewmodel.SplashViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { DataStorePreferences(get()) }
    single { BackendRepository() }
    single { DatabaseRepository(get(), get()) }

    single { BreweryRoom.getInstance(androidApplication()).breweryDao }

    viewModel { SplashViewModel(get()) }
    viewModel { BreweriesViewModel(get(), get()) }
}
