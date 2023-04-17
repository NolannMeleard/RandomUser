package com.nmel.randomuser.models.parser

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nmel.randomuser.RandomUserTestApplication
import com.nmel.user.models.api.ApiUserName
import com.nmel.user.models.local.UserName
import com.nmel.user.models.parser.formatUserNameParsingException
import com.nmel.user.models.parser.fromApiUserName
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
class UserNameParserKtTest {
    @Test
    fun `fromApiUserName_ensure failure when title is null`() {
        // given
        val apiUserName = ApiUserName(
            title = null,
            first = "Jennie",
            last = "Nichols"
        )

        // when
        val actual = UserName.fromApiUserName(apiUserName)

        // then
        val expected = Result.failure<UserName>(
            formatUserNameParsingException(apiUserName, "title", apiUserName.title)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiUserName_ensure failure when title is blank`() {
        // given
        val apiUserName = ApiUserName(
            title = "",
            first = "Jennie",
            last = "Nichols"
        )

        // when
        val actual = UserName.fromApiUserName(apiUserName)

        // then
        val expected = Result.failure<UserName>(
            formatUserNameParsingException(apiUserName, "title", apiUserName.title)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiUserName_ensure failure when first is null`() {
        // given
        val apiUserName = ApiUserName(
            title = "Miss",
            first = null,
            last = "Nichols"
        )

        // when
        val actual = UserName.fromApiUserName(apiUserName)

        // then
        val expected = Result.failure<UserName>(
            formatUserNameParsingException(apiUserName, "first", apiUserName.first)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiUserName_ensure failure when first is blank`() {
        // given
        val apiUserName = ApiUserName(
            title = "Miss",
            first = "",
            last = "Nichols"
        )

        // when
        val actual = UserName.fromApiUserName(apiUserName)

        // then
        val expected = Result.failure<UserName>(
            formatUserNameParsingException(apiUserName, "first", apiUserName.first)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiUserName_ensure failure when last is null`() {
        // given
        val apiUserName = ApiUserName(
            title = "Miss",
            first = "Jennie",
            last = null
        )

        // when
        val actual = UserName.fromApiUserName(apiUserName)

        // then
        val expected = Result.failure<UserName>(
            formatUserNameParsingException(apiUserName, "last", apiUserName.last)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiUserName_ensure failure when last is blank`() {
        // given
        val apiUserName = ApiUserName(
            title = "Miss",
            first = "Jennie",
            last = ""
        )

        // when
        val actual = UserName.fromApiUserName(apiUserName)

        // then
        val expected = Result.failure<UserName>(
            formatUserNameParsingException(apiUserName, "last", apiUserName.last)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiUserName_ensure success when fields are filled`() {
        // given
        val apiUserName = ApiUserName(
            title = "Miss",
            first = "Jennie",
            last = "Nichols"
        )

        // when
        val actual = UserName.fromApiUserName(apiUserName)

        // then
        val expected = Result.success(
            UserName(
                title = "Miss",
                first = "Jennie",
                last = "Nichols"
            )
        )

        Assert.assertEquals(expected, actual)
    }
}