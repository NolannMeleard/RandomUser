package com.nmel.user.models.parser

import com.nmel.core_network.models.ParsingException
import com.nmel.user.models.api.ApiStreet
import com.nmel.user.models.local.Street

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */


/**
 * Create a street parsing exception
 *
 * @param apiStreet ApiStreet being parsed
 * @param fieldName Field name on which parsing failed
 * @param fieldValue Field value on which parsing failed
 * @param reason Optional reason
 * @return Parsing exception
 */
fun formatStreetParsingException(
    apiStreet: ApiStreet,
    fieldName: String,
    fieldValue: String?,
    reason: String? = ""
) = ParsingException(
    "Street",
    fieldName,
    fieldValue,
    reason,
    "name : ${apiStreet.name}"
)

/**
 * Return it as a result failure
 *
 * @param apiStreet ApiStreet being parsed
 * @param fieldName Field name on which parsing failed
 * @param fieldValue Field value on which parsing failed
 * @param reason Optional reason
 * @return Result failure
 */
fun reportError(
    apiStreet: ApiStreet,
    fieldName: String,
    fieldValue: String?,
    reason: String? = ""
): Result<Street> {
    val exceptionToReport = formatStreetParsingException(
        apiStreet,
        fieldName,
        fieldValue,
        reason
    )

    return Result.failure(exceptionToReport)
}

fun Street.Companion.fromApiCoordinates(apiStreet: ApiStreet): Result<Street> {
    if (apiStreet.name.isNullOrBlank()) {
        return reportError(apiStreet, "name", apiStreet.name)
    }

    if (apiStreet.number == null) {
        return reportError(apiStreet, "number", null)
    }

    val street = Street(
        name = apiStreet.name,
        number = apiStreet.number
    )

    return Result.success(street)
}

