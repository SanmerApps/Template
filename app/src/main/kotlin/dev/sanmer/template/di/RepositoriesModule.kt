package dev.sanmer.template.di

import dev.sanmer.template.repository.AssetsRepository
import dev.sanmer.template.repository.AssetsRepositoryImpl
import org.koin.dsl.module

val Repositories = module {
    single<AssetsRepository> {
        AssetsRepositoryImpl(get())
    }
}