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
    val german: MutableState<String> = mutableStateOf("")
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
    val target: MutableState<String> = mutableStateOf("")
    val winLine: MutableState<String> = mutableStateOf("")
    val step: MutableState<String> = mutableStateOf("")
    val score: MutableState<String> = mutableStateOf("")

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
        "target" to target,
        "winLine" to winLine,
        "step" to step,
        "score" to score,
        "german" to german,
    )
}







