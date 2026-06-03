package com.thibaudremy.orthocapture.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

private val FakeProjects = listOf(
    "Façade nord - atelier municipal",
    "Relevé sol - cour intérieure",
    "Toiture annexe - diagnostic visuel",
)

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
            Text("Projets récents", style = MaterialTheme.typography.titleMedium)
            FakeProjects.forEach { projectName ->
                SecondaryNavigationButton(
                    text = projectName,
                    onClick = onOpenProject,
                )
            }
            SecondaryNavigationButton(
                text = "Réglages",
                onClick = onOpenSettings,
            )
        }
    }
}
