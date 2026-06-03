package com.thibaudremy.orthocapture.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.thibaudremy.orthocapture.data.ProjectRepository
import com.thibaudremy.orthocapture.ui.screens.CaptureScreen
import com.thibaudremy.orthocapture.ui.screens.CreateProjectScreen
import com.thibaudremy.orthocapture.ui.screens.ExportScreen
import com.thibaudremy.orthocapture.ui.screens.GalleryScreen
import com.thibaudremy.orthocapture.ui.screens.ProjectDetailScreen
import com.thibaudremy.orthocapture.ui.screens.ProjectListScreen
import com.thibaudremy.orthocapture.ui.screens.SettingsScreen

@Composable
fun OrthoCaptureNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppDestination.ProjectList.route,
    ) {
        composable(AppDestination.ProjectList.route) {
            ProjectListScreen(
                onCreateProject = { navController.navigate(AppDestination.CreateProject.route) },
                onOpenProject = { projectId ->
                    navController.navigate(AppDestination.ProjectDetail.createRoute(projectId))
                },
                onOpenSettings = { navController.navigate(AppDestination.Settings.route) },
            )
        }
        composable(AppDestination.CreateProject.route) {
            CreateProjectScreen(
                onBack = { navController.popBackStack() },
                onProjectCreated = {
                    navController.navigate(
                        AppDestination.ProjectDetail.createRoute(ProjectRepository.DEFAULT_PROJECT_ID),
                    ) {
                        popUpTo(AppDestination.ProjectList.route)
                    }
                },
            )
        }
        composable(
            route = AppDestination.ProjectDetail.route,
            arguments = listOf(
                navArgument(AppDestination.ProjectDetail.PROJECT_ID_ARGUMENT) {
                    type = NavType.StringType
                },
            ),
        ) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString(
                AppDestination.ProjectDetail.PROJECT_ID_ARGUMENT,
            )
            val project = projectId
                ?.let(ProjectRepository::getProject)
                ?: ProjectRepository.getDefaultProject()

            ProjectDetailScreen(
                project = project,
                onBack = { navController.popBackStack() },
                onCapture = { navController.navigate(AppDestination.Capture.route) },
                onGallery = { navController.navigate(AppDestination.Gallery.route) },
                onExport = { navController.navigate(AppDestination.Export.route) },
            )
        }
        composable(AppDestination.Capture.route) {
            CaptureScreen(onBack = { navController.popBackStack() })
        }
        composable(AppDestination.Gallery.route) {
            GalleryScreen(onBack = { navController.popBackStack() })
        }
        composable(AppDestination.Export.route) {
            ExportScreen(onBack = { navController.popBackStack() })
        }
        composable(AppDestination.Settings.route) {
            SettingsScreen(onBack = { navController.popBackStack() })
        }
    }
}
