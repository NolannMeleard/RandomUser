package com.nmel.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nmel.user.models.local.User

/**
 * Created by Nolann Méléard on 19 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onCardClick: (User) -> Unit
) {
    val lazyColumnState = rememberLazyListState()
    val userList by viewModel.usersData.observeAsState()
    val shouldStartPaginate = remember {
        derivedStateOf {
            viewModel.canPaginate && (lazyColumnState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: -1) >= (lazyColumnState.layoutInfo.totalItemsCount - 10)
        }
    }

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if (shouldStartPaginate.value && viewModel.listState == ListState.IDLE)
            viewModel.getUsers()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Contact list")
            })
        },
        containerColor = MaterialTheme.colorScheme.background,
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
                when (viewModel.listState) {
                    ListState.LOADING -> {}
                    ListState.PAGINATING -> {
                        Column(
                            modifier = Modifier
                                .size(50.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    ListState.PAGINATION_EXHAUST -> {

                    }
                    else -> {}
                }
            }
        }
    }
}


