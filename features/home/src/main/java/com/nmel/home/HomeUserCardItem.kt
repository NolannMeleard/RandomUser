package com.nmel.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.nmel.user.models.local.User
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

/**
 * Created by Nolann Méléard on 18 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUserCardItem(modifier: Modifier = Modifier, userData: User, onCardClick: (User) -> Unit) {
    ElevatedCard(
        onClick = {
            onCardClick(userData)
        },
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(4.2f),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(15.dp)
        ) {
            val (userThumbnail, userName) = createRefs()

            GlideImage(
                imageModel = { userData.picture.thumbnail },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit,
                ),
                loading = {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    )
                },

                modifier = Modifier
                    .clip(CircleShape)
                    .constrainAs(userThumbnail) {
                        top.linkTo(parent.top, margin = 4.dp)
                        start.linkTo(parent.start, margin = 8.dp)
                        bottom.linkTo(parent.bottom, margin = 4.dp)
                        height = Dimension.fillToConstraints
                        width = Dimension.wrapContent
                    }
            )

            Text(
                text = "${userData.name.first} ${userData.name.last}",
                color = Color.DarkGray,
                modifier = Modifier.constrainAs(userName) {
                    top.linkTo(parent.top)
                    start.linkTo(userThumbnail.end, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                }
            )
        }
    }
}