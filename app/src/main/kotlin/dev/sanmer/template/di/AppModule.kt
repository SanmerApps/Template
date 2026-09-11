package dev.sanmer.template.di

import androidx.navigation3.runtime.NavBackStack
import dev.sanmer.template.ui.screen.Screen
import dev.sanmer.template.ui.screen.dependency.DependencyScreen
import dev.sanmer.template.ui.screen.home.HomeScreen
import org.koin.androidx.scope.dsl.activityRetainedScope
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val AppModule = module {
    includes(ViewModelsModule)

    activityRetainedScope {
        scoped { NavBackStack(Screen.Home) }

        navigation<Screen.Home> {
            val backStack = get<NavBackStack<Screen>>()
            HomeScreen(
                viewModel = koinViewModel(),
                goTo = backStack::add
            )
        }

        navigation<Screen.Dependency> {
            val backStack = get<NavBackStack<Screen>>()
            DependencyScreen(
                viewModel = koinViewModel(),
                goBack = backStack::removeLastOrNull
            )
        }
    }
}