package com.example.cheas_stoeckli.data.Fake

import com.example.cheas_stoeckli.domain.models.News
import com.example.cheas_stoeckli.ui.enums.NewsKind

val newsList = listOf(
    News(
        title = "Sommeröffnungszeiten",
        text = "Ab dem 1. Juli gelten unsere Sommeröffnungszeiten. Wir haben täglich von 11:00 bis 22:00 Uhr für euch geöffnet!",
        imgDownloadPath = "https://picsum.photos/200",
        destination = "",
        date = "",
        time = "",
        type = NewsKind.NEWS
    ),
    News(
        title = "Käseabend am See",
        text = "Genießt einen romantischen Abend mit Käseplatten und Wein direkt am Seeufer. Musik, Kerzenschein und guter Geschmack.",
        imgDownloadPath = "https://picsum.photos/200",
        destination = "Seepromenade 12, 34567 Käsetal",
        date = "20.07.2025",
        time = "19:30",
        type = NewsKind.EVENTS
    ),
    News(
        title = "Letzte Chance auf Rabatt!",
        text = "Nur noch heute 10% Frühbucherrabatt auf unser großes Sommer-Fondue-Event sichern!",
        imgDownloadPath = "https://picsum.photos/200",
        destination = "",
        date = "",
        time = "",
        type = NewsKind.REMINDER
    ),
    News(
        title = "Wein & Käse Verköstigung",
        text = "Am Wochenende laden wir zur exklusiven Käse- und Weinverkostung mit Harri Winzer ein. Anmeldung erforderlich!",
        imgDownloadPath = "https://picsum.photos/200",
        destination = "Weingut Harri, Rebenweg 8, 67890 Käseland",
        date = "28.06.2025",
        time = "16:00",
        type = NewsKind.EVENTS
    ),
    News(
        title = "Chäs & Chill – Afterwork Edition",
        text = "Jeden Donnerstag ab 18 Uhr: Genieße mit Kolleg:innen eine kleine Käseplatte und ein Glas Wein zum Feierabend.",
        imgDownloadPath = "https://picsum.photos/200",
        destination = "Altmarkt 5, 12345 Käseburg",
        date = "04.07.2025",
        time = "18:00",
        type = NewsKind.EVENTS
    ),
    News(
        title = "Neue Käsesorte eingetroffen!",
        text = "Ab sofort exklusiv bei uns: der Trüffel-Morbier – cremig, aromatisch und perfekt zum Raclette.",
        imgDownloadPath = "https://picsum.photos/200",
        destination = "",
        date = "",
        time = "",
        type = NewsKind.NEWS
    ),
    News(
        title = "Nicht vergessen: Anmeldung schließt heute!",
        text = "Letzte Erinnerung: Heute ist der letzte Tag zur Anmeldung für das große Sommer-Fondue-Event.",
        imgDownloadPath = "https://picsum.photos/200",
        destination = "",
        date = "19.06.2025",
        time = "22:00",
        type = NewsKind.REMINDER
    )
)

val fakeNews: News = News(
    title = "Nicht vergessen: Anmeldung schließt heute!",
    text = "Letzte Erinnerung: Heute ist der letzte Tag zur Anmeldung für das große Sommer-Fondue-Event. Denn wer nicht mit dabei ist, verpasst das beste Event das es jemals gegeben hat! ",
    imgDownloadPath = "https://picsum.photos/200",
    destination = "Rostock",
    date = "19.06.2025",
    time = "22:00",
    type = NewsKind.REMINDER
)