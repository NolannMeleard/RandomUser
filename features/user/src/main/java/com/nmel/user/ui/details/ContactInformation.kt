package com.nmel.user.ui.details

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nmel.user.R
import com.nmel.user.models.local.User

/**
 * Created by Nolann Méléard on 19 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */

@Composable
fun ContactInformation(modifier: Modifier = Modifier, user: User) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Divider(
            color = Color.LightGray,
            thickness = 0.5.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
        )

        Text(
            text = "Contact :",
            color = MaterialTheme.colorScheme.onPrimary,
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
            title = "Email :",
            content = user.email,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        CommonRowData(
            iconResId = R.drawable.ic_person,
            title = "Username :",
            content = user.login.username,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        CommonRowData(
            iconResId = R.drawable.ic_phone,
            title = "Cellphone :",
            content = user.cell,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        CommonRowData(
            iconResId = R.drawable.ic_home,
            title = "Home :",
            content = user.phone,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}