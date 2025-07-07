package com.example.cheas_stoeckli.ui.components.Raclette

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.SubcomposeAsyncImage
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.domain.models.Raclette
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary
import com.example.cheas_stoeckli.utils.RotatingPlaceholder

@Composable
fun RacletteDetailDialog(
    raclette: Raclette,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = cardBackgroundPrimary,
            modifier = Modifier
              //  .wrapContentHeight()
                .fillMaxHeight(0.8f)
                .fillMaxWidth(0.9f)
        ) {
            Box {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    model = raclette.imgDownloadPath,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    loading = {
                        RotatingPlaceholder(drawableRes = R.drawable.cheesewheel_placeholder)
                    }
                )
            }
        }
    }
}
