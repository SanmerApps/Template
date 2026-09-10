package dev.sanmer.template.repository

import dev.sanmer.template.model.dependency.Dependency

interface AssetsRepository {
    suspend fun getDependencies(): List<Dependency>
}