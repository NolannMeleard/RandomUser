package com.nmel.user.models.parser

import com.nmel.core_network.models.ParsingException
import com.nmel.user.models.api.ApiUser
import com.nmel.user.models.local.Dob
import com.nmel.user.models.local.Location
import com.nmel.user.models.local.Login
import com.nmel.user.models.local.Picture
import com.nmel.user.models.local.User
import com.nmel.user.models.local.UserName

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */


/**
 * Create a user parsing exception
 *
 * @param apiUser ApiUser being parsed
 * @param fieldName Field name on which parsing failed
 * @param fieldValue Field value on which parsing failed
 * @param reason Optional reason
 * @return Parsing exception
 */
fun formatUserParsingException(
    apiUser: ApiUser,
    fieldName: String,
    fieldValue: String?,
    reason: String? = ""
) = ParsingException(
    "User",
    fieldName,
    fieldValue,
    reason,
    "userEmail : ${apiUser.email}"
)

/**
 * Return it as a result failure
 *
 * @param apiUser ApiUser being parsed
 * @param fieldName Field name on which parsing failed
 * @param fieldValue Field value on which parsing failed
 * @param reason Optional reason
 * @return Result failure
 */
fun reportError(
    apiUser: ApiUser,
    fieldName: String,
    fieldValue: String?,
    reason: String? = ""
): Result<User> {
    val exceptionToReport = formatUserParsingException(
        apiUser,
        fieldName,
        fieldValue,
        reason
    )

    return Result.failure(exceptionToReport)
}

fun User.Companion.fromApiUser(apiUser: ApiUser): Result<User> {

    val userNameParsed: UserName = apiUser.name?.let {
        UserName.fromApiUserName(it).getOrNull()
    } ?: return reportError(apiUser, "name", apiUser.name.toString())

    val locationParsed: Location = apiUser.location?.let {
        Location.fromApiLocation(it).getOrNull()
    } ?: return reportError(apiUser, "location", apiUser.location.toString())

    if (apiUser.gender.isNullOrBlank()) {
        return reportError(apiUser, "gender", apiUser.gender)
    }

    val pictureParsed: Picture = apiUser.picture?.let {
        Picture.fromApiPicture(it).getOrNull()
    } ?: return reportError(apiUser, "picture", apiUser.picture.toString())

    val loginParsed: Login = apiUser.login?.let {
        Login.fromApiLogin(it).getOrNull()
    } ?: return reportError(apiUser, "login", apiUser.login.toString())


    val dobParser: Dob = apiUser.dob?.let {
        Dob.fromApiDob(it).getOrNull()
    } ?: return reportError(apiUser, "dob", apiUser.dob.toString())

    if (apiUser.email.isNullOrBlank()) {
        return reportError(apiUser, "email", apiUser.email)
    }

    if (apiUser.phone.isNullOrBlank()) {
        return reportError(apiUser, "phone", apiUser.phone)
    }

    if (apiUser.cell.isNullOrBlank()) {
        return reportError(apiUser, "cell", apiUser.cell)
    }

    if (apiUser.nat.isNullOrBlank()) {
        return reportError(apiUser, "nat", apiUser.nat)
    }


    val user = User(
        name = userNameParsed,
        location = locationParsed,
        gender = apiUser.gender,
        picture = pictureParsed,
        login = loginParsed,
        dob = dobParser,
        email = apiUser.email,
        phone = apiUser.phone,
        cell = apiUser.cell,
        nat = apiUser.nat,
    )

    return Result.success(user)
}