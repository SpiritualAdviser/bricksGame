package com.example.bricksGame.localization

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


object Localization {

    fun runTranslation(dictionary: Map<String, String>) {

        strings.forEach { (key, text) ->
            val currentText = dictionary.getValue(key)
            text.value = currentText
        }
    }

    val adventure: MutableState<String> = mutableStateOf("")
    val russian: MutableState<String> = mutableStateOf("")
    val english: MutableState<String> = mutableStateOf("")

    private val strings = mapOf(
        "adventure" to adventure,
        "russian" to russian,
        "english" to english,

    )
}


class Dictionary {
    val en = mapOf(
        "adventure" to "Adventure",
        "russian" to "Russian",
        "english" to "English"
    )

    val ru = mapOf(
        "adventure" to "Приключения",
        "russian" to "Русский",
        "english" to "Английский",
    )
}






