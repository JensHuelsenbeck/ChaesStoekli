package com.example.cheas_stoeckli.ui.components.News

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.cheas_stoeckli.domain.models.User
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary
import com.example.cheas_stoeckli.ui.theme.loginButtonColor


@Composable
fun NewsInformation(
    user: User?,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = cardBackgroundPrimary,
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .background(cardBackgroundPrimary)
            ) {
                Text(
                    text = "Hinweise zu den Ankündigungen",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(Modifier.height(8.dp))
                if (user?.permissonLevel == "1") {
                    Text(
                        text = "Du kannst auf die Ankündigungen tippen, um mehr Details zu sehen – \n " +
                                "inklusive einer Wegbeschreibung oder Routenplanung bei Veranstaltungen." +
                                "Dafür brauchen wir aber deine Zustimmung zur Standortbestimmung. " +
                                "Wir werden  dich danach fragen, wenn es nötig ist.",
                        fontSize = 16.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = "Dafür brauchen wir aber deine Zustimmung zur Standortbestimmung. " +
                                "Wenn es nötig sein sollte, fragen wir danach." +
                                " \nDie App wird aber auch ohne deine Erlaubnis wunderbar funktionieren",
                        fontSize = 16.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "Zusätzlich kannst du durch langes Drücken eine Ankündigung löschen " +
                                "– \n und über den Button unten rechts eine neue hinzufügen.",
                        fontSize = 16.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                } else {
                    Text(
                        text = "Du kannst auf die Ankündigungen tippen, um mehr Details zu sehen – \n" +
                                "inklusive einer Wegbeschreibung oder Routenplanung bei Veranstaltungen.\n",
                        fontSize = 16.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = "Dafür brauchen wir aber deine Zustimmung zur Standortbestimmung. " +
                                "Wenn es nötig sein sollte, fragen wir danach." +
                        " \nDie App wird aber auch ohne deine Erlaubnis wunderbar funktionieren",
                        fontSize = 16.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = loginButtonColor)
                ) {
                    Text(
                        text = "Verstanden \u2714",
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }

}