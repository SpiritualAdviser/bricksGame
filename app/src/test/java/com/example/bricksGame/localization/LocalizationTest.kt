package com.example.bricksGame.localization

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class LocalizationTest {
    private val dictionary = Dictionary()

    @Before
    fun set(){
        val currentLocale = "de"
        val systemLanguage = when (currentLocale) {
            "ru" -> dictionary.ru
            "de" -> dictionary.de
            "en" -> dictionary.en
            else -> dictionary.en
        }
        Localization.runTranslation(systemLanguage)
    }

    @Test
    fun runTranslation() {
        val result = Localization.survival.value
        val expectedValue = dictionary.de.getValue("survival")
        assertEquals(expectedValue, result)
    }
}