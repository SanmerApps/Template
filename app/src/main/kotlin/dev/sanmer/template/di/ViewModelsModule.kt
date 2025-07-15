package dev.sanmer.template.di

import dev.sanmer.template.ui.main.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val ViewModels = module {
    viewModelOf(::MainViewModel)
}