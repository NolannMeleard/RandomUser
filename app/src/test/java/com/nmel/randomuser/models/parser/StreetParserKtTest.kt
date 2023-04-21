package com.nmel.randomuser.models.parser

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nmel.randomuser.RandomUserTestApplication
import com.nmel.user.models.api.ApiStreet
import com.nmel.user.models.local.Street
import com.nmel.user.models.parser.formatStreetParsingException
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
class StreetParserKtTest {
    @Test
    fun `fromApiStreet_ensure failure when name is null`() {
        // given
        val apiStreet = ApiStreet(
            number = 4890,
            name = null
        )

        // when
        val actual =
            Street.fromApiCoordinates(apiStreet)

        // then
        val expected = Result.failure<Street>(
            formatStreetParsingException(apiStreet, "name", apiStreet.name)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiStreet_ensure failure when name is blank`() {
        // given
        val apiStreet = ApiStreet(
            number = 4890,
            name = ""
        )

        // when
        val actual =
            Street.fromApiCoordinates(apiStreet)

        // then
        val expected = Result.failure<Street>(
            formatStreetParsingException(apiStreet, "name", apiStreet.name)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiStreet_ensure failure when number is null`() {
        // given
        val apiStreet = ApiStreet(
            number = null,
            name = "Valwood Pkwy"
        )

        // when
        val actual =
            Street.fromApiCoordinates(apiStreet)

        // then
        val expected = Result.failure<Street>(
            formatStreetParsingException(apiStreet, "number", null)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiStreet_ensure success when fields are filled`() {
        // given
        val apiStreet = ApiStreet(
            number = 4890,
            name = "Valwood Pkwy"
        )

        // when
        val actual =
            Street.fromApiCoordinates(apiStreet)

        // then
        val expected = Result.success(
            Street(
                number = 4890,
                name = "Valwood Pkwy"
            )
        )

        Assert.assertEquals(expected, actual)
    }
}