package com.nmel.randomuser.models.parser

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nmel.randomuser.RandomUserTestApplication
import com.nmel.user.models.api.ApiDob
import com.nmel.user.models.local.Dob
import com.nmel.user.models.parser.formatDobParsingException
import com.nmel.user.models.parser.fromApiDob
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
class DobParserKtTest {
    @Test
    fun `fromApiDob_ensure failure when date is null`() {
        // given
        val apiDob = ApiDob(
            date = null,
            age = 30
        )

        // when
        val actual = Dob.fromApiDob(apiDob)

        // then
        val expected = Result.failure<Dob>(
            formatDobParsingException(apiDob, "date", apiDob.date)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiDob_ensure failure when date is blank`() {
        // given
        val apiDob = ApiDob(
            date = "",
            age = 30
        )

        // when
        val actual = Dob.fromApiDob(apiDob)

        // then
        val expected = Result.failure<Dob>(
            formatDobParsingException(apiDob, "date", apiDob.date)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiDob_ensure failure when age is null`() {
        // given
        val apiDob = ApiDob(
            date = "1992-03-08T15:13:16.688Z",
            age = null
        )

        // when
        val actual = Dob.fromApiDob(apiDob)

        // then
        val expected = Result.failure<Dob>(
            formatDobParsingException(apiDob, "age", null)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiDob_ensure success when fields are filled`() {
        // given
        val apiDob = ApiDob(
            date = "1992-03-08T15:13:16.688Z",
            age = 30
        )

        // when
        val actual = Dob.fromApiDob(apiDob)

        // then
        val expected = Result.success(
            Dob(
                date = "1992-03-08T15:13:16.688Z",
                age = 30
            )
        )

        Assert.assertEquals(expected, actual)
    }
}