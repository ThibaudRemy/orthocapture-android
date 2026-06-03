package com.thibaudremy.orthocapture.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.thibaudremy.orthocapture.data.ProjectRepository

@Composable
fun ProjectListScreen(
    onCreateProject: () -> Unit,
    onOpenProject: (String) -> Unit,
    onOpenSettings: () -> Unit,
) {
    val projects = ProjectRepository.getProjects()

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
            projects.forEach { project ->
                SecondaryNavigationButton(
                    text = "${project.name} · ${project.photoCount} photo(s)",
                    onClick = { onOpenProject(project.id) },
                )
            }
            SecondaryNavigationButton(
                text = "Réglages",
                onClick = onOpenSettings,
            )
        }
    }
}
