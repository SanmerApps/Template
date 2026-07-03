package dev.sanmer.template.ui.screen.license

import android.content.Intent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.sanmer.template.R
import dev.sanmer.template.model.LoadData
import dev.sanmer.template.model.ui.UiLicense
import dev.sanmer.template.ui.component.Finished
import dev.sanmer.template.ui.component.LabelText
import dev.sanmer.template.ui.component.Loading
import dev.sanmer.template.ui.ktx.plus
import dev.sanmer.template.ui.ktx.surface

@Composable
fun LicenseScreen(
    viewModel: LicenseViewModel,
    onBack: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            TopBar(
                onBack = onBack,
                scrollBehavior = scrollBehavior
            )
        }
    ) { contentPadding ->
        Crossfade(
            targetState = viewModel.data
        ) {
            when (it) {
                LoadData.Pending, LoadData.Loading -> Loading(
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxSize()
                )

                is LoadData.Success<List<UiLicense>> -> LicenseList(
                    list = it.value,
                    contentPadding = contentPadding,
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
                )

                is LoadData.Failure -> Finished(
                    label = it.error.message ?: it.error.javaClass.name,
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun LicenseList(
    list: List<UiLicense>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState()
) = LazyColumn(
    modifier = modifier,
    state = listState,
    contentPadding = PaddingValues(all = 20.dp) + contentPadding,
    verticalArrangement = Arrangement.spacedBy(20.dp)
) {
    items(list) {
        LicenseItem(it)
    }
}

@Composable
private fun LicenseItem(
    license: UiLicense
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .surface(
                shape = MaterialTheme.shapes.large,
                backgroundColor = MaterialTheme.colorScheme.surface,
                border = CardDefaults.outlinedCardBorder(false)
            )
            .clickable(
                onClick = {
                    context.startActivity(
                        Intent.parseUri(license.url, Intent.URI_INTENT_SCHEME)
                    )
                },
                enabled = license.hasUrl
            )
            .padding(all = 20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        ValueText(
            title = license.name,
            value = license.dependency
        )

        FlowRow(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            LabelText(
                text = license.version
            )

            license.spdxLicenses.forEach {
                LabelText(
                    text = it.name
                )
            }

            license.unknownLicenses.forEach {
                LabelText(
                    text = it.name.ifEmpty { it.url }
                )
            }
        }
    }
}

@Composable
private fun ValueText(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium
    )

    Text(
        text = value,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.outline
    )
}

@Composable
private fun TopBar(
    onBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) = TopAppBar(
    title = { Text(text = stringResource(R.string.licenses_title)) },
    navigationIcon = {
        IconButton(
            onClick = onBack
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_left),
                contentDescription = null
            )
        }
    },
    scrollBehavior = scrollBehavior
)