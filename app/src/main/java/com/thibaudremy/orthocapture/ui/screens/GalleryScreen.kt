package com.thibaudremy.orthocapture.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun GalleryScreen(onBack: () -> Unit) {
    OrthoCaptureScreenScaffold(title = "Galerie", onBack = onBack) { paddingValues ->
        ScreenBody(paddingValues) {
            ScreenIntro(
                title = "Galerie projet",
                description = "Placeholder : l’affichage des photos et métadonnées locales sera ajouté après la capture réelle.",
            )
        }
    }
}
