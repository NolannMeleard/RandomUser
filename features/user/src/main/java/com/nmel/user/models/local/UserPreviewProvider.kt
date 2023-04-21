package com.nmel.user.models.local

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

/**
 * Created by Nolann Méléard on 19 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
class UserPreviewProvider : PreviewParameterProvider<User> {
    override val values: Sequence<User> = sequenceOf(
        User(
            location = Location(
                street = Street(
                    number = 4890,
                    name = "Valwood Pkwy"
                ),
                coordinates = Coordinates(
                    longitude = "-69.8246",
                    latitude = "134.8719"
                ),
                city = "Billings",
                state = "Michigan",
                country = "United States",
                postcode = "63104"
            ),
            dob = Dob(
                date = "1992-03-08T15:13:16.688Z",
                age = 30
            ),
            login = Login(
                username = "yellowpeacock117",
            ),
            picture = Picture(
                large = "https://randomuser.me/api/portraits/men/75.jpg",
                medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
            ),
            name = UserName(
                title = "Miss",
                first = "Jennie",
                last = "Nichols"
            ),
            email = "jennie.nichols@example.com",
            nat = "US",
            phone = "(272) 790-0888",
            cell = "(272) 790-0999",
            gender = "female"
        )
    )
}