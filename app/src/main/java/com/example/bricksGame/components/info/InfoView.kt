package com.example.bricksGame.components.info

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bricksGame.components.gameMeny.models.HomeScreenViewModel

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun Info(url: String, homeScreenViewModel: HomeScreenViewModel = hiltViewModel()) {
    var webView: WebView? = null
    AndroidView(factory = {
        WebView(it).apply {

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
            if (it.canGoBack()) {
                it.goBack()
            } else {
                it.destroy()
                homeScreenViewModel.soundController.onWebView = false
                homeScreenViewModel.buttonController.navigateToHome()
            }
        }
    }
}