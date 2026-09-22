package dev.sanmer.template.ui.screen.dependency

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.sanmer.template.model.LoadData
import dev.sanmer.template.model.LoadData.Default.loadData
import dev.sanmer.template.model.dependency.Dependency
import dev.sanmer.template.repository.AssetsRepository
import kotlinx.coroutines.launch

class DependencyViewModel(
    private val assetsRepository: AssetsRepository
) : ViewModel() {
    var data by mutableStateOf<LoadData<List<Dependency>>>(LoadData.Loading)
        private set

    init {
        Log.d(TAG, "init")
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            data = loadData {
                assetsRepository.getDependencies()
            }.onFailure {
                Log.e(TAG, "getDependencies", it)
            }
        }
    }

    private companion object Default {
        const val TAG = "DependencyViewModel"
    }
}