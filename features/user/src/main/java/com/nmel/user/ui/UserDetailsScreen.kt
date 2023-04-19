package com.nmel.user.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.nmel.core_ui.composable.BackButton
import com.nmel.user.models.local.User
import com.nmel.user.ui.details.ContactInformation
import com.nmel.user.ui.details.PersonalInformation
import com.nmel.user.ui.details.UserHeader

/**
 * Created by Nolann Méléard on 19 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreen(modifier: Modifier = Modifier, user: User, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "User") },
                navigationIcon = { BackButton(onClick = onBackClick) },
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(it)
        ) {
            UserDetailsScreenContent(user = user)
        }
    }
}

@Composable
fun UserDetailsScreenContent(modifier: Modifier = Modifier, user: User) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .padding(vertical = 20.dp)
            .shadow(
                3.dp,
                shape = MaterialTheme.shapes.large
            )
            .clip(MaterialTheme.shapes.large)
            .background(
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colorScheme.primaryContainer
            ),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        UserHeader(
            user = user, modifier = Modifier
                .fillMaxWidth()
        )

        ContactInformation(
            user = user,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        PersonalInformation(
            user = user,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )
    }
}