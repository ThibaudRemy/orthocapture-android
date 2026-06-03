package com.thibaudremy.orthocapture.camera

import android.content.Context
import android.os.Environment
import java.io.File
import java.util.Locale

class LocalPhotoStorage(private val context: Context) {
    fun createPhotoFile(): File {
        val photoDirectory = File(
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            ORTHOCAPTURE_DIRECTORY,
        ).apply { mkdirs() }

        val nextIndex = (photoDirectory.listFiles { file ->
            file.isFile && file.extension.equals(JPG_EXTENSION, ignoreCase = true)
        } ?: emptyArray<File>())
            .mapNotNull { file -> extractPhotoIndex(file.name) }
            .maxOrNull()
            ?.plus(1)
            ?: FIRST_PHOTO_INDEX

        return File(photoDirectory, String.format(Locale.US, PHOTO_FILE_PATTERN, nextIndex))
    }

    private fun extractPhotoIndex(fileName: String): Int? {
        return PHOTO_FILE_REGEX.matchEntire(fileName)?.groupValues?.get(1)?.toIntOrNull()
    }

    companion object {
        private const val ORTHOCAPTURE_DIRECTORY = "orthocapture"
        private const val JPG_EXTENSION = "jpg"
        private const val FIRST_PHOTO_INDEX = 1
        private const val PHOTO_FILE_PATTERN = "IMG_%04d.jpg"
        private val PHOTO_FILE_REGEX = Regex("IMG_(\\d{4})\\.jpg", RegexOption.IGNORE_CASE)
    }
}
