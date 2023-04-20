package com.nmel.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by Nolann Méléard on 20 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */

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
                Text(
                    text = stringResource(R.string.loading),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    style = TextStyle(
                        fontSize = 16.sp,
                    ),
                )
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
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
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 20.dp)
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