package dev.sanmer.template.ui.ktx

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

operator fun PaddingValues.plus(other: PaddingValues): PaddingValues =
    OperatorPaddingValues(this, other, Dp::plus)

operator fun PaddingValues.minus(other: PaddingValues): PaddingValues =
    OperatorPaddingValues(this, other, Dp::minus)

@Immutable
class OperatorPaddingValues(
    private val that: PaddingValues,
    private val other: PaddingValues,
    private val operator: Dp.(Dp) -> Dp,
) : PaddingValues {
    override fun calculateBottomPadding(): Dp =
        operator(
            that.calculateBottomPadding(),
            other.calculateBottomPadding()
        )

    override fun calculateLeftPadding(layoutDirection: LayoutDirection): Dp =
        operator(
            that.calculateLeftPadding(layoutDirection),
            other.calculateLeftPadding(layoutDirection)
        )

    override fun calculateRightPadding(layoutDirection: LayoutDirection): Dp =
        operator(
            that.calculateRightPadding(layoutDirection),
            other.calculateRightPadding(layoutDirection)
        )

    override fun calculateTopPadding(): Dp =
        operator(
            that.calculateTopPadding(),
            other.calculateTopPadding()
        )
}

fun PaddingValues.horizontal() = HorizontalPaddingValues(this)

@Immutable
class HorizontalPaddingValues(
    private val padding: PaddingValues
) : PaddingValues {
    override fun calculateLeftPadding(layoutDirection: LayoutDirection) =
        padding.calculateLeftPadding(layoutDirection)

    override fun calculateTopPadding() = 0.dp

    override fun calculateRightPadding(layoutDirection: LayoutDirection) =
        padding.calculateRightPadding(layoutDirection)

    override fun calculateBottomPadding() = 0.dp
}

fun PaddingValues.vertical() = VerticalPaddingValues(this)

@Immutable
class VerticalPaddingValues(
    private val padding: PaddingValues
) : PaddingValues {
    override fun calculateLeftPadding(layoutDirection: LayoutDirection) = 0.dp

    override fun calculateTopPadding() = padding.calculateTopPadding()

    override fun calculateRightPadding(layoutDirection: LayoutDirection) = 0.dp

    override fun calculateBottomPadding() = padding.calculateBottomPadding()
}