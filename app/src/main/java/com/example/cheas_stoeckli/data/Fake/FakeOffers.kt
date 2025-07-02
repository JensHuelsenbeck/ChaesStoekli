package com.example.cheas_stoeckli.data.Fake

import com.example.cheas_stoeckli.domain.models.Offer
import com.example.cheas_stoeckli.ui.enums.OfferKind

val offerList = listOf(
    Offer(
        title = "Herzhafte Käse- / Fleischplatten",
        text = """
Frisch gerichtete Apéroplatten mit aufgeschnittenem Käse, wahlweise auch mit Fleisch garniert.

Unsere aufgeschnittenen Platten sind jeweils komplett von Hand mit viel Liebe und Arbeit gestaltet.

Wir halten es uns deshalb vor, aus Kapazitätsgründen die Platten nur in Absprache und mit genügend Vorlauf zu ermöglichen.
        """,
        bottomText = "Deshalb: Je früher desto besser!",
        imgDownloadPath = "https://picsum.photos/200",
        imgPath = "",
        type = OfferKind.ALLGEMEIN,
    ),

    Offer(
        title = "Täglich frische Milchprodukte",
        text = """
Unsere Milch stammt direkt vom Hof und wird täglich frisch verarbeitet.

Ob Joghurt, Rahm oder Butter – bei uns finden Sie Qualität ohne Umwege.
        """,
        bottomText = "Nur solange der Vorrat reicht.",
        imgDownloadPath = "",
        imgPath = "",
        type = OfferKind.ALLGEMEIN,
    ),

    Offer(
        title = "Geschenkkörbe individuell gestalten",
        text = """
Wir stellen für Sie liebevoll gestaltete Geschenkkörbe zusammen – ideal für Geburtstage, Firmenanlässe oder als kleines Dankeschön.

Sprechen Sie uns einfach an und wir kümmern uns um den Rest.
        """,
        imgDownloadPath = "https://picsum.photos/400/226 ",
        imgPath = "",
        type = OfferKind.ALLGEMEIN,
    ),

    Offer(
        title = "Saisonale Spezialitäten vom Hof",
        text = """
Je nach Jahreszeit bieten wir besondere Produkte an: von feinen Kürbissen im Herbst bis zu frischen Beeren im Sommer.

Besuchen Sie uns regelmäßig und entdecken Sie unsere saisonalen Highlights.
        """,
        bottomText = "",
        imgDownloadPath = "",
        imgPath = "",
        type = OfferKind.ALLGEMEIN,
    ),

    Offer(
        title = "Käseverkostung mit Hofbesichtigung",
        text = """
Erleben Sie unseren Käse direkt vor Ort und werfen Sie einen Blick hinter die Kulissen der Produktion.

Die Verkostung ist kostenlos, eine Anmeldung ist jedoch erforderlich.
        """,
        bottomText = "Anmeldung via Telefon oder vor Ort möglich.",
        imgDownloadPath = "https://picsum.photos/400/225 ",
        imgPath = "",
        type = OfferKind.ALLGEMEIN,
    ),
    Offer(
        title = "Hier gehts zum Käsesortiment",
        text = "",
        bottomText = "",
        imgDownloadPath = "",
        imgPath = "",
        type = OfferKind.KAESE,
    ),
    Offer(
        title = "Hier gehts zum Raclettesortiment",
        text = "",
        bottomText = "",
        imgDownloadPath = "",
        imgPath = "",
        type = OfferKind.RACLETTE,
    ),
    Offer(
        title = "Hier gehts zum Fonduesortiment",
        text = "",
        bottomText = "",
        imgDownloadPath = "",
        imgPath = "",
        type = OfferKind.FONDUE,
    ),
)
val fakeOffer = Offer(
    title = "Herzhafte Käse- / Fleischplatten",
    text = """
Frisch gerichtete Apéroplatten mit aufgeschnittenem Käse, wahlweise auch mit Fleisch garniert.
            
Unsere aufgeschnittenen Platten sind jeweils komplett von Hand mit viel Liebe und Arbeit gestaltet.
            
Wir halten es uns deshalb vor, aus Kapazitätsgründen die Platten nur in Absprache und mit genügen Vorlauf zu ermöglichen.
            """,
    bottomText = "Deshalb: Je früher desto besser!",
    imgDownloadPath = "https://picsum.photos/400/225 ",
    imgPath = "",
    type = OfferKind.ALLGEMEIN,
)