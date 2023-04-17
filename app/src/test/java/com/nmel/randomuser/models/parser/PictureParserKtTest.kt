package com.nmel.randomuser.models.parser

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nmel.randomuser.RandomUserTestApplication
import com.nmel.user.models.api.ApiPicture
import com.nmel.user.models.local.Picture
import com.nmel.user.models.parser.formatPictureParsingException
import com.nmel.user.models.parser.fromApiPicture
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
class PictureParserKtTest {
    @Test
    fun `fromApiPicture_ensure failure when field large is null`() {
        // given
        val apiPicture = ApiPicture(
            large = null,
            medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
            thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
        )

        // when
        val actual =
            Picture.fromApiPicture(apiPicture)

        // then
        val expected = Result.failure<Picture>(
            formatPictureParsingException(apiPicture, "large", apiPicture.large)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiPicture_ensure failure when field large is blank`() {
        // given
        val apiPicture = ApiPicture(
            large = "",
            medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
            thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
        )

        // when
        val actual =
            Picture.fromApiPicture(apiPicture)

        // then
        val expected = Result.failure<Picture>(
            formatPictureParsingException(apiPicture, "large", apiPicture.large)
        )

        Assert.assertEquals(expected, actual)
    }


    @Test
    fun `fromApiPicture_ensure failure when field medium is null`() {
        // given
        val apiPicture = ApiPicture(
            large = "https://randomuser.me/api/portraits/men/75.jpg",
            medium = null,
            thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
        )

        // when
        val actual =
            Picture.fromApiPicture(apiPicture)

        // then
        val expected = Result.failure<Picture>(
            formatPictureParsingException(apiPicture, "medium", apiPicture.medium)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiPicture_ensure failure when field medium is blank`() {
        // given
        val apiPicture = ApiPicture(
            large = "https://randomuser.me/api/portraits/men/75.jpg",
            medium = "",
            thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
        )

        // when
        val actual =
            Picture.fromApiPicture(apiPicture)

        // then
        val expected = Result.failure<Picture>(
            formatPictureParsingException(apiPicture, "medium", apiPicture.medium)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiPicture_ensure failure when field thumbnail is null`() {
        // given
        val apiPicture = ApiPicture(
            large = "https://randomuser.me/api/portraits/men/75.jpg",
            medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
            thumbnail = null
        )

        // when
        val actual =
            Picture.fromApiPicture(apiPicture)

        // then
        val expected = Result.failure<Picture>(
            formatPictureParsingException(apiPicture, "thumbnail", apiPicture.thumbnail)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiPicture_ensure failure when field thumbnail is blank`() {
        // given
        val apiPicture = ApiPicture(
            large = "https://randomuser.me/api/portraits/men/75.jpg",
            medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
            thumbnail = ""
        )

        // when
        val actual =
            Picture.fromApiPicture(apiPicture)

        // then
        val expected = Result.failure<Picture>(
            formatPictureParsingException(apiPicture, "thumbnail", apiPicture.thumbnail)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `fromApiPicture_ensure success when fields are filled`() {
        // given
        val apiPicture = ApiPicture(
            large = "https://randomuser.me/api/portraits/men/75.jpg",
            medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
            thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
        )

        // when
        val actual =
            Picture.fromApiPicture(apiPicture)

        // then
        val expected = Result.success(
            Picture(
                large = "https://randomuser.me/api/portraits/men/75.jpg",
                medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
            )
        )

        Assert.assertEquals(expected, actual)
    }
}