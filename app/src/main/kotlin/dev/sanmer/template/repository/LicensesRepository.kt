package dev.sanmer.template.repository

import dev.sanmer.template.model.license.Artifact

interface LicensesRepository {
    suspend fun fetch(): List<Artifact>
}