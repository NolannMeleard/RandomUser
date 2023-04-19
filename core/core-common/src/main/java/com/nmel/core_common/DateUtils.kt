package com.nmel.core_common

import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Created by Nolann Méléard on 19 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
object DateUtils {
    private const val ISO8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ss"

    /**
     * Format an iso8601 string date and format it in dd/MM/yyyy
     *
     * @param date The iso8601 string date to format
     * @return The formatted date
     */
    fun parseDateAndFormatToDisplay(date: String): String {
        return try {
            val dateParsed = SimpleDateFormat(ISO8601_PATTERN, Locale.getDefault()).parse(date)

            dateParsed?.let {
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(it)
            } ?: ""
        } catch (ex: Exception) {
            Timber.w(ex)
            ""
        }
    }
}