package cz.radovanholik.moviesviewer

import android.app.Application
import cz.radovanholik.moviesviewer.di.appModule
import cz.radovanholik.moviesviewer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

import timber.log.Timber.DebugTree

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    private fun initKoin() {
        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(
                // TODO - add modules
                appModule,
                viewModelModule
            )
        }
    }
}