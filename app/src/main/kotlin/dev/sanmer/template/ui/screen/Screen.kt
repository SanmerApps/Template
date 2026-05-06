package dev.sanmer.template.ui.screen

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Screen : NavKey {
    @Serializable
    data object Home : Screen
}