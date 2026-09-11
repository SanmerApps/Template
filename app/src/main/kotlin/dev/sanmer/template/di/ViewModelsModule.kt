package dev.sanmer.template.di

import dev.sanmer.template.ui.screen.dependency.DependencyViewModel
import dev.sanmer.template.ui.screen.home.HomeViewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val ViewModelsModule = module {
    includes(RepositoriesModule)
    viewModel<HomeViewModel>()
    viewModel<DependencyViewModel>()
}