package com.nmel.user.models.parser

import com.nmel.core_network.models.ParsingException
import com.nmel.user.models.api.ApiDob
import com.nmel.user.models.local.Dob

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */

/**
 * Create a dob parsing exception
 *
 * @param apiDob ApiDob being parsed
 * @param fieldName Field name on which parsing failed
 * @param fieldValue Field value on which parsing failed
 * @param reason Optional reason
 * @return Parsing exception
 */
fun formatDobParsingException(
    apiDob: ApiDob,
    fieldName: String,
    fieldValue: String?,
    reason: String? = ""
) = ParsingException(
    "Dob",
    fieldName,
    fieldValue,
    reason,
    "date : ${apiDob.date}"
)

/**
 * Return it as a result failure
 *
 * @param apiDob ApiDob being parsed
 * @param fieldName Field name on which parsing failed
 * @param fieldValue Field value on which parsing failed
 * @param reason Optional reason
 * @return Result failure
 */
fun reportError(
    apiDob: ApiDob,
    fieldName: String,
    fieldValue: String?,
    reason: String? = ""
): Result<Dob> {
    val exceptionToReport = formatDobParsingException(
        apiDob,
        fieldName,
        fieldValue,
        reason
    )

    return Result.failure(exceptionToReport)
}

fun Dob.Companion.fromApiDob(apiDob: ApiDob): Result<Dob> {

    if (apiDob.age == null) {
        return reportError(apiDob, "age", null)
    }

    if (apiDob.date.isNullOrBlank()) {
        return reportError(apiDob, "date", apiDob.date)
    }

    val dob = Dob(
        age = apiDob.age,
        date = apiDob.date
    )
    return Result.success(dob)
}