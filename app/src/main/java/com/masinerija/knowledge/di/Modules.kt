package com.masinerija.knowledge.di

import com.masinerija.knowledge.repository.DataStorePreferences
import com.masinerija.knowledge.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { DataStorePreferences(get()) }

    viewModel { SplashViewModel(get()) }
}
