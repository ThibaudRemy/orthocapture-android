package com.thibaudremy.orthocapture.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val CaptureTypes = listOf("sol", "façade", "toiture", "intérieur", "autre")
private val QualityLevels = listOf("rapide", "standard", "haute qualité")

@Composable
fun CreateProjectScreen(
    onBack: () -> Unit,
    onProjectCreated: () -> Unit,
) {
    var projectName by remember { mutableStateOf("") }
    var projectDescription by remember { mutableStateOf("") }
    var selectedCaptureType by remember { mutableStateOf(CaptureTypes.first()) }
    var selectedQuality by remember { mutableStateOf("standard") }

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
                value = projectDescription,
                onValueChange = { projectDescription = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Description") },
                minLines = 4,
            )
            OptionGroup(
                title = "Type de capture",
                options = CaptureTypes,
                selectedOption = selectedCaptureType,
                onOptionSelected = { selectedCaptureType = it },
            )
            OptionGroup(
                title = "Qualité",
                options = QualityLevels,
                selectedOption = selectedQuality,
                onOptionSelected = { selectedQuality = it },
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                OutlinedButton(onClick = onBack) {
                    Text("Annuler")
                }
                Button(onClick = onProjectCreated) {
                    Text("Créer projet")
                }
            }
        }
    }
}
