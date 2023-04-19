package com.nmel.user.ui.details

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.nmel.user.models.local.User
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

/**
 * Created by Nolann Méléard on 19 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */

@Composable
fun UserHeader(modifier: Modifier = Modifier, user: User) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (userPicture, userFName, userLName) = createRefs()

        GlideImage(
            imageModel = { user.picture.large },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Fit,
            ),
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .constrainAs(userPicture) {
                    top.linkTo(parent.top, 20.dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
        )
        Text(
            text = "${user.name.title} ${user.name.first} ${user.name.last}",
            color = MaterialTheme.colorScheme.onPrimary,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.constrainAs(userLName) {
                top.linkTo(userPicture.bottom, 8.dp)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = "from ${user.location.country}",
            color = MaterialTheme.colorScheme.onPrimary,
            style = TextStyle(
                fontSize = 16.sp
            ),
            modifier = Modifier.constrainAs(userFName) {
                top.linkTo(userLName.bottom, 2.dp)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }
        )
    }
}
