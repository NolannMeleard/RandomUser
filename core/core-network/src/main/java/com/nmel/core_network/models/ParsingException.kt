package com.nmel.core_network.models

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
data class ParsingException(
    val parserName: String,
    val parsingFieldName: String,
    val parsingFieldValue: String?,
    val reason: String?,
    val objectParsingDescription: String?
) : Exception(
    formatParsingException(
        parserName,
        parsingFieldName,
        parsingFieldValue,
        reason,
        objectParsingDescription
    )
) {
    companion object {
        fun formatParsingException(
            parserName: String,
            parsingFieldName: String,
            parsingFieldValue: String?,
            reason: String?,
            objectParsingDescription: String?
        ): String {
            val exceptionMessageBuilder =
                StringBuilder("Error occurred on $parserName parsing field $parsingFieldName with value $parsingFieldValue")
            reason?.takeIf { it.isNotEmpty() }?.let {
                exceptionMessageBuilder.append(" because $reason")
            }

            objectParsingDescription?.takeIf { it.isNotEmpty() }?.let {
                exceptionMessageBuilder.append(" for $objectParsingDescription")
            }

            return exceptionMessageBuilder.toString()
        }
    }
}