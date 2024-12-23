package com.example.bricksGame.components.popups

import android.annotation.SuppressLint
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun WinLine(text: String) {
    val snackState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    coroutineScope.launch {

        snackState.showSnackbar(text)
    }
}

