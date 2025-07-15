package dev.sanmer.template

import android.app.Application
import dev.sanmer.template.di.Repositories
import dev.sanmer.template.di.ViewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(Repositories, ViewModels)
        }
    }
}