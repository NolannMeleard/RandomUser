package com.nmel.randomuser.models.parser

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nmel.randomuser.RandomUserTestApplication
import com.nmel.user.models.api.ApiCoordinates
import com.nmel.user.models.api.ApiLocation
import com.nmel.user.models.api.ApiStreet
import com.nmel.user.models.local.Coordinates
import com.nmel.user.models.local.Location
import com.nmel.user.models.local.Street
import com.nmel.user.models.parser.formatLocationParsingException
import com.nmel.user.models.parser.fromApiLocation
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
class LocationParserKtTest {
    @Test
    fun `fromApiLocation_ensure failure when street field is null`() {
        // given
        val apiLocation = ApiLocation(
            street = null,
            coordinates = ApiCoordinates(
                longitude = "-69.8246",
                latitude = "134.8719"
            ),
            city = "Billings",
            state = "Michigan",
            country = "United States",
            postcode = "63104"
        )

        // when
        val actual = Location.fromApiLocation(apiLocation)

        // then
        val expected = Result.failure<Location>(
            formatLocationParsingException(apiLocation, "street", apiLocation.street.toString())
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiLocation_ensure failure when street field contains blank value`() {
        // given
        val apiLocation = ApiLocation(
            street = ApiStreet(
                number = 4890,
                name = ""
            ),
            coordinates = ApiCoordinates(
                longitude = "-69.8246",
                latitude = "134.8719"
            ),
            city = "Billings",
            state = "Michigan",
            country = "United States",
            postcode = "63104"
        )

        // when
        val actual = Location.fromApiLocation(apiLocation)

        // then
        val expected = Result.failure<Location>(
            formatLocationParsingException(apiLocation, "street", apiLocation.street.toString())
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiLocation_ensure failure when coordinates field is null`() {
        // given
        val apiLocation = ApiLocation(
            street = ApiStreet(
                number = 4890,
                name = "Valwood Pkwy"
            ),
            coordinates = null,
            city = "Billings",
            state = "Michigan",
            country = "United States",
            postcode = "63104"
        )

        // when
        val actual = Location.fromApiLocation(apiLocation)

        // then
        val expected = Result.failure<Location>(
            formatLocationParsingException(apiLocation, "coordinates", apiLocation.coordinates.toString())
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiLocation_ensure failure when coordinates field has blank values`() {
        // given
        val apiLocation = ApiLocation(
            street = ApiStreet(
                number = 4890,
                name = "Valwood Pkwy"
            ),
            coordinates = ApiCoordinates(
                longitude = "-69.8246",
                latitude = ""
            ),
            city = "Billings",
            state = "Michigan",
            country = "United States",
            postcode = "63104"
        )

        // when
        val actual = Location.fromApiLocation(apiLocation)

        // then
        val expected = Result.failure<Location>(
            formatLocationParsingException(apiLocation, "coordinates", apiLocation.coordinates.toString())
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiLocation_ensure failure when city field is null`() {
        // given
        val apiLocation = ApiLocation(
            street = ApiStreet(
                number = 4890,
                name = "Valwood Pkwy"
            ),
            coordinates = ApiCoordinates(
                longitude = "-69.8246",
                latitude = "134.8719"
            ),
            city = null,
            state = "Michigan",
            country = "United States",
            postcode = "63104"
        )

        // when
        val actual = Location.fromApiLocation(apiLocation)

        // then
        val expected = Result.failure<Location>(
            formatLocationParsingException(apiLocation, "city", apiLocation.city)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiLocation_ensure failure when city field is blank`() {
        // given
        val apiLocation = ApiLocation(
            street = ApiStreet(
                number = 4890,
                name = "Valwood Pkwy"
            ),
            coordinates = ApiCoordinates(
                longitude = "-69.8246",
                latitude = "134.8719"
            ),
            city = "",
            state = "Michigan",
            country = "United States",
            postcode = "63104"
        )

        // when
        val actual = Location.fromApiLocation(apiLocation)

        // then
        val expected = Result.failure<Location>(
            formatLocationParsingException(apiLocation, "city", apiLocation.city)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiLocation_ensure failure when state field is null`() {
        // given
        val apiLocation = ApiLocation(
            street = ApiStreet(
                number = 4890,
                name = "Valwood Pkwy"
            ),
            coordinates = ApiCoordinates(
                longitude = "-69.8246",
                latitude = "134.8719"
            ),
            city = "Billings",
            state = null,
            country = "United States",
            postcode = "63104"
        )

        // when
        val actual = Location.fromApiLocation(apiLocation)

        // then
        val expected = Result.failure<Location>(
            formatLocationParsingException(apiLocation, "state", apiLocation.state)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiLocation_ensure failure when state field is blank`() {
        // given
        val apiLocation = ApiLocation(
            street = ApiStreet(
                number = 4890,
                name = "Valwood Pkwy"
            ),
            coordinates = ApiCoordinates(
                longitude = "-69.8246",
                latitude = "134.8719"
            ),
            city = "Billings",
            state = "",
            country = "United States",
            postcode = "63104"
        )

        // when
        val actual = Location.fromApiLocation(apiLocation)

        // then
        val expected = Result.failure<Location>(
            formatLocationParsingException(apiLocation, "state", apiLocation.state)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiLocation_ensure failure when country field is null`() {
        // given
        val apiLocation = ApiLocation(
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
            country = null,
            postcode = "63104"
        )

        // when
        val actual = Location.fromApiLocation(apiLocation)

        // then
        val expected = Result.failure<Location>(
            formatLocationParsingException(apiLocation, "country", apiLocation.country)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiLocation_ensure failure when country field is blank`() {
        // given
        val apiLocation = ApiLocation(
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
            country = "",
            postcode = "63104"
        )

        // when
        val actual = Location.fromApiLocation(apiLocation)

        // then
        val expected = Result.failure<Location>(
            formatLocationParsingException(apiLocation, "country", apiLocation.country)
        )

        Assert.assertEquals(expected, actual)
    }


    @Test
    fun `fromApiLocation_ensure failure when postcode field is null`() {
        // given
        val apiLocation = ApiLocation(
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
            postcode = null
        )

        // when
        val actual = Location.fromApiLocation(apiLocation)

        // then
        val expected = Result.failure<Location>(
            formatLocationParsingException(apiLocation, "postcode", apiLocation.postcode)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiLocation_ensure failure when postcode field is blank`() {
        // given
        val apiLocation = ApiLocation(
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
            postcode = ""
        )

        // when
        val actual = Location.fromApiLocation(apiLocation)

        // then
        val expected = Result.failure<Location>(
            formatLocationParsingException(apiLocation, "postcode", apiLocation.postcode)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiLocation_ensure success when all fields are filled`() {
        // given
        val apiLocation = ApiLocation(
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
        )

        // when
        val actual = Location.fromApiLocation(apiLocation)

        // then
        val expected = Result.success(
            Location(
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
            )
        )

        Assert.assertEquals(expected, actual)
    }
}