package com.nmel.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nmel.user.models.local.User
import kotlinx.coroutines.launch

/**
 * Created by Nolann Méléard on 19 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onCardClick: (User) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val lazyColumnState = rememberLazyListState()
    val userList by viewModel.usersData.observeAsState()
    val shouldStartPaginate = remember {
        derivedStateOf {
            viewModel.canPaginate && (lazyColumnState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: -1) >= (lazyColumnState.layoutInfo.totalItemsCount - 10)
        }
    }

    val hasReachBottom = remember {
        derivedStateOf {
            lazyColumnState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == userList?.size && viewModel.listState == ListState.NOT_CONNECTED
        }
    }

    val pullRefreshState =
        rememberPullRefreshState(viewModel.listState == ListState.LOADING, {
            viewModel.listState = ListState.LOADING
            viewModel.getUsers(shouldRefresh = true)
        })

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if ((shouldStartPaginate.value && viewModel.listState == ListState.IDLE))
            viewModel.getUsers()
    }

    LaunchedEffect(key1 = hasReachBottom.value) {
        if (viewModel.listState == ListState.NOT_CONNECTED)
            viewModel.getUsers()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(R.string.scaffold_title),
                    modifier.clickable {
                        coroutineScope.launch {
                            lazyColumnState.animateScrollToItem(0)
                        }
                    })
            })
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Box(
            modifier = modifier
                .pullRefresh(pullRefreshState)
        ) {
            LazyColumn(state = lazyColumnState, modifier = modifier.padding(it)) {
                items(items = userList.orEmpty(), key = { item -> item.email }) { user ->
                    HomeUserCardItem(
                        userData = user,
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp),
                        onCardClick = onCardClick
                    )
                }
                item(
                    key = viewModel.listState,
                ) {
                    ListStateViews(viewModel.listState)
                }
            }

            PullRefreshIndicator(
                viewModel.listState == ListState.LOADING,
                pullRefreshState,
                Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
fun ListStateViews(listState: ListState) {
    when (listState) {
        ListState.LOADING -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(R.string.loading))
                CircularProgressIndicator()
            }
        }

        ListState.NOT_CONNECTED -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.list_state_not_connected_limit),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)
                )
            }
        }

        ListState.ERROR -> {
            Column(
                modifier = Modifier
                    .height(LocalConfiguration.current.screenHeightDp.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.local_fetch_error),
                    color = MaterialTheme.colorScheme.error,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)
                )
            }
        }

        else -> {}
    }
}


