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

- `ProjectListScreen` : liste d’entrée avec projets fictifs et accès aux réglages.
- `CreateProjectScreen` : formulaire placeholder avec nom, description, type de capture, qualité et actions créer/annuler.
- `ProjectDetailScreen` : point d’entrée vers les actions du projet.
- `CaptureScreen` : placeholder, sans CameraX pour ce lot.
- `GalleryScreen` : placeholder de galerie projet.
- `ExportScreen` : placeholder d’export.
- `SettingsScreen` : rappel des paramètres cible du lot 01 (Galaxy S24, SM-S921B/DS, mode documentaire, stockage local activé).

Hors périmètre de ce lot : CameraX, traitement photogrammétrique, upload serveur et base de données.

## Lot 02

Ce lot ajoute un modèle de données local simple pour préparer le stockage projet, sans introduire de base de données ni de dépendance lourde.

Éléments ajoutés :

- `domain/model/Project.kt` : représentation d’un projet terrain avec identifiant, nom, description, type de capture, qualité, date de création, appareil cible, nombre de photos et statut.
- `domain/model/PhotoItem.kt` : représentation minimale d’une future photo avec métadonnées temporelles, coordonnées optionnelles et indicateur de qualité.
- `domain/model/CaptureType.kt`, `CaptureQuality.kt`, `ProjectStatus.kt` : énumérations lisibles pour qualifier les projets.
- `data/ProjectRepository.kt` : repository en mémoire contenant des projets fictifs centralisés.
- `ProjectListScreen` consomme désormais des objets `Project` plutôt qu’une liste de chaînes.
- `ProjectDetailScreen` affiche les informations principales du projet sélectionné.
- La navigation transmet un `projectId` simple vers l’écran détail ; en cas d’identifiant absent ou inconnu, le repository fournit un projet fictif par défaut.

Hors périmètre de ce lot : Room, DataStore, CameraX, écriture réelle de `projects.json`, upload serveur et traitement photogrammétrique.

## Structure du projet

```text
.
├── app/                         # Module Android applicatif
│   └── src/main/java/com/thibaudremy/orthocapture/
│       ├── MainActivity.kt
│       ├── data/                # Repository local en mémoire
│       ├── domain/model/        # Modèles Project, PhotoItem et enums métier
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
