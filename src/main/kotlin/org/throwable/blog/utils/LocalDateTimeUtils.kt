package org.throwable.blog.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @author throwable
 * @version v1.0
 * @since 2018/3/13 1:00
 * @description
 */
object LocalDateTimeUtils {

    private val FORMATTER_MAP = HashMap<String, DateTimeFormatter>()

    fun format(target: LocalDateTime, pattern: String): String {
        return getOrCreateFormatter(pattern).format(target)
    }

    fun parse(target: String, pattern: String): LocalDateTime {
        return LocalDateTime.parse(target, getOrCreateFormatter(pattern))
    }

    private fun getOrCreateFormatter(pattern: String): DateTimeFormatter {
        return if (FORMATTER_MAP.containsKey(pattern)) {
            FORMATTER_MAP.getValue(pattern)
        } else {
            val dateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
            FORMATTER_MAP[pattern] = dateTimeFormatter
            dateTimeFormatter
        }
    }
}