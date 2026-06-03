package com.thibaudremy.orthocapture.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen(onBack: () -> Unit) {
    OrthoCaptureScreenScaffold(title = "Réglages", onBack = onBack) { paddingValues ->
        ScreenBody(paddingValues) {
            ScreenIntro(
                title = "Réglages",
                description = "Placeholder : préférences de capture et d’export adaptées au Samsung Galaxy S24 SM-S921B/DS.",
            )
        }
    }
}
