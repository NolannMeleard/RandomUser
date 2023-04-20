package com.nmel.core_ui.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nmel.core_ui.R
import com.nmel.core_ui.theme.RandomUserTheme

/**
 * Created by Nolann Méléard on 19 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
@Composable
fun BackButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier
                .size(18.dp),
            painter = painterResource(R.drawable.ic_back_arrow),
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BackButtonPreview() {
    RandomUserTheme {
        BackButton(
            onClick = {}
        )
    }
}