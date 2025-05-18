# Jetpack Compose Todo List Application
- Name: Saron Yitbareck
- ID: UGR/3774/15

This Android app demonstrates a Todo List built using Jetpack Compose, Retrofit, and Room. It fetches TODO items from the JSONPlaceholder API, caches data locally with Room, and provides a smooth, responsive UI with Compose and MVVM architecture.

---

## Features

- **Fetch Todos** from remote API (`https://jsonplaceholder.typicode.com/todos`) using Retrofit with Kotlin Coroutines
- **Local Caching** with Room database for offline access and faster loading
- **List Screen** showing todos with title and completion status, using Jetpack Compose `LazyColumn`
- **Detail Screen** showing full details of the selected todo item
- **Navigation** implemented with Jetpack Compose Navigation components
- **Error Handling** for network failures with cached data fallback and retry option
- **Data Synchronization** loads cached data immediately, then updates from the network

---

## Architecture

The app follows the **MVVM (Model-View-ViewModel)** architecture pattern:

- **Model**: Kotlin data classes,Repository, Room entities, Retrofit API service
- **ViewModel**: Handles UI state and business logic, exposes state to the UI
- **View**: Jetpack Compose UI components observing ViewModel state

---

## Tech Stack & Libraries

- Kotlin
- Jetpack Compose
- Retrofit with Kotlin Coroutines
- Room Database
- Jetpack Compose Navigation
- Android Architecture Components (ViewModel, LiveData/StateFlow)

---

## Project Structure

- `model/` — Data models & Room entities
- `network/` — Retrofit API interface and network setup
- `database/` — Room database and DAO
- `repository/` — Repository handling data from network and local DB
- `ui/` — Compose UI screens and components
- `viewmodel/` — ViewModels for List and Detail screens
- `navigation/` — Navigation graph for Compose

---

## How to Run

1. Clone the repo:
   ```
   git clone https://github.com/saron03/jetpack-compose-assignment-2.git
   ```
2. Open the project in Android Studio.
3. Build and run on an emulator or physical device with internet access.
4. The app will display cached todos if available, and refresh from the network automatically.

## License
This project is for educational purposes.

