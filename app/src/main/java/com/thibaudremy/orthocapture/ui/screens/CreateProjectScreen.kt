package com.thibaudremy.orthocapture.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun CreateProjectScreen(
    onBack: () -> Unit,
    onProjectCreated: () -> Unit,
) {
    var projectName by remember { mutableStateOf("") }
    var projectNotes by remember { mutableStateOf("") }

    OrthoCaptureScreenScaffold(title = "Nouveau projet", onBack = onBack) { paddingValues ->
        ScreenBody(paddingValues) {
            ScreenIntro(
                title = "Créer une session terrain",
                description = "Renseignez les informations minimales du lot de capture. Le stockage réel sera ajouté dans un prochain lot.",
            )
            OutlinedTextField(
                value = projectName,
                onValueChange = { projectName = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Nom du projet") },
                singleLine = true,
            )
            OutlinedTextField(
                value = projectNotes,
                onValueChange = { projectNotes = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Notes terrain") },
                minLines = 4,
            )
            PrimaryNavigationButton(
                text = "Continuer vers le projet",
                onClick = onProjectCreated,
            )
        }
    }
}
