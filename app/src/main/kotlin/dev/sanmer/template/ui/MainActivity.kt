package dev.sanmer.template.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dev.sanmer.template.Logger
import dev.sanmer.template.ui.main.MainScreen
import dev.sanmer.template.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    private val logger = Logger.Android("MainActivity")

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        logger.d("onCreate")

        setContent {
            AppTheme {
                MainScreen()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        logger.d("onDestroy")
    }
}