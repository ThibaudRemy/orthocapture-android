package com.thibaudremy.orthocapture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.thibaudremy.orthocapture.navigation.OrthoCaptureNavHost
import com.thibaudremy.orthocapture.ui.theme.OrthoCaptureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrthoCaptureTheme {
                OrthoCaptureNavHost()
            }
        }
    }
}
