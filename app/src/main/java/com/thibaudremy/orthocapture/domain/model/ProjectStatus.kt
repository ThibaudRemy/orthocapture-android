package com.thibaudremy.orthocapture.domain.model

enum class ProjectStatus(val label: String) {
    DRAFT("Brouillon"),
    READY_FOR_CAPTURE("Prêt pour capture"),
    CAPTURE_IN_PROGRESS("Capture en cours"),
    CAPTURE_COMPLETE("Capture terminée"),
}
