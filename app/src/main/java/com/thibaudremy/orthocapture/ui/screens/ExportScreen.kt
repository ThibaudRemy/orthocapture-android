package com.thibaudremy.orthocapture.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun ExportScreen(onBack: () -> Unit) {
    OrthoCaptureScreenScaffold(title = "Export", onBack = onBack) { paddingValues ->
        ScreenBody(paddingValues) {
            ScreenIntro(
                title = "Export documentaire",
                description = "Placeholder : l’export ZIP avec images et métadonnées JSON/CSV sera implémenté dans un prochain lot.",
            )
        }
    }
}
