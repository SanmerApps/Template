package dev.sanmer.template.ui.ktx

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.unit.Dp

fun CornerBasedShape.top(size: Dp) = copy(
    topStart = CornerSize(size), topEnd = CornerSize(size)
)

fun CornerBasedShape.bottom(size: Dp) = copy(
    bottomStart = CornerSize(size), bottomEnd = CornerSize(size)
)

infix fun CornerBasedShape.topWith(other: CornerBasedShape) = copy(
    topStart = other.topStart, topEnd = other.topEnd
)

infix fun CornerBasedShape.bottomWith(other: CornerBasedShape) = copy(
    bottomStart = other.bottomStart, bottomEnd = other.bottomEnd
)