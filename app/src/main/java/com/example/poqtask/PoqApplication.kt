package com.example.poqtask

import android.app.Application
import com.example.poqtask.di.networkModule
import com.example.poqtask.di.repositoryModule
import com.example.poqtask.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PoqApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin for dependency injection
        startKoin {
            androidContext(this@PoqApplication)
            modules(
                networkModule,
                repositoryModule,
                viewModelModule,
            )
        }
    }
}
