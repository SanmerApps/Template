package dev.sanmer.template.ui.screen.dependency

import android.content.Context
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
import dev.sanmer.template.model.dependency.Dependency
import dev.sanmer.template.ui.component.Finished
import dev.sanmer.template.ui.component.LabelText
import dev.sanmer.template.ui.component.Loading
import dev.sanmer.template.ui.ktx.plus
import dev.sanmer.template.ui.ktx.surface

@Composable
fun DependencyScreen(
    viewModel: DependencyViewModel,
    goBack: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            TopBar(
                onBack = goBack,
                scrollBehavior = scrollBehavior
            )
        }
    ) { contentPadding ->
        Crossfade(
            targetState = viewModel.data
        ) { data ->
            data.onLoading {
                Loading(
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxSize()
                )
            }.onSuccess {
                DependencyList(
                    list = it,
                    contentPadding = contentPadding,
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
                )
            }.onFailure {
                Finished(
                    label = it.message ?: it.javaClass.name,
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun DependencyList(
    list: List<Dependency>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState()
) = LazyColumn(
    modifier = modifier,
    state = listState,
    contentPadding = PaddingValues(15.dp) + contentPadding,
    verticalArrangement = Arrangement.spacedBy(15.dp)
) {
    items(list) {
        DependencyItem(it)
    }
}

@Composable
private fun DependencyItem(
    dependency: Dependency,
    context: Context = LocalContext.current
) = Column(
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
                    Intent.parseUri(dependency.scm.url, Intent.URI_INTENT_SCHEME)
                )
            },
            enabled = dependency.scm.url.isNotEmpty()
        )
        .padding(20.dp)
) {
    Text(
        text = dependency.name.ifEmpty { dependency.artifactId },
        style = MaterialTheme.typography.titleMedium
    )

    Text(
        text = "${dependency.groupId}:${dependency.artifactId}",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.outline
    )

    FlowRow(
        modifier = Modifier.padding(top = 15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        LabelText(
            text = dependency.version
        )

        dependency.spdxLicenses.forEach {
            LabelText(
                text = it.name
            )
        }

        dependency.unknownLicenses.forEach {
            LabelText(
                text = it.name.ifEmpty { it.url }
            )
        }
    }
}

@Composable
private fun TopBar(
    onBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) = TopAppBar(
    title = { Text(text = stringResource(R.string.dependency_title)) },
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