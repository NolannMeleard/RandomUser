package com.nmel.user.models.parser

import com.nmel.core_network.models.ParsingException
import com.nmel.user.models.api.ApiCoordinates
import com.nmel.user.models.local.Coordinates

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
/**
 * Create a coordiantes parsing exception
 *
 * @param apiCoordinates ApiStreet being parsed
 * @param fieldName Field name on which parsing failed
 * @param fieldValue Field value on which parsing failed
 * @param reason Optional reason
 * @return Parsing exception
 */
fun formatCoordinatesParsingException(
    apiCoordinates: ApiCoordinates,
    fieldName: String,
    fieldValue: String?,
    reason: String? = ""
) = ParsingException(
    "Street",
    fieldName,
    fieldValue,
    reason,
    "name : ${apiCoordinates.longitude}"
)

/**
 * Return it as a result failure
 *
 * @param apiCoordinates ApiCoordinates being parsed
 * @param fieldName Field name on which parsing failed
 * @param fieldValue Field value on which parsing failed
 * @param reason Optional reason
 * @return Result failure
 */
fun reportError(
    apiCoordinates: ApiCoordinates,
    fieldName: String,
    fieldValue: String?,
    reason: String? = ""
): Result<Coordinates> {
    val exceptionToReport = formatCoordinatesParsingException(
        apiCoordinates,
        fieldName,
        fieldValue,
        reason
    )

    return Result.failure(exceptionToReport)
}

fun Coordinates.Companion.fromApiCoordinates(apiCoordinates: ApiCoordinates): Result<Coordinates> {
    if (apiCoordinates.longitude.isNullOrBlank()) {
        return reportError(apiCoordinates, "longitude", apiCoordinates.longitude)
    }

    if (apiCoordinates.latitude.isNullOrBlank()) {
        return reportError(apiCoordinates, "latitude", apiCoordinates.latitude)
    }

    val coordinates = Coordinates(
        longitude = apiCoordinates.longitude,
        latitude = apiCoordinates.latitude
    )

    return Result.success(coordinates)
}

