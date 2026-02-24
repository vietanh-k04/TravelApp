package com.example.travelappandroid.utils

import java.text.Normalizer
import java.util.Locale

fun String.toNoAccent(): String {
    var result = this

    result = result.replace("[\\u200B-\\u200D\\uFEFF]".toRegex(), "")
    result = result.replace("\u00A0", " ")

    result = Normalizer.normalize(result, Normalizer.Form.NFD)
        .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")

    result = result.replace("đ", "d").replace("Đ", "D")

    result = result.replace("[^a-zA-Z0-9 ]".toRegex(), " ")

    return result.lowercase(Locale.getDefault()).trim()
}