package dev.sanmer.template.ui.theme

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import dev.sanmer.template.compat.BuildCompat

@Composable
fun AppTheme(
    darkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = getColorScheme(darkMode)

    SystemBarStyle(
        darkMode = darkMode
    )

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = Shapes,
        typography = Typography,
        content = content
    )
}

@Composable
private fun getColorScheme(darkMode: Boolean): ColorScheme {
    val context = LocalContext.current
    return if (BuildCompat.atLeastS) {
        when {
            darkMode -> dynamicDarkColorScheme(context)
            else -> dynamicLightColorScheme(context)
        }
    } else {
        when {
            darkMode -> DarkColorScheme
            else -> LightColorScheme
        }
    }
}

@Composable
private fun SystemBarStyle(
    darkMode: Boolean,
    statusBarScrim: Color = Color.Transparent,
    navigationBarScrim: Color = Color.Transparent
) {
    val context = LocalContext.current
    val activity = context as ComponentActivity

    SideEffect {
        activity.enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                statusBarScrim.toArgb(),
                statusBarScrim.toArgb(),
            ) { darkMode },
            navigationBarStyle = when {
                darkMode -> SystemBarStyle.dark(
                    navigationBarScrim.toArgb()
                )

                else -> SystemBarStyle.light(
                    navigationBarScrim.toArgb(),
                    navigationBarScrim.toArgb(),
                )
            }
        )
    }
}