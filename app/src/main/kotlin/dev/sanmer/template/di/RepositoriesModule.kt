package dev.sanmer.template.di

import dev.sanmer.template.repository.LicensesRepository
import dev.sanmer.template.repository.LicensesRepositoryImpl
import org.koin.dsl.module

val Repositories = module {
    single<LicensesRepository> {
        LicensesRepositoryImpl(get())
    }
}