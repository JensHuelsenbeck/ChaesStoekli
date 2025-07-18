package com.example.cheas_stoeckli.ui.components.More

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DSGVODialog(
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        containerColor = cardBackgroundPrimary,
        sheetState = sheetState,
        scrimColor = Color.Black.copy(alpha = 0.8f)
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = cardBackgroundPrimary,
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = """ 
                            Stand: Juli 2025

                            1. Verantwortlicher

                            Chäs Stöckli AG
                            Zürichstrasse 112
                            8910 Affoltern am Albis
                            Schweiz

                            Telefon: +41 44 761 6194
                            E-Mail: mail@chaesstoeckli.ch

                            ⸻

                            2. Erhebung und Verarbeitung personenbezogener Daten

                            Die Nutzung dieser App ist grundsätzlich ohne Angabe personenbezogener Daten möglich.
                            Personenbezogene Daten werden nur verarbeitet, wenn dies zur Nutzung der App erforderlich ist.
                            Bei Anmeldung über Google werden Ihre E-Mail-Adresse sowie – sofern von Ihnen angegeben – Ihr Anzeigename an uns übermittelt.

                            ⸻

                            3. Zweck der Datenverarbeitung

                            Die Verarbeitung erfolgt zu folgenden Zwecken:
                            	•	Anmeldung über Google (Firebase Authentication)
                            	•	Speichern und Abrufen von Inhalten (Firebase Firestore / Storage)
                            	•	Anzeigen von Karten und Routen (Google Maps / Static Maps API)
                            	•	Kontaktaufnahme über Telefon oder E-Mail (nur durch Nutzeraktion)

                            ⸻

                            4. Rechtsgrundlage der Verarbeitung

                            Die Datenverarbeitung erfolgt auf Grundlage von:
                            	•	Art. 6 Abs. 1 lit. b DSGVO (Vertragserfüllung – z.B. Bereitstellung von App-Funktionen)
                            	•	Art. 6 Abs. 1 lit. a DSGVO (Einwilligung – z.B. bei freiwilliger Kontaktaufnahme)

                            ⸻

                            5. Empfänger und Drittanbieter

                            Für bestimmte Funktionen verwenden wir Dienste der Google Ireland Limited, Gordon House, Barrow Street, Dublin 4, Irland:
                            	•	Firebase Authentication (Login)
                            	•	Firebase Firestore (Datenspeicherung)
                            	•	Firebase Storage (Bildspeicherung)
                            	•	Google Maps & Static Maps API (Kartendarstellung)

                            Weitere Informationen zum Datenschutz bei Google:
                            📎 https://policies.google.com/privacy

                            ⸻

                            6. Datenübertragung außerhalb der EU

                            Daten können auf Servern außerhalb der EU (z.B. in den USA) verarbeitet werden.
                            Google verwendet zur Absicherung Standardvertragsklauseln gemäß Art. 46 DSGVO.

                            ⸻

                            7. Speicherdauer

                            Personenbezogene Daten werden nur so lange gespeichert, wie dies zur Nutzung der App erforderlich ist.
                            Daten können vom Nutzer jederzeit gelöscht werden (z.B. durch Abmeldung oder Löschung des Accounts).

                            ⸻

                            8. Rechte der betroffenen Personen

                            Als betroffene Person haben Sie folgende Rechte:
                            	•	Auskunft über gespeicherte Daten (Art. 15 DSGVO)
                            	•	Berichtigung unrichtiger Daten (Art. 16 DSGVO)
                            	•	Löschung (Art. 17 DSGVO)
                            	•	Einschränkung der Verarbeitung (Art. 18 DSGVO)
                            	•	Datenübertragbarkeit (Art. 20 DSGVO)
                            	•	Widerspruch gegen die Verarbeitung (Art. 21 DSGVO)

                            Kontaktieren Sie uns hierzu unter: mail@chaesstoeckli.ch

                            ⸻

                            9. Beschwerderecht

                            Sie haben das Recht, sich bei einer Datenschutzaufsichtsbehörde der EU zu beschweren, wenn Sie der Ansicht sind, dass die Verarbeitung Ihrer personenbezogenen Daten gegen die DSGVO verstößt.

                        """.trimIndent(),
                    color = Color.Black,
                    fontSize = 15.sp
                )
            }
        }
    }
}
