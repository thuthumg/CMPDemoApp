# CMPDemoApp

CMPDemoApp is a **Compose Multiplatform** demo application showcasing a complete mobile architecture for both **Android and iOS** using **shared business logic and UI**. It includes essential mobile features like local storage, network communication, authentication, offline support, and camera usage — all built using modern tools and best practices.

---

## Features

- Authentication Flow (Login)
- Home Screen
- Sample List Screen
- Detail Screen
- Settings Screen
- Camera Access
- Online & Offline Support
- Shared UI and Logic across Android & iOS

---

## Architecture

CMPDemoApp follows **Clean Architecture** with strict separation of concerns and modular layers:

- **Presentation Layer**  
  Jetpack Compose UI (shared) with **MVI pattern** for predictable state management.

- **Domain Layer**  
  Platform-independent business logic and use cases.

- **Data Layer**  
  Abstracted repositories using:
  - **Ktor** for API communication
  - **SQLDelight** for type-safe local database access

- **Dependency Injection**  
  Powered by **Koin**, configured for multiplatform usage.

---

## Tech Stack

| Layer                | Tools Used                           |
|----------------------|--------------------------------------|
| UI                   | Jetpack Compose (Multiplatform)      |
| Architecture         | Clean Architecture + MVI             |
| Dependency Injection | [Koin](https://insert-koin.io)       |
| Networking           | [Ktor](https://ktor.io)              |
| Local Database       | [SQLDelight](https://cashapp.github.io/sqldelight/) |
| State Management     | Kotlin Coroutines + Flow             |
| Shared Logic         | Kotlin Multiplatform (KMP)           |

---

##Purpose

This app is a **learning project** built to explore and demonstrate:

- Clean architecture principles in a multiplatform environment
- Building reusable UI with **Jetpack Compose Multiplatform**
- Sharing business logic and storage layers between Android and iOS
- Real-world app patterns including auth, camera, and offline support
