package com.thibaudremy.orthocapture.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.thibaudremy.orthocapture.domain.model.Project

@Composable
fun ProjectDetailScreen(
    project: Project,
    onBack: () -> Unit,
    onCapture: () -> Unit,
    onGallery: () -> Unit,
    onExport: () -> Unit,
) {
    OrthoCaptureScreenScaffold(title = "Projet", onBack = onBack) { paddingValues ->
        ScreenBody(paddingValues) {
            ScreenIntro(
                title = project.name,
                description = project.description,
            )
            ProjectInfoLine(label = "Type de capture", value = project.captureType.label)
            ProjectInfoLine(label = "Qualité", value = project.quality.label)
            ProjectInfoLine(label = "Photos", value = "${project.photoCount} photo(s)")
            ProjectInfoLine(label = "Statut", value = project.status.label)
            ProjectInfoLine(label = "Appareil cible", value = project.deviceModel)
            PrimaryNavigationButton(text = "Capture", onClick = onCapture)
            SecondaryNavigationButton(text = "Galerie", onClick = onGallery)
            SecondaryNavigationButton(text = "Export", onClick = onExport)
        }
    }
}

@Composable
private fun ProjectInfoLine(label: String, value: String) {
    Text(
        text = "$label : $value",
        style = MaterialTheme.typography.bodyLarge,
    )
}
