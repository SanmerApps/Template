package dev.sanmer.template.repository

import android.content.Context
import dev.sanmer.template.Const
import dev.sanmer.template.model.dependency.Dependency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

class AssetsRepositoryImpl(
    private val context: Context
) : AssetsRepository {
    override suspend fun getDependencies(): List<Dependency> = withContext(Dispatchers.IO) {
        context.assets.open(Const.LICENSEE_PATH).use { stream ->
            Json.decodeFromStream(stream)
        }
    }
}