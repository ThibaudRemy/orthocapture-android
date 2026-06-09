package com.thibaudremy.orthocapture.camera

data class LocalPhoto(
    val filename: String,
    val path: String,
    val lastModified: Long,
    val sizeBytes: Long,
)
