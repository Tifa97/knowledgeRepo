package com.masinerija.knowledge

import android.app.Application
import androidx.databinding.library.BuildConfig
import com.jakewharton.threetenabp.AndroidThreeTen
import com.masinerija.knowledge.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class KnowledgeApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@KnowledgeApplication)
            modules(appModule)
        }
    }
}