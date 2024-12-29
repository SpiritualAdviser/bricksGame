package com.example.bricksGame.components.info

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.bricksGame.helper.ButtonController
import com.example.bricksGame.soundController

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun Info(url: String) {
    var webView: WebView? = null
    AndroidView(factory = {
        WebView(it).apply {
            it

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

//            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()

            settings.javaScriptEnabled = true
            loadUrl(url)
        }

    }, update = {
        webView = it
        it.loadUrl(url)
    })

    BackHandler {
        webView?.let {
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                webView.destroy()
                soundController.onWebView = false
//                soundController.soundMuteOnRestart()
                ButtonController.navigateToHome()
            }
        }
    }

}