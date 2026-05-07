package dev.sanmer.template.ui.di

import androidx.navigation3.runtime.NavBackStack
import dev.sanmer.template.ui.screen.Screen
import dev.sanmer.template.ui.screen.home.HomeScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.scope.dsl.activityRetainedScope
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val Navigation = module {
    includes(ViewModels)

    activityRetainedScope {
        scoped { NavBackStack(Screen.Home) }

        navigation<Screen.Home> {
            HomeScreen(
                viewModel = koinViewModel(),
            )
        }
    }
}