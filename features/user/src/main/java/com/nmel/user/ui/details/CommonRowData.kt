package com.nmel.user.ui.details

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nmel.core_ui.theme.RandomUserTheme
import com.nmel.user.R

/**
 * Created by Nolann Méléard on 19 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */

@Composable
fun CommonRowData(
    modifier: Modifier = Modifier,
    @DrawableRes iconResId: Int? = null,
    title: String,
    content: String
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        if (iconResId != null) {
            Icon(
                painter = painterResource(iconResId),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(start = 8.dp)

        )

        Text(
            text = content,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = TextStyle(
                fontSize = 14.sp,
            ),
            modifier = Modifier
                .padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CommonRowDataPreviewWithIcon() {
    RandomUserTheme {
        CommonRowData(
            iconResId = R.drawable.ic_cake,
            title = "Title",
            content = "Description Content"
        )

    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CommonRowDataPreviewWithoutIcon() {
    RandomUserTheme {
        CommonRowData(title = "Title", content = "Description Content")

    }
}