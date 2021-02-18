package com.nicktra.moviedex

import android.app.Application
import com.nicktra.moviedex.core.di.databaseModule
import com.nicktra.moviedex.core.di.networkModule
import com.nicktra.moviedex.core.di.repositoryModule
import com.nicktra.moviedex.di.useCaseModule
import com.nicktra.moviedex.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}