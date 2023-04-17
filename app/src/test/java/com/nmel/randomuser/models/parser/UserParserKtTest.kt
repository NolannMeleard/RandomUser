package com.nmel.randomuser.models.parser

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nmel.randomuser.RandomUserTestApplication
import com.nmel.user.models.api.ApiCoordinates
import com.nmel.user.models.api.ApiDob
import com.nmel.user.models.api.ApiLocation
import com.nmel.user.models.api.ApiLogin
import com.nmel.user.models.api.ApiPicture
import com.nmel.user.models.api.ApiStreet
import com.nmel.user.models.api.ApiUser
import com.nmel.user.models.api.ApiUserName
import com.nmel.user.models.local.Coordinates
import com.nmel.user.models.local.Dob
import com.nmel.user.models.local.Location
import com.nmel.user.models.local.Login
import com.nmel.user.models.local.Picture
import com.nmel.user.models.local.Street
import com.nmel.user.models.local.User
import com.nmel.user.models.local.UserName
import com.nmel.user.models.parser.formatUserParsingException
import com.nmel.user.models.parser.fromApiUser
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
@RunWith(AndroidJUnit4::class)
@Config(application = RandomUserTestApplication::class)
class UserParserKtTest {
    @Test
    fun `fromApiUser_ensure failure when nat is null`() {
        // given
        val apiUser = ApiUser(
            location = ApiLocation(
                street = ApiStreet(
                    number = 4890,
                    name = "Valwood Pkwy"
                ),
                coordinates = ApiCoordinates(
                    longitude = "-69.8246",
                    latitude = "134.8719"
                ),
                city = "Billings",
                state = "Michigan",
                country = "United States",
                postcode = "63104"
            ),
            dob = ApiDob(
                date = "1992-03-08T15:13:16.688Z",
                age = 30
            ),
            login = ApiLogin(
                username = "yellowpeacock117",
            ),
            picture = ApiPicture(
                large = "https://randomuser.me/api/portraits/men/75.jpg",
                medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
            ),
            name = ApiUserName(
                title = "Miss",
                first = "Jennie",
                last = "Nichols"
            ),
            email = "jennie.nichols@example.com",
            nat = null,
            phone = "(272) 790-0888",
            cell = "(272) 790-0999",
            gender = "female"
        )

        // when
        val actual = User.fromApiUser(apiUser)

        // then
        val expected = Result.failure<User>(
            formatUserParsingException(apiUser, "nat", apiUser.nat)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiUser_ensure failure when nat is blank`() {
        // given
        val apiUser = ApiUser(
            location = ApiLocation(
                street = ApiStreet(
                    number = 4890,
                    name = "Valwood Pkwy"
                ),
                coordinates = ApiCoordinates(
                    longitude = "-69.8246",
                    latitude = "134.8719"
                ),
                city = "Billings",
                state = "Michigan",
                country = "United States",
                postcode = "63104"
            ),
            dob = ApiDob(
                date = "1992-03-08T15:13:16.688Z",
                age = 30
            ),
            login = ApiLogin(
                username = "yellowpeacock117",
            ),
            picture = ApiPicture(
                large = "https://randomuser.me/api/portraits/men/75.jpg",
                medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
            ),
            name = ApiUserName(
                title = "Miss",
                first = "Jennie",
                last = "Nichols"
            ),
            email = "jennie.nichols@example.com",
            nat = "",
            phone = "(272) 790-0888",
            cell = "(272) 790-0999",
            gender = "female"
        )

        // when
        val actual = User.fromApiUser(apiUser)

        // then
        val expected = Result.failure<User>(
            formatUserParsingException(apiUser, "nat", apiUser.nat)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiUser_ensure failure when email is null`() {
        // given
        val apiUser = ApiUser(
            location = ApiLocation(
                street = ApiStreet(
                    number = 4890,
                    name = "Valwood Pkwy"
                ),
                coordinates = ApiCoordinates(
                    longitude = "-69.8246",
                    latitude = "134.8719"
                ),
                city = "Billings",
                state = "Michigan",
                country = "United States",
                postcode = "63104"
            ),
            dob = ApiDob(
                date = "1992-03-08T15:13:16.688Z",
                age = 30
            ),
            login = ApiLogin(
                username = "yellowpeacock117",
            ),
            picture = ApiPicture(
                large = "https://randomuser.me/api/portraits/men/75.jpg",
                medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
            ),
            name = ApiUserName(
                title = "Miss",
                first = "Jennie",
                last = "Nichols"
            ),
            email = null,
            nat = "US",
            phone = "(272) 790-0888",
            cell = "(272) 790-0999",
            gender = "female"
        )

        // when
        val actual = User.fromApiUser(apiUser)

        // then
        val expected = Result.failure<User>(
            formatUserParsingException(apiUser, "email", apiUser.email)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiUser_ensure failure when email is blank`() {
        // given
        val apiUser = ApiUser(
            location = ApiLocation(
                street = ApiStreet(
                    number = 4890,
                    name = "Valwood Pkwy"
                ),
                coordinates = ApiCoordinates(
                    longitude = "-69.8246",
                    latitude = "134.8719"
                ),
                city = "Billings",
                state = "Michigan",
                country = "United States",
                postcode = "63104"
            ),
            dob = ApiDob(
                date = "1992-03-08T15:13:16.688Z",
                age = 30
            ),
            login = ApiLogin(
                username = "yellowpeacock117",
            ),
            picture = ApiPicture(
                large = "https://randomuser.me/api/portraits/men/75.jpg",
                medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
            ),
            name = ApiUserName(
                title = "Miss",
                first = "Jennie",
                last = "Nichols"
            ),
            email = "",
            nat = "US",
            phone = "(272) 790-0888",
            cell = "(272) 790-0999",
            gender = "female"
        )

        // when
        val actual = User.fromApiUser(apiUser)

        // then
        val expected = Result.failure<User>(
            formatUserParsingException(apiUser, "email", apiUser.email)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiUser_ensure failure when phone is null`() {
        // given
        val apiUser = ApiUser(
            location = ApiLocation(
                street = ApiStreet(
                    number = 4890,
                    name = "Valwood Pkwy"
                ),
                coordinates = ApiCoordinates(
                    longitude = "-69.8246",
                    latitude = "134.8719"
                ),
                city = "Billings",
                state = "Michigan",
                country = "United States",
                postcode = "63104"
            ),
            dob = ApiDob(
                date = "1992-03-08T15:13:16.688Z",
                age = 30
            ),
            login = ApiLogin(
                username = "yellowpeacock117",
            ),
            picture = ApiPicture(
                large = "https://randomuser.me/api/portraits/men/75.jpg",
                medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
            ),
            name = ApiUserName(
                title = "Miss",
                first = "Jennie",
                last = "Nichols"
            ),
            email = "jennie.nichols@example.com",
            nat = "US",
            phone = null,
            cell = "(272) 790-0999",
            gender = "female"
        )

        // when
        val actual = User.fromApiUser(apiUser)

        // then
        val expected = Result.failure<User>(
            formatUserParsingException(apiUser, "phone", apiUser.phone)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiUser_ensure failure when phone is blank`() {
        // given
        val apiUser = ApiUser(
            location = ApiLocation(
                street = ApiStreet(
                    number = 4890,
                    name = "Valwood Pkwy"
                ),
                coordinates = ApiCoordinates(
                    longitude = "-69.8246",
                    latitude = "134.8719"
                ),
                city = "Billings",
                state = "Michigan",
                country = "United States",
                postcode = "63104"
            ),
            dob = ApiDob(
                date = "1992-03-08T15:13:16.688Z",
                age = 30
            ),
            login = ApiLogin(
                username = "yellowpeacock117",
            ),
            picture = ApiPicture(
                large = "https://randomuser.me/api/portraits/men/75.jpg",
                medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
            ),
            name = ApiUserName(
                title = "Miss",
                first = "Jennie",
                last = "Nichols"
            ),
            email = "jennie.nichols@example.com",
            nat = "US",
            phone = "",
            cell = "(272) 790-0999",
            gender = "female"
        )

        // when
        val actual = User.fromApiUser(apiUser)

        // then
        val expected = Result.failure<User>(
            formatUserParsingException(apiUser, "phone", apiUser.phone)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiUser_ensure failure when cell is null`() {
        // given
        val apiUser = ApiUser(
            location = ApiLocation(
                street = ApiStreet(
                    number = 4890,
                    name = "Valwood Pkwy"
                ),
                coordinates = ApiCoordinates(
                    longitude = "-69.8246",
                    latitude = "134.8719"
                ),
                city = "Billings",
                state = "Michigan",
                country = "United States",
                postcode = "63104"
            ),
            dob = ApiDob(
                date = "1992-03-08T15:13:16.688Z",
                age = 30
            ),
            login = ApiLogin(
                username = "yellowpeacock117",
            ),
            picture = ApiPicture(
                large = "https://randomuser.me/api/portraits/men/75.jpg",
                medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
            ),
            name = ApiUserName(
                title = "Miss",
                first = "Jennie",
                last = "Nichols"
            ),
            email = "jennie.nichols@example.com",
            nat = "US",
            phone = "(272) 790-0888",
            cell = null,
            gender = "female"
        )

        // when
        val actual = User.fromApiUser(apiUser)

        // then
        val expected = Result.failure<User>(
            formatUserParsingException(apiUser, "cell", apiUser.cell)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiUser_ensure failure when cell is blank`() {
        // given
        val apiUser = ApiUser(
            location = ApiLocation(
                street = ApiStreet(
                    number = 4890,
                    name = "Valwood Pkwy"
                ),
                coordinates = ApiCoordinates(
                    longitude = "-69.8246",
                    latitude = "134.8719"
                ),
                city = "Billings",
                state = "Michigan",
                country = "United States",
                postcode = "63104"
            ),
            dob = ApiDob(
                date = "1992-03-08T15:13:16.688Z",
                age = 30
            ),
            login = ApiLogin(
                username = "yellowpeacock117",
            ),
            picture = ApiPicture(
                large = "https://randomuser.me/api/portraits/men/75.jpg",
                medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
            ),
            name = ApiUserName(
                title = "Miss",
                first = "Jennie",
                last = "Nichols"
            ),
            email = "jennie.nichols@example.com",
            nat = "US",
            phone = "(272) 790-0888",
            cell = "",
            gender = "female"
        )

        // when
        val actual = User.fromApiUser(apiUser)

        // then
        val expected = Result.failure<User>(
            formatUserParsingException(apiUser, "cell", apiUser.cell)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiUser_ensure failure when gender is null`() {
        // given
        val apiUser = ApiUser(
            location = ApiLocation(
                street = ApiStreet(
                    number = 4890,
                    name = "Valwood Pkwy"
                ),
                coordinates = ApiCoordinates(
                    longitude = "-69.8246",
                    latitude = "134.8719"
                ),
                city = "Billings",
                state = "Michigan",
                country = "United States",
                postcode = "63104"
            ),
            dob = ApiDob(
                date = "1992-03-08T15:13:16.688Z",
                age = 30
            ),
            login = ApiLogin(
                username = "yellowpeacock117",
            ),
            picture = ApiPicture(
                large = "https://randomuser.me/api/portraits/men/75.jpg",
                medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
            ),
            name = ApiUserName(
                title = "Miss",
                first = "Jennie",
                last = "Nichols"
            ),
            email = "jennie.nichols@example.com",
            nat = "US",
            phone = "(272) 790-0888",
            cell = "(272) 790-0999",
            gender = null
        )

        // when
        val actual = User.fromApiUser(apiUser)

        // then
        val expected = Result.failure<User>(
            formatUserParsingException(apiUser, "gender", apiUser.gender)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiUser_ensure failure when gender is blank`() {
        // given
        val apiUser = ApiUser(
            location = ApiLocation(
                street = ApiStreet(
                    number = 4890,
                    name = "Valwood Pkwy"
                ),
                coordinates = ApiCoordinates(
                    longitude = "-69.8246",
                    latitude = "134.8719"
                ),
                city = "Billings",
                state = "Michigan",
                country = "United States",
                postcode = "63104"
            ),
            dob = ApiDob(
                date = "1992-03-08T15:13:16.688Z",
                age = 30
            ),
            login = ApiLogin(
                username = "yellowpeacock117",
            ),
            picture = ApiPicture(
                large = "https://randomuser.me/api/portraits/men/75.jpg",
                medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
            ),
            name = ApiUserName(
                title = "Miss",
                first = "Jennie",
                last = "Nichols"
            ),
            email = "jennie.nichols@example.com",
            nat = "US",
            phone = "(272) 790-0888",
            cell = "(272) 790-0999",
            gender = ""
        )

        // when
        val actual = User.fromApiUser(apiUser)

        // then
        val expected = Result.failure<User>(
            formatUserParsingException(apiUser, "gender", apiUser.gender)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiUser_ensure success when fields are filled`() {
        // given
        val apiUser = ApiUser(
            location = ApiLocation(
                street = ApiStreet(
                    number = 4890,
                    name = "Valwood Pkwy"
                ),
                coordinates = ApiCoordinates(
                    longitude = "-69.8246",
                    latitude = "134.8719"
                ),
                city = "Billings",
                state = "Michigan",
                country = "United States",
                postcode = "63104"
            ),
            dob = ApiDob(
                date = "1992-03-08T15:13:16.688Z",
                age = 30
            ),
            login = ApiLogin(
                username = "yellowpeacock117",
            ),
            picture = ApiPicture(
                large = "https://randomuser.me/api/portraits/men/75.jpg",
                medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
            ),
            name = ApiUserName(
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

        // when
        val actual = User.fromApiUser(apiUser)

        // then
        val expected = Result.success(
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

        Assert.assertEquals(expected, actual)
    }
}