package com.nmel.user.models.parser

import com.nmel.core_network.models.ParsingException
import com.nmel.user.models.api.ApiPicture
import com.nmel.user.models.api.ApiUser
import com.nmel.user.models.local.Picture
import com.nmel.user.models.local.User

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */

/**
 * Create a user parsing exception
 *
 * @param apiPicture ApiPicture being parsed
 * @param fieldName Field name on which parsing failed
 * @param fieldValue Field value on which parsing failed
 * @param reason Optional reason
 * @return Parsing exception
 */
fun formatPictureParsingException(
    apiPicture: ApiPicture,
    fieldName: String,
    fieldValue: String?,
    reason: String? = ""
) = ParsingException(
    "Picture",
    fieldName,
    fieldValue,
    reason,
    "medium : ${apiPicture.medium}"
)

/**
 * Return it as a result failure
 *
 * @param apiPicture ApiPicture being parsed
 * @param fieldName Field name on which parsing failed
 * @param fieldValue Field value on which parsing failed
 * @param reason Optional reason
 * @return Result failure
 */
fun reportError(
    apiPicture: ApiPicture,
    fieldName: String,
    fieldValue: String?,
    reason: String? = ""
): Result<Picture> {
    val exceptionToReport = formatPictureParsingException(
        apiPicture,
        fieldName,
        fieldValue,
        reason
    )

    return Result.failure(exceptionToReport)
}

fun Picture.Companion.fromApiPicture(apiPicture: ApiPicture): Result<Picture> {
    if (apiPicture.thumbnail.isNullOrBlank()) {
        return reportError(apiPicture, "thumbnail", apiPicture.thumbnail)
    }
    if (apiPicture.medium.isNullOrBlank()) {
        return reportError(apiPicture, "medium", apiPicture.medium)
    }
    if (apiPicture.large.isNullOrBlank()) {
        return reportError(apiPicture, "large", apiPicture.large)
    }

    val picture = Picture(
        thumbnail = apiPicture.thumbnail,
        medium = apiPicture.medium,
        large = apiPicture.large,
    )

    return Result.success(picture)
}