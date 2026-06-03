package com.thibaudremy.orthocapture.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun ProjectListScreen(
    onCreateProject: () -> Unit,
    onOpenProject: () -> Unit,
    onOpenSettings: () -> Unit,
) {
    OrthoCaptureScreenScaffold(title = "Projets") { paddingValues ->
        ScreenBody(paddingValues) {
            ScreenIntro(
                title = "OrthoCapture",
                description = "Préparez des séries de photos terrain pour une photogrammétrie documentaire.",
            )
            PrimaryNavigationButton(
                text = "Créer un projet",
                onClick = onCreateProject,
            )
            SecondaryNavigationButton(
                text = "Ouvrir le projet de démonstration",
                onClick = onOpenProject,
            )
            SecondaryNavigationButton(
                text = "Réglages",
                onClick = onOpenSettings,
            )
        }
    }
}
