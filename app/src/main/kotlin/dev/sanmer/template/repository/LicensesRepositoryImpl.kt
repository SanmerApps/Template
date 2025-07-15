package dev.sanmer.template.repository

import android.content.Context
import dev.sanmer.template.Const
import dev.sanmer.template.model.license.Artifact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

class LicensesRepositoryImpl(
    private val context: Context
) : LicensesRepository {
    override suspend fun fetch(): List<Artifact> = withContext(Dispatchers.IO) {
        context.assets.open(Const.LICENSEE_PATH).use { stream ->
            Json.decodeFromStream(stream)
        }
    }
}