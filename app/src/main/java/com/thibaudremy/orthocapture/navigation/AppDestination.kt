package com.thibaudremy.orthocapture.navigation

sealed class AppDestination(val route: String) {
    data object ProjectList : AppDestination("projects")
    data object CreateProject : AppDestination("projects/create")
    data object ProjectDetail : AppDestination("projects/detail")
    data object Capture : AppDestination("capture")
    data object Gallery : AppDestination("gallery")
    data object Export : AppDestination("export")
    data object Settings : AppDestination("settings")
}
