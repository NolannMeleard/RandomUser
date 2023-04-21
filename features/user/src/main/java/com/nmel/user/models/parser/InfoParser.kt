package com.nmel.user.models.parser

import com.nmel.core_network.models.ParsingException
import com.nmel.user.models.api.ApiInfo
import com.nmel.user.models.local.Info

/**
 * Created by Nolann Méléard on 18 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
/**
 * Create a dob parsing exception
 *
 * @param apiInfo ApiInfo being parsed
 * @param fieldName Field name on which parsing failed
 * @param fieldValue Field value on which parsing failed
 * @param reason Optional reason
 * @return Parsing exception
 */
fun formatInfoParsingException(
    apiInfo: ApiInfo,
    fieldName: String,
    fieldValue: String?,
    reason: String? = ""
) = ParsingException(
    "Info",
    fieldName,
    fieldValue,
    reason,
    "page : ${apiInfo.page}"
)

/**
 * Return it as a result failure
 *
 * @param apiInfo ApiInfo being parsed
 * @param fieldName Field name on which parsing failed
 * @param fieldValue Field value on which parsing failed
 * @param reason Optional reason
 * @return Result failure
 */
fun reportError(
    apiInfo: ApiInfo,
    fieldName: String,
    fieldValue: String?,
    reason: String? = ""
): Result<Info> {
    val exceptionToReport = formatInfoParsingException(
        apiInfo,
        fieldName,
        fieldValue,
        reason
    )

    return Result.failure(exceptionToReport)
}

fun Info.Companion.fromApiInfo(apiInfo: ApiInfo): Result<Info> {

    if (apiInfo.seed.isNullOrBlank()) {
        return reportError(apiInfo, "seed", apiInfo.seed)
    }

    if (apiInfo.page == null) {
        return reportError(apiInfo, "page", null)
    }

    val info = Info(
        seed = apiInfo.seed,
        page = apiInfo.page
    )
    return Result.success(info)
}