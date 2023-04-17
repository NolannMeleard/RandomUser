package com.nmel.user.models.parser

import com.nmel.core_network.models.ParsingException
import com.nmel.user.models.api.ApiUserName
import com.nmel.user.models.local.UserName

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */

/**
 * Create a user parsing exception
 *
 * @param apiUserName ApiUserName being parsed
 * @param fieldName Field name on which parsing failed
 * @param fieldValue Field value on which parsing failed
 * @param reason Optional reason
 * @return Parsing exception
 */
fun formatUserNameParsingException(
    apiUserName: ApiUserName,
    fieldName: String,
    fieldValue: String?,
    reason: String? = ""
) = ParsingException(
    "UserName",
    fieldName,
    fieldValue,
    reason,
    "userEmail : ${apiUserName.title}"
)

/**
 * Return it as a result failure
 *
 * @param apiUserName ApiUserName being parsed
 * @param fieldName Field name on which parsing failed
 * @param fieldValue Field value on which parsing failed
 * @param reason Optional reason
 * @return Result failure
 */
fun reportError(
    apiUserName: ApiUserName,
    fieldName: String,
    fieldValue: String?,
    reason: String? = ""
): Result<UserName> {
    val exceptionToReport = formatUserNameParsingException(
        apiUserName,
        fieldName,
        fieldValue,
        reason
    )

    return Result.failure(exceptionToReport)
}

fun UserName.Companion.fromApiUserName(apiUserName: ApiUserName): Result<UserName> {
    if (apiUserName.title.isNullOrBlank()) {
        return reportError(apiUserName, "title", apiUserName.title)
    }

    if (apiUserName.first.isNullOrBlank()) {
        return reportError(apiUserName, "first", apiUserName.first)
    }

    if (apiUserName.last.isNullOrBlank()) {
        return reportError(apiUserName, "last", apiUserName.last)
    }

    val userName = UserName(
        title = apiUserName.title,
        first = apiUserName.first,
        last = apiUserName.last
    )

    return Result.success(userName)
}


