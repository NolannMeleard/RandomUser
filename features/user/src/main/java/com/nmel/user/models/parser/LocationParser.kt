package com.nmel.user.models.parser

import com.nmel.core_network.models.ParsingException
import com.nmel.user.models.api.ApiLocation
import com.nmel.user.models.local.Coordinates
import com.nmel.user.models.local.Location
import com.nmel.user.models.local.Street

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */


/**
 * Create a location parsing exception
 *
 * @param apiLocation ApiLocation being parsed
 * @param fieldName Field name on which parsing failed
 * @param fieldValue Field value on which parsing failed
 * @param reason Optional reason
 * @return Parsing exception
 */
fun formatLocationParsingException(
    apiLocation: ApiLocation,
    fieldName: String,
    fieldValue: String?,
    reason: String? = ""
) = ParsingException(
    "Location",
    fieldName,
    fieldValue,
    reason,
    "city : ${apiLocation.city}"
)

/**
 * Return it as a result failure
 *
 * @param apiLocation ApiLocation being parsed
 * @param fieldName Field name on which parsing failed
 * @param fieldValue Field value on which parsing failed
 * @param reason Optional reason
 * @return Result failure
 */
fun reportError(
    apiLocation: ApiLocation,
    fieldName: String,
    fieldValue: String?,
    reason: String? = ""
): Result<Location> {
    val exceptionToReport = formatLocationParsingException(
        apiLocation,
        fieldName,
        fieldValue,
        reason
    )

    return Result.failure(exceptionToReport)
}

fun Location.Companion.fromApiLocation(apiLocation: ApiLocation): Result<Location> {
    val streetParsed: Street = apiLocation.street?.let {
        Street.fromApiCoordinates(it).getOrNull()
    } ?: return reportError(apiLocation, "street", apiLocation.street.toString())


    if (apiLocation.state.isNullOrBlank()) {
        return reportError(apiLocation, "state", apiLocation.state)
    }

    if (apiLocation.city.isNullOrBlank()) {
        return reportError(apiLocation, "city", apiLocation.city)
    }

    val coordinatesParsed: Coordinates = apiLocation.coordinates?.let {
        Coordinates.fromApiCoordinates(it).getOrNull()
    } ?: return reportError(apiLocation, "coordinates", apiLocation.coordinates.toString())

    if (apiLocation.country.isNullOrBlank()) {
        return reportError(apiLocation, "country", apiLocation.country)
    }

    if (apiLocation.postcode.isNullOrBlank()) {
        return reportError(apiLocation, "postcode", apiLocation.postcode)
    }

    val location = Location(
        street = streetParsed,
        state = apiLocation.state,
        city = apiLocation.city,
        coordinates = coordinatesParsed,
        country = apiLocation.country,
        postcode = apiLocation.postcode
    )

    return Result.success(location)
}