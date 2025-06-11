# Chäs Stöckli

Chäs Stöckli bietet eine Plattform in der sich Kunden über das vorhandene Käseangebot informieren können **bevor**
Sie den Laden betreten. 
 
Diese App richtet sich an alle Käseliebhaber in der Schweiz um Affoltern am Albis und darüber hinaus!


## Design 🎨
Füge hier am Ende die Screenshots deiner App ein.

<p>
  <img src="./img/screen1.png" width="200">
  <img src="./img/screen2.png" width="200">
  <img src="./img/screen3.png" width="200">
</p>


## Features

- [ ] Suche nach Kategorien, zb Käse aus Ziegen- oder Kuhmilch
- [ ] Integration von Google Maps zur Wegfindung
- [ ] Veranstaltungen und deren Eintragung in lokale Kalenderapps
- [ ] Favorisierung von Käsesorten
- [ ] Pushbenachrichtigung bei neuen Events oder einem aktuallisiertem Angebot

## Technischer Aufbau 🔧

- **MVVM-Architektur**
  - `ui/` enthält Composables und Screens
  - `viewmodel/` für ViewModel-Klassen
  - `data/` mit Repositories, RemoteDataSource (API) und LocalDataSource (Firebase)
  - `di/` für die  **Dependency Injection** mit Koin 


#### Projektaufbau

#### Datenspeicherung

- 🔄 **Firebase Firestore** für alle persistente Event- und Nutzerdaten (offline-fähig, automatische Synchronisation)
- 🔐 **Firebase Authentication** für Veranstalter-Login und geschützte Bereiche
- 🖼️ **Firebase Cloud Storage** für Bilder und Medieninhalte von Events


#### API Calls

- 📍 **Google Maps  / Google Directions API** für die Routenberechnung zum Laden


#### 3rd-Party Frameworks

- 💎 **Jetpack Compose** für UI
- ✳️ **Koin** für Dependency Injection
- 🌐 **Retrofit & OkHttp** für Netzwerk-Anfragen
- 🖼️ **Coil** zum asynchronen Laden von Bildern

