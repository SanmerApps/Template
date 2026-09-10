package dev.sanmer.template.ui.screen.dependency

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.sanmer.template.Logger
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

    private val logger = Logger.Android("DependencyViewModel")

    init {
        logger.d("init")
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            data = loadData {
                assetsRepository.getDependencies()
            }.onFailure {
                logger.e(it)
            }
        }
    }
}