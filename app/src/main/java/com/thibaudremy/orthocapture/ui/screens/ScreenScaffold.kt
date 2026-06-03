package com.thibaudremy.orthocapture.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrthoCaptureScreenScaffold(
    title: String,
    onBack: (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    if (onBack != null) {
                        TextButton(onClick = onBack) {
                            Text("Retour")
                        }
                    }
                },
            )
        },
        content = content,
    )
}

@Composable
fun ScreenBody(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        content = content,
    )
}

@Composable
fun ScreenIntro(title: String, description: String) {
    Text(text = title, style = MaterialTheme.typography.headlineSmall)
    Text(text = description, style = MaterialTheme.typography.bodyLarge)
}

@Composable
fun PrimaryNavigationButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text)
    }
}

@Composable
fun SecondaryNavigationButton(text: String, onClick: () -> Unit) {
    OutlinedButton(onClick = onClick) {
        Text(text)
    }
}

@Composable
fun OptionGroup(
    title: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        options.chunked(3).forEach { optionRow ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                optionRow.forEach { option ->
                    if (option == selectedOption) {
                        Button(onClick = { onOptionSelected(option) }) {
                            Text(option)
                        }
                    } else {
                        OutlinedButton(onClick = { onOptionSelected(option) }) {
                            Text(option)
                        }
                    }
                }
            }
        }
    }
}