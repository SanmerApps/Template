package dev.sanmer.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import dev.sanmer.app.ui.main.MainScreen
import dev.sanmer.app.ui.theme.AppTheme
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Timber.d("onCreate")

        setContent {
            AppTheme {
                MainScreen()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        Timber.d("onDestroy")
    }
}