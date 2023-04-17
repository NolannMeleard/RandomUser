package com.nmel.randomuser.models.parser

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nmel.randomuser.RandomUserTestApplication
import com.nmel.user.models.api.ApiLogin
import com.nmel.user.models.local.Login
import com.nmel.user.models.parser.formatLoginParsingException
import com.nmel.user.models.parser.fromApiLogin
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
class LoginParserKtTest {
    @Test
    fun `fromApiLogin_ensure error when username is blank`() {
        // given
        val apiLogin = ApiLogin(
            username = "",
        )

        // when
        val actual = Login.fromApiLogin(apiLogin)

        // then
        val expected = Result.failure<Login>(
            formatLoginParsingException(apiLogin, "username", apiLogin.username)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiLogin_ensure error when username is null`() {
        // given
        val apiLogin = ApiLogin(
            username = null,
        )

        // when
        val actual = Login.fromApiLogin(apiLogin)

        // then
        val expected = Result.failure<Login>(
            formatLoginParsingException(apiLogin, "username", apiLogin.username)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiLogin_ensure success when fields are filled`() {
        // given
        val apiLogin = ApiLogin(
            username = "yellowpeacock117",
        )

        // when
        val actual = Login.fromApiLogin(apiLogin)

        // then
        val expected = Result.success(
            Login(
                username = "yellowpeacock117",
            )
        )

        Assert.assertEquals(expected, actual)
    }
}