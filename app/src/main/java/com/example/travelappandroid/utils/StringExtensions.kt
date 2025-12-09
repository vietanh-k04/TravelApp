package com.example.travelappandroid.utils

import java.text.Normalizer
import java.util.Locale

fun String.toNoAccent(): String {
    var result = this

    // Xóa các khoảng trắng đặc biệt / zero-width
    result = result.replace("[\\u200B-\\u200D\\uFEFF]".toRegex(), "")
    result = result.replace("\u00A0", " ") // non-breaking space

    // Chuẩn hóa unicode
    result = Normalizer.normalize(result, Normalizer.Form.NFD)
        .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")

    // Chuẩn hóa chữ Đ/đ
    result = result.replace("đ", "d").replace("Đ", "D")

    // Loại bỏ dấu câu đặc biệt (tăng độ chính xác search)
    result = result.replace("[^a-zA-Z0-9 ]".toRegex(), " ")

    // Xóa thừa & lowercase
    return result.lowercase(Locale.getDefault()).trim()
}