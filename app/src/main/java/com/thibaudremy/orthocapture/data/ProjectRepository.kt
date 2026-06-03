package com.thibaudremy.orthocapture.data

import com.thibaudremy.orthocapture.domain.model.CaptureQuality
import com.thibaudremy.orthocapture.domain.model.CaptureType
import com.thibaudremy.orthocapture.domain.model.Project
import com.thibaudremy.orthocapture.domain.model.ProjectStatus

object ProjectRepository {
    const val DEFAULT_PROJECT_ID = "facade-nord-atelier-municipal"

    private val projects = listOf(
        Project(
            id = DEFAULT_PROJECT_ID,
            name = "Façade nord - atelier municipal",
            description = "Relevé visuel de façade pour préparer une orthophoto documentaire.",
            captureType = CaptureType.FACADE,
            quality = CaptureQuality.DOCUMENTARY,
            createdAt = "2026-06-03T09:00:00Z",
            deviceModel = "Samsung Galaxy S24 SM-S921B/DS",
            photoCount = 24,
            status = ProjectStatus.CAPTURE_IN_PROGRESS,
        ),
        Project(
            id = "releve-sol-cour-interieure",
            name = "Relevé sol - cour intérieure",
            description = "Parcours de capture au sol pour documenter une cour intérieure.",
            captureType = CaptureType.GROUND,
            quality = CaptureQuality.DOCUMENTARY,
            createdAt = "2026-06-03T10:30:00Z",
            deviceModel = "Samsung Galaxy S24 SM-S921B/DS",
            photoCount = 12,
            status = ProjectStatus.READY_FOR_CAPTURE,
        ),
        Project(
            id = "toiture-annexe-diagnostic-visuel",
            name = "Toiture annexe - diagnostic visuel",
            description = "Série photo préparatoire pour diagnostic visuel de toiture annexe.",
            captureType = CaptureType.ROOF,
            quality = CaptureQuality.HIGH_DETAIL,
            createdAt = "2026-06-03T11:15:00Z",
            deviceModel = "Samsung Galaxy S24 SM-S921B/DS",
            photoCount = 0,
            status = ProjectStatus.DRAFT,
        ),
    )

    fun getProjects(): List<Project> = projects

    fun getProject(projectId: String): Project? = projects.firstOrNull { it.id == projectId }

    fun getDefaultProject(): Project = projects.first()
}
