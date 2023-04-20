package com.nmel.user.ui.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nmel.core_common.DateUtils
import com.nmel.core_ui.theme.RandomUserTheme
import com.nmel.user.R
import com.nmel.user.models.local.Location
import com.nmel.user.models.local.User
import com.nmel.user.models.local.UserPreviewProvider

/**
 * Created by Nolann Méléard on 19 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
@Composable
fun PersonalInformation(modifier: Modifier = Modifier, user: User) {
    val formattedDate = DateUtils.parseDateAndFormatToDisplay(user.dob.date)
    Column(
        modifier = modifier.padding(bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Divider(
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            thickness = 0.5.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
        )

        Text(
            text = stringResource(R.string.personal_information_title),
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
            iconResId = R.drawable.ic_cake,
            title = stringResource(R.string.age_title),
            content = user.dob.age.toString(),
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        CommonRowData(
            iconResId = R.drawable.ic_calendar,
            title = stringResource(R.string.birthday_title),
            content = formattedDate,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        CommonRowData(
            iconResId = R.drawable.ic_home,
            title = stringResource(R.string.address_title),
            content = Location.formatLocationToString(user.location),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PersonalInformationPreview(@PreviewParameter(UserPreviewProvider::class) user: User) {
    RandomUserTheme {
        PersonalInformation(user = user)
    }
}