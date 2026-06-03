# OrthoCapture Android

Application Android native de capture photographique assistée pour préparer des jeux de photos destinés à un futur traitement photogrammétrique documentaire.

## Objectif V1

- Création de projets terrain
- Capture photo assistée
- Stockage local des images
- Métadonnées JSON/CSV
- Export ZIP
- Préparation future pour traitement OpenDroneMap / WebODM / NodeODM

## Cible

- Samsung Galaxy S24
- Modèle SM-S921B/DS
- Niveau de précision : orthophoto visuelle / documentaire

## Lot 01

Ce lot met en place un squelette Android natif compilable en Kotlin avec Jetpack Compose et Navigation Compose.

Écrans disponibles :

- `ProjectListScreen` : liste d’entrée des projets et accès aux réglages.
- `CreateProjectScreen` : formulaire placeholder de création de projet.
- `ProjectDetailScreen` : point d’entrée vers les actions du projet.
- `CaptureScreen` : placeholder, sans CameraX pour ce lot.
- `GalleryScreen` : placeholder de galerie projet.
- `ExportScreen` : placeholder d’export.
- `SettingsScreen` : placeholder des réglages.

Hors périmètre de ce lot : CameraX, traitement photogrammétrique, upload serveur et base de données.

## Structure du projet

```text
.
├── app/                         # Module Android applicatif
│   └── src/main/java/com/thibaudremy/orthocapture/
│       ├── MainActivity.kt
│       ├── navigation/          # Destinations et NavHost Compose
│       └── ui/                  # Thème et écrans Compose
├── docs/                        # Documentation produit existante
├── gradle/                      # Version catalog Gradle
├── build.gradle.kts
└── settings.gradle.kts
```

## Prérequis de compilation

- JDK compatible avec la version d’Android Gradle Plugin utilisée.
- Android SDK installé avec la plateforme `android-35`.
- Une variable `ANDROID_HOME` ou `ANDROID_SDK_ROOT` pointant vers le SDK Android.

## Compiler

Depuis la racine du dépôt, avec une installation Gradle disponible :

```bash
gradle :app:assembleDebug
```

Optionnellement, générez le wrapper Gradle pour stabiliser les builds locaux :

```bash
gradle wrapper --gradle-version 8.14.4
./gradlew :app:assembleDebug
```

## Ouvrir dans Android Studio

1. Ouvrir le dossier racine du dépôt.
2. Laisser Android Studio synchroniser Gradle.
3. Sélectionner un appareil ou émulateur Android.
4. Lancer la configuration `app`.
