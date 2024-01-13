package dev.sanmer.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dev.sanmer.app.utils.timber.DebugTree
import dev.sanmer.app.utils.timber.ReleaseTree
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    init {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.d("onCreate")
    }
}