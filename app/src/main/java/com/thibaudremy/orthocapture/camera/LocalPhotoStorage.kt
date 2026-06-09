package com.thibaudremy.orthocapture.camera

import android.content.Context
import android.os.Environment
import java.io.File
import java.util.Locale

class LocalPhotoStorage(private val context: Context) {
    fun createPhotoFile(): File {
        val photoDirectory = getPhotoDirectory().apply { mkdirs() }

        val nextIndex = listPhotoFiles(photoDirectory)
            .mapNotNull { file -> extractPhotoIndex(file.name) }
            .maxOrNull()
            ?.plus(1)
            ?: FIRST_PHOTO_INDEX

        return File(photoDirectory, String.format(Locale.US, PHOTO_FILE_PATTERN, nextIndex))
    }

    fun listPhotos(): List<LocalPhoto> {
        return listPhotoFiles(getPhotoDirectory())
            .sortedByDescending(File::lastModified)
            .map { file ->
                LocalPhoto(
                    filename = file.name,
                    path = file.absolutePath,
                    lastModified = file.lastModified(),
                    sizeBytes = file.length(),
                )
            }
    }

    private fun getPhotoDirectory(): File {
        val picturesDirectory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            ?: File(context.filesDir, Environment.DIRECTORY_PICTURES)
        return File(picturesDirectory, ORTHOCAPTURE_DIRECTORY)
    }

    private fun listPhotoFiles(directory: File): List<File> {
        return directory.listFiles { file ->
            file.isFile && file.extension.lowercase(Locale.US) in SUPPORTED_EXTENSIONS
        }?.toList().orEmpty()
    }

    private fun extractPhotoIndex(fileName: String): Int? {
        return PHOTO_FILE_REGEX.matchEntire(fileName)?.groupValues?.get(1)?.toIntOrNull()
    }

    companion object {
        private const val ORTHOCAPTURE_DIRECTORY = "orthocapture"
        private const val FIRST_PHOTO_INDEX = 1
        private const val PHOTO_FILE_PATTERN = "IMG_%04d.jpg"
        private val PHOTO_FILE_REGEX = Regex("IMG_(\\d{4})\\.jpe?g", RegexOption.IGNORE_CASE)
        private val SUPPORTED_EXTENSIONS = setOf("jpg", "jpeg")
    }
}
