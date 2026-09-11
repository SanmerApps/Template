package dev.sanmer.template.di

import dev.sanmer.template.repository.AssetsRepository
import dev.sanmer.template.repository.AssetsRepositoryImpl
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

val RepositoriesModule = module {
    single<AssetsRepositoryImpl>() bind AssetsRepository::class
}