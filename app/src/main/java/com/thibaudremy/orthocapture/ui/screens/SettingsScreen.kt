package com.thibaudremy.orthocapture.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen(onBack: () -> Unit) {
    OrthoCaptureScreenScaffold(title = "Réglages", onBack = onBack) { paddingValues ->
        ScreenBody(paddingValues) {
            ScreenIntro(
                title = "Réglages",
                description = "Paramètres de référence du lot 01, sans CameraX, base de données ni upload serveur.",
            )
            Text("Appareil cible : Samsung Galaxy S24")
            Text("Modèle : SM-S921B/DS")
            Text("Mode : orthophoto visuelle / documentaire")
            Text("Stockage local : activé")
            Text("Traitement photogrammétrique : hors périmètre V1")
        }
    }
}
