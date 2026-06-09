package com.thibaudremy.orthocapture.ui.screens

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.thibaudremy.orthocapture.camera.LocalPhoto
import com.thibaudremy.orthocapture.camera.LocalPhotoStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun GalleryScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val photoStorage = remember(context) { LocalPhotoStorage(context.applicationContext) }
    val photos by produceState(initialValue = emptyList(), photoStorage) {
        value = withContext(Dispatchers.IO) { photoStorage.listPhotos() }
    }

    OrthoCaptureScreenScaffold(title = "Galerie", onBack = onBack) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            ScreenIntro(
                title = "Galerie projet",
                description = "${photos.size} photo${if (photos.size > 1) "s" else ""} JPG capturée${if (photos.size > 1) "s" else ""} localement.",
            )

            SecondaryNavigationButton(text = "Retour au projet", onClick = onBack)

            if (photos.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Aucune photo capturée pour le moment.",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 150.dp),
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(bottom = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(photos, key = LocalPhoto::path) { photo ->
                        LocalPhotoCard(photo)
                    }
                }
            }
        }
    }
}

@Composable
private fun LocalPhotoCard(photo: LocalPhoto) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column {
            LocalPhotoThumbnail(photo)
            Text(
                text = photo.filename,
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
            )
        }
    }
}

@Composable
private fun LocalPhotoThumbnail(photo: LocalPhoto) {
    val image by produceState<ImageBitmap?>(
        initialValue = null,
        photo.path,
        photo.lastModified,
    ) {
        value = withContext(Dispatchers.IO) { decodeThumbnail(photo.path) }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(4f / 3f),
        contentAlignment = Alignment.Center,
    ) {
        if (image == null) {
            Text("Miniature indisponible", style = MaterialTheme.typography.bodySmall)
        } else {
            Image(
                bitmap = image!!,
                contentDescription = "Photo ${photo.filename}",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

private fun decodeThumbnail(path: String): ImageBitmap? {
    val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
    BitmapFactory.decodeFile(path, bounds)

    var sampleSize = 1
    while (bounds.outWidth / sampleSize > THUMBNAIL_MAX_SIZE ||
        bounds.outHeight / sampleSize > THUMBNAIL_MAX_SIZE
    ) {
        sampleSize *= 2
    }

    val options = BitmapFactory.Options().apply { inSampleSize = sampleSize }
    return BitmapFactory.decodeFile(path, options)?.asImageBitmap()
}

private const val THUMBNAIL_MAX_SIZE = 512
