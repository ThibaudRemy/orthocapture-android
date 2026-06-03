package com.thibaudremy.orthocapture.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun CaptureScreen(onBack: () -> Unit) {
    OrthoCaptureScreenScaffold(title = "Capture", onBack = onBack) { paddingValues ->
        ScreenBody(paddingValues) {
            ScreenIntro(
                title = "Capture photo",
                description = "Placeholder : CameraX et les guides de prise de vue seront ajoutés dans un lot ultérieur.",
            )
        }
    }
}
