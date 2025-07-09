package com.example.cheas_stoeckli.ui.components.Raclette

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.domain.models.Raclette
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary
import com.example.cheas_stoeckli.utils.RotatingPlaceholder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RacletteDetailDialog(
    raclette: Raclette,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet (
        onDismissRequest = { onDismiss() },
        containerColor = cardBackgroundPrimary,
        sheetState = sheetState,
        scrimColor = Color.Black.copy(alpha = 0.8f)
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = cardBackgroundPrimary,
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .fillMaxWidth()
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
