package com.thibaudremy.orthocapture.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.thibaudremy.orthocapture.camera.LocalPhotoStorage

@Composable
fun CaptureScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val hasCameraPermission = remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED,
        )
    }
    var imageCapture by remember { mutableStateOf<ImageCapture?>(null) }
    var captureMessage by remember { mutableStateOf<String?>(null) }
    var captureError by remember { mutableStateOf<String?>(null) }
    val photoStorage = remember(context) { LocalPhotoStorage(context) }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ) { isGranted ->
        hasCameraPermission.value = isGranted
        captureError = if (isGranted) {
            null
        } else {
            "Permission caméra refusée. La capture photo est indisponible."
        }
    }

    LaunchedEffect(Unit) {
        if (!hasCameraPermission.value) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    OrthoCaptureScreenScaffold(title = "Capture photo", onBack = onBack) { paddingValues ->
        ScreenBody(paddingValues) {
            ScreenIntro(
                title = "Capture photo",
                description = "Aperçu CameraX et capture JPG locale dans le dossier applicatif.",
            )

            if (hasCameraPermission.value) {
                CameraPreview(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(420.dp),
                    onImageCaptureReady = { imageCapture = it },
                    onCameraError = { captureError = it },
                )

                Button(
                    onClick = {
                        val currentImageCapture = imageCapture
                        if (currentImageCapture == null) {
                            captureError = "La caméra n'est pas encore prête."
                            return@Button
                        }

                        val photoFile = photoStorage.createPhotoFile()
                        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

                        currentImageCapture.takePicture(
                            outputOptions,
                            ContextCompat.getMainExecutor(context),
                            object : ImageCapture.OnImageSavedCallback {
                                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                                    captureError = null
                                    captureMessage = "Photo enregistrée : ${photoFile.name}"
                                }

                                override fun onError(exception: ImageCaptureException) {
                                    captureMessage = null
                                    captureError = "Échec de la capture : ${exception.message ?: "erreur inconnue"}"
                                }
                            },
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Prendre photo")
                }
            } else {
                PermissionRequestCard(onRequestPermission = {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                })
            }

            captureMessage?.let { message ->
                Text(text = message, color = MaterialTheme.colorScheme.primary)
            }

            captureError?.let { message ->
                Text(text = message, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
private fun CameraPreview(
    modifier: Modifier = Modifier,
    onImageCaptureReady: (ImageCapture) -> Unit,
    onCameraError: (String) -> Unit,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val previewView = remember {
        PreviewView(context).apply {
            scaleType = PreviewView.ScaleType.FILL_CENTER
        }
    }

    DisposableEffect(lifecycleOwner, previewView) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        val executor = ContextCompat.getMainExecutor(context)
        val listener = Runnable {
            try {
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also { preview ->
                    preview.setSurfaceProvider(previewView.surfaceProvider)
                }
                val imageCapture = ImageCapture.Builder()
                    .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                    .build()

                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    preview,
                    imageCapture,
                )
                onImageCaptureReady(imageCapture)
            } catch (exception: Exception) {
                onCameraError("Impossible de démarrer CameraX : ${exception.message ?: "erreur inconnue"}")
            }
        }

        cameraProviderFuture.addListener(listener, executor)

        onDispose {
            runCatching { cameraProviderFuture.get().unbindAll() }
        }
    }

    Card(modifier = modifier) {
        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(
                factory = { previewView },
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

@Composable
private fun PermissionRequestCard(onRequestPermission: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Autorisation caméra requise",
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = "OrthoCapture a besoin de la caméra pour afficher l'aperçu et enregistrer les photos JPG localement.",
                style = MaterialTheme.typography.bodyMedium,
            )
            Button(onClick = onRequestPermission) {
                Text("Autoriser la caméra")
            }
        }
    }
}
