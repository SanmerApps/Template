package dev.sanmer.template.ui.screen.home

import androidx.lifecycle.ViewModel
import dev.sanmer.template.Logger

class HomeViewModel : ViewModel() {
    private val logger = Logger.Android("HomeViewModel")

    init {
        logger.d("init")
    }
}