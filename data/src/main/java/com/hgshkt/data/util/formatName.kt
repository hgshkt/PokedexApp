package com.hgshkt.data.util

import java.util.Locale

fun String.formatName(
    locale: Locale = Locale.ROOT
): String {
    return capitalize(locale).replace('-', ' ')
}