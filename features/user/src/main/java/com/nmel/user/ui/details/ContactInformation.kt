package com.nmel.user.ui.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nmel.core_ui.theme.RandomUserTheme
import com.nmel.user.R
import com.nmel.user.models.local.User
import com.nmel.user.models.local.UserPreviewProvider

/**
 * Created by Nolann Méléard on 19 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */

@Composable
fun ContactInformation(modifier: Modifier = Modifier, user: User) {
    Column(modifier = modifier.padding(bottom = 8.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Divider(
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            thickness = 0.5.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
        )

        Text(
            text = stringResource(R.string.contact_title),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(start = 12.dp)
                .padding(bottom = 4.dp)
        )

        CommonRowData(
            iconResId = R.drawable.ic_email,
            title = stringResource(R.string.email_title),
            content = user.email,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        CommonRowData(
            iconResId = R.drawable.ic_person,
            title = stringResource(R.string.username_title),
            content = user.login.username,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        CommonRowData(
            iconResId = R.drawable.ic_phone,
            title = stringResource(R.string.cellphone_title),
            content = user.cell,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        CommonRowData(
            iconResId = R.drawable.ic_home,
            title = stringResource(R.string.home_title),
            content = user.phone,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ContactInformationPreview(@PreviewParameter(UserPreviewProvider::class) user: User) {
    RandomUserTheme {
        ContactInformation(user = user)
    }
}