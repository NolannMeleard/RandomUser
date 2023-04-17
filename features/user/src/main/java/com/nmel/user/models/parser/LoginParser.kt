package com.nmel.user.models.parser

import com.nmel.core_network.models.ParsingException
import com.nmel.user.models.api.ApiLogin
import com.nmel.user.models.local.Login

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */

/**
 * Create a user parsing exception
 *
 * @param apiLogin ApiLogin being parsed
 * @param fieldName Field name on which parsing failed
 * @param fieldValue Field value on which parsing failed
 * @param reason Optional reason
 * @return Parsing exception
 */
fun formatLoginParsingException(
    apiLogin: ApiLogin,
    fieldName: String,
    fieldValue: String?,
    reason: String? = ""
) = ParsingException(
    "Login",
    fieldName,
    fieldValue,
    reason,
    "username : ${apiLogin.username}"
)

/**
 * Return it as a result failure
 *
 * @param apiLogin ApiLogin being parsed
 * @param fieldName Field name on which parsing failed
 * @param fieldValue Field value on which parsing failed
 * @param reason Optional reason
 * @return Result failure
 */
fun reportError(
    apiLogin: ApiLogin,
    fieldName: String,
    fieldValue: String?,
    reason: String? = ""
): Result<Login> {
    val exceptionToReport = formatLoginParsingException(
        apiLogin,
        fieldName,
        fieldValue,
        reason
    )

    return Result.failure(exceptionToReport)
}

fun Login.Companion.fromApiLogin(apiLogin: ApiLogin): Result<Login> {
    if (apiLogin.username.isNullOrBlank()) {
        return reportError(apiLogin, "username", apiLogin.username)
    }

    val login = Login(
        username = apiLogin.username,
    )

    return Result.success(login)
}