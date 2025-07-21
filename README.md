# Chäs Stöckli - Wenn Käse der Himmel wäre 🧀


Chäs Stöckli bietet eine Plattform in der sich Kunden über das vorhandene Käseangebot informieren können **bevor**
Sie den Laden betreten. 
 
Diese App richtet sich an alle Käseliebhaber in der Schweiz um Affoltern am Albis und darüber hinaus!


## Design 🎨

<p>
  <img src="./img/chässtöckli_login.png" width="200">
  <img src="./img/readne2.png" width="200">
  <img src="./img/readme3.png" width="200">
  <img src="./img/readme4.png" width="200">
</p>


## Features ✅

- [ ] Informationsfindung über das Käse- und sonstige Sortiment.
- [ ] Favorisierung von Käsesorten
- [ ] Filterung per Kategorien(zb Käse aus Ziegen- oder Kuhmilch) und Favoriten .
- [ ] Integration von Google Maps zur Wegfindung.


## Technischer Aufbau 🔧

- **MVVM-Architektur**
  - `ui/` enthält Composables und Screens
  - `viewmodel/` für ViewModel-Klassen
  - `data/` mit Repositories, RemoteDataSource (API) und LocalDataSource (Firebase)
  - `di/` für die  **Dependency Injection** mit Koin 


#### Projektaufbau

Das Projekt folgt einer klaren MVVM-Architektur mit folgender Verzeichnisstruktur:

📦 Cheas_Stoeckli
┣ 📂 ui              → Composables
┣ 📂 screens         → Screens
┣ 📂 viewmodel       → ViewModels für Zustand & Logik
┣ 📂 data
┃ ┣ 📂 repository    → Repositories zur Datenverarbeitung
┃ ┣ 📂 remote        → Zugriff auf Firebase Firestore, Auth, Storage
┃ ┗ 📂 model         → Datenklassen (z.B. Cheese, News)
┣ 📂 di              → Dependency Injection via Koin
┣ 📂 util            → Hilfsfunktionen 
┣ 📂 navigation      → Routendefinition für Navigation


#### Datenspeicherung

- 🔄 **Firebase Firestore** für alle persistenten Event- ,Nutzer- und Käsedaten (offline-fähig, automatische Synchronisation)
- 🔐 **Firebase Authentication** für individuelle Nutzer- und Adminaccounts 
- 🖼️ **Firebase Cloud Storage** für Bilder und Medieninhalte von Events


#### API Calls

- 📍 **Google Directions API** für die Routenberechnung zum Laden
- 📍 **Google Static Maps API** zum anzeigen einer Karte mit Route


#### 3rd-Party Frameworks

- 💎 **Jetpack Compose** für UI
- 🔀 **Navigation Compose** für Bildschirm-Navigation
- ❄️ **Koin** für Dependency Injection
- 🔄 **Kotlin Coroutines** & **Flow** für Asynchronität und Datenströme
- 🧠 **ViewModel (Lifecycle)** zur Zustandshaltung
- 🌐 **Retrofit** & **OkHttp** für Netzwerk-Anfragen
- 🖼️ **Coil** zum asynchronen Laden von Bildern
- 🔥 **Firebase** für:
  - Authentifizierung (Google Sign-In)
  - Cloud Firestore (Datenhaltung)
  - Cloud Storage (Bilder)
- 📦 **Material Icons** für intuitive UI-Symbole
