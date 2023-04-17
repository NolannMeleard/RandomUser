package com.nmel.randomuser.models.parser

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nmel.randomuser.RandomUserTestApplication
import com.nmel.user.models.api.ApiCoordinates
import com.nmel.user.models.local.Coordinates
import com.nmel.user.models.parser.formatCoordinatesParsingException
import com.nmel.user.models.parser.fromApiCoordinates
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
class CoordinatesParserKtTest {
    @Test
    fun `fromApiCoordinates_ensure failure when longitude is null`() {
        // given
        val apiCoordinates = ApiCoordinates(
            longitude = null,
            latitude = "134.8719"
        )

        // when
        val actual = Coordinates.fromApiCoordinates(apiCoordinates)

        // then
        val expected = Result.failure<Coordinates>(
            formatCoordinatesParsingException(apiCoordinates, "longitude", apiCoordinates.longitude)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiCoordinates_ensure failure when longitude is blank`() {
        // given
        val apiCoordinates = ApiCoordinates(
            longitude = "",
            latitude = "134.8719"
        )

        // when
        val actual = Coordinates.fromApiCoordinates(apiCoordinates)

        // then
        val expected = Result.failure<Coordinates>(
            formatCoordinatesParsingException(apiCoordinates, "longitude", apiCoordinates.longitude)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiCoordinates_ensure failure when latitude is null`() {
        // given
        val apiCoordinates = ApiCoordinates(
            longitude = "-69.8246",
            latitude = null
        )

        // when
        val actual = Coordinates.fromApiCoordinates(apiCoordinates)

        // then
        val expected = Result.failure<Coordinates>(
            formatCoordinatesParsingException(apiCoordinates, "latitude", apiCoordinates.latitude)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiCoordinates_ensure failure when latitude is blank`() {
        // given
        val apiCoordinates = ApiCoordinates(
            longitude = "-69.8246",
            latitude = ""
        )

        // when
        val actual = Coordinates.fromApiCoordinates(apiCoordinates)

        // then
        val expected = Result.failure<Coordinates>(
            formatCoordinatesParsingException(apiCoordinates, "latitude", apiCoordinates.latitude)
        )

        Assert.assertEquals(expected, actual)
    }


    @Test
    fun `fromApiCoordinates_ensure success when fields are filled`() {
        // given
        val apiCoordinates = ApiCoordinates(
            longitude = "-69.8246",
            latitude = "134.8719"
        )

        // when
        val actual = Coordinates.fromApiCoordinates(apiCoordinates)

        // then
        val expected = Result.success(
            Coordinates(
                longitude = "-69.8246",
                latitude = "134.8719"
            )
        )

        Assert.assertEquals(expected, actual)
    }
}