package com.example.bricksGame.helper

import android.annotation.SuppressLint
import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.bricksGame.config.GameConfig
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.times

@RunWith(AndroidJUnit4::class)
class SoundControllerTest {
    private val gameConfig = GameConfig()
    val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
    private val soundController = SoundController(gameConfig, context = appContext)

    @Before
    fun set(){
        soundController.createMediaPlayer()
    }

    @Test
    fun setRun() {
        val result = soundController.isRun
        val expectedValue = true
        assertEquals(expectedValue, result)
    }
}