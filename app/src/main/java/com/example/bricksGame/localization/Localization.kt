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
    val survival: MutableState<String> = mutableStateOf("")
    val players: MutableState<String> = mutableStateOf("")
    val records: MutableState<String> = mutableStateOf("")
    val info: MutableState<String> = mutableStateOf("")
    val snackAddPlayer: MutableState<String> = mutableStateOf("")
    val snackEmptyNamePlayer: MutableState<String> = mutableStateOf("")
    val createPlayer: MutableState<String> = mutableStateOf("")
    val achieve: MutableState<String> = mutableStateOf("")
    val enterPlayerName: MutableState<String> = mutableStateOf("")
    val labelName: MutableState<String> = mutableStateOf("")

    private val strings = mapOf(
        "adventure" to adventure,
        "russian" to russian,
        "english" to english,
        "survival" to survival,
        "players" to players,
        "records" to records,
        "info" to info,
        "snackAddPlayer" to snackAddPlayer,
        "snackEmptyNamePlayer" to snackEmptyNamePlayer,
        "createPlayer" to createPlayer,
        "achiev" to achieve,
        "enterPlayerName" to enterPlayerName,
        "labelName" to labelName,
    )
}

class Dictionary {
    val en = mapOf(
        "adventure" to "Adventure",
        "russian" to "Russian",
        "english" to "English",
        "survival" to "Survival",
        "players" to "Players",
        "records" to "Records",
        "info" to "Info",
        "snackAddPlayer" to "The Player is added",
        "snackEmptyNamePlayer" to "The name of Player can not be empty",
        "createPlayer" to "Create Player",
        "achiev" to "Achiev",
        "enterPlayerName" to "Enter player name",
        "labelName" to "name",
    )

    val ru = mapOf(
        "adventure" to "Приключения",
        "russian" to "Русский",
        "english" to "Английский",
        "survival" to "Выживание",
        "players" to "Игроки",
        "records" to "Рекорды",
        "info" to "Информация",
        "snackAddPlayer" to "Игрок добавлен",
        "snackEmptyNamePlayer" to "Заполните Имя Игрока",
        "createPlayer" to "Создать Игрока",
        "achiev" to "Рекорды",
        "enterPlayerName" to "Имя игрока",
        "labelName" to "имя",
    )
}






