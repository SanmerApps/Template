package dev.sanmer.template.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.sanmer.template.Logger
import dev.sanmer.template.model.LoadData
import dev.sanmer.template.model.LoadData.Default.asLoadData
import dev.sanmer.template.model.ui.UiLicense
import dev.sanmer.template.repository.LicensesRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val licensesRepository: LicensesRepository
) : ViewModel() {
    var data by mutableStateOf<LoadData<List<UiLicense>>>(LoadData.Loading)
        private set

    private val logger = Logger.Android("MainViewModel")

    init {
        logger.d("init")
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            data = runCatching {
                licensesRepository.fetch().map(::UiLicense)
            }.onFailure {
                logger.e(it)
            }.asLoadData()
        }
    }
}