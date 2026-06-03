package com.thibaudremy.orthocapture.domain.model

data class PhotoItem(
    val id: String,
    val projectId: String,
    val filename: String,
    val timestamp: String,
    val latitude: Double?,
    val longitude: Double?,
    val altitude: Double?,
    val qualityFlag: String,
)
