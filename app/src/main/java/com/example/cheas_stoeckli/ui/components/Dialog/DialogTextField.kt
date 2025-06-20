package com.example.cheas_stoeckli.ui.components.Dialog

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DialogTextField(
    usedString: MutableState<String>,
    label: String,
    placeholder: String,
    maxLines: Int,
    minLines: Int,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = usedString.value,
        onValueChange = { usedString.value = it },
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        shape = RoundedCornerShape(12.dp),
        maxLines = maxLines,
        minLines = minLines,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            cursorColor = Color.Black,
            focusedTextColor = Color.Black,
            unfocusedLabelColor = Color.Black.copy(alpha = 0.5f),
            focusedLabelColor = Color.Black,
            focusedPlaceholderColor = Color.Black.copy(alpha = 0.5f),
            unfocusedTextColor = Color.Black,
        )
    )
    Spacer(Modifier.height(8.dp))
}