package com.nmel.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.nmel.core_ui.ParentFragmentWithBinding
import com.nmel.core_ui.theme.RandomUserTheme
import com.nmel.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Nolann Méléard on 16 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */

@AndroidEntryPoint
class HomeFragment : ParentFragmentWithBinding<FragmentHomeBinding>() {
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.composeView.apply {
            defineContentOfComposeView(this) {
                RandomUserTheme {
                    HomeScreen(
                        Modifier.background(MaterialTheme.colorScheme.background),
                        viewModel = viewModel
                    )
                }
            }
        }
        return binding.root
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
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
        containerColor = MaterialTheme.colorScheme.secondary,
    ) {
        LazyColumn(state = lazyColumnState, modifier = modifier.padding(it)) {
            items(items = userList.orEmpty(), key = { it.email }) {
                HomeUserCardItem(
                    userData = it,
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp)
                ) {

                }
            }
            item(
                key = viewModel.listState,
            ) {
                when (viewModel.listState) {
                    ListState.LOADING -> {

                    }

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
