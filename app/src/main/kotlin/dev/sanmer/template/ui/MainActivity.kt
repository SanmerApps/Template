package dev.sanmer.template.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dev.sanmer.template.Logger
import dev.sanmer.template.ui.screen.main.MainScreen
import dev.sanmer.template.ui.theme.AppTheme
import org.koin.android.ext.android.get
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.compose.navigation3.getEntryProvider
import org.koin.androidx.scope.activityRetainedScope
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.scope.Scope

@OptIn(KoinExperimentalAPI::class)
class MainActivity : ComponentActivity(), AndroidScopeComponent {
    override val scope: Scope by activityRetainedScope()
    private val logger = Logger.Android("MainActivity")

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        logger.d("onCreate")

        setContent {
            AppTheme {
                MainScreen(
                    backStack = get(),
                    entryProvider = getEntryProvider()
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        logger.d("onDestroy")
    }
}