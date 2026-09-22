package dev.sanmer.template.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    init {
        Log.d(TAG, "init")
    }

    private companion object Default {
        const val TAG = "HomeViewModel"
    }
}