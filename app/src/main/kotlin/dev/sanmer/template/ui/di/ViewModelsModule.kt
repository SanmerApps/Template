package dev.sanmer.template.ui.di

import dev.sanmer.template.ui.screen.home.HomeViewModel
import dev.sanmer.template.ui.screen.license.LicenseViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val ViewModels = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::LicenseViewModel)
}