package com.thibaudremy.orthocapture.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ProjectDetailScreen(
    onBack: () -> Unit,
    onCapture: () -> Unit,
    onGallery: () -> Unit,
    onExport: () -> Unit,
) {
    OrthoCaptureScreenScaffold(title = "Projet", onBack = onBack) { paddingValues ->
        ScreenBody(paddingValues) {
            ScreenIntro(
                title = "Projet de démonstration",
                description = "Point d’entrée vers la capture, la galerie et l’export du projet.",
            )
            Text("État actuel : squelette fonctionnel sans persistance locale.")
            PrimaryNavigationButton(text = "Capture", onClick = onCapture)
            SecondaryNavigationButton(text = "Galerie", onClick = onGallery)
            SecondaryNavigationButton(text = "Export", onClick = onExport)
        }
    }
}
