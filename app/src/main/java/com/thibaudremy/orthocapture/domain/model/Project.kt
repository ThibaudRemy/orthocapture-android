package com.thibaudremy.orthocapture.domain.model

data class Project(
    val id: String,
    val name: String,
    val description: String,
    val captureType: CaptureType,
    val quality: CaptureQuality,
    val createdAt: String,
    val deviceModel: String,
    val photoCount: Int,
    val status: ProjectStatus,
)
