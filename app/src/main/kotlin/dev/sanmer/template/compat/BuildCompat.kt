package dev.sanmer.template.compat

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

object BuildCompat {
    @get:ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
    val atLeastS inline get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
}