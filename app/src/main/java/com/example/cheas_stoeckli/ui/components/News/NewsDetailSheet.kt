package com.example.cheas_stoeckli.ui.components.News

import android.Manifest
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.data.Fake.fakeNews
import com.example.cheas_stoeckli.domain.models.News
import com.example.cheas_stoeckli.ui.enums.NewsKind
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary
import com.example.cheas_stoeckli.ui.theme.loginButtonColor
import com.example.cheas_stoeckli.ui.viewModel.News.NewsDetailViewModel
import com.example.cheas_stoeckli.utils.RotatingPlaceholder
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("MissingPermission")
@Composable
fun NewsDetailSheet(
    news: News,
    onDismiss: () -> Unit,
    newsDetailViewModel: NewsDetailViewModel = koinViewModel()
) {

    val context = LocalContext.current


    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val mapsUrl by newsDetailViewModel.staticMapUrl.collectAsState()
    val fineLocPermission = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    val permissionGranted = fineLocPermission.status.isGranted

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
                    .fillMaxWidth(0.9f)
                    .verticalScroll(rememberScrollState())

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(cardBackgroundPrimary)
                ) {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 220.dp)
                            .padding(4.dp),
                        model = news.imgDownloadPath,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        loading = {
                            RotatingPlaceholder(drawableRes = R.drawable.cheesewheel_placeholder)

                        }
                    )
                }
                Spacer(Modifier.height(6.dp))
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    Text(
                        text = news.title,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(8.dp)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = news.text,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                        modifier = Modifier.padding(8.dp)
                    )
                    Spacer(Modifier.height(12.dp))
                    if (news.type == NewsKind.EVENTS) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Text(
                                text = "Wo muss ich hin?: ",
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black,
                            )
                            Text(
                                text = "\n${news.destination},\n${news.date}," +
                                        "\n${news.time}",
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                color = Color.Black,
                            )
                        }
                    }
                }
                Spacer(Modifier.height(12.dp))

                if (news.type == NewsKind.EVENTS) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp)
                            .background(cardBackgroundPrimary),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        SubcomposeAsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 260.dp)
                                .padding(4.dp),
                            model = mapsUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            loading = {
                                RotatingPlaceholder(drawableRes = R.drawable.cheesewheel_placeholder)
                            }
                        )
                        Button(
                            onClick = {
                                newsDetailViewModel.intentToGoogleMaps(
                                    context = context, destination = news.destination
                                )
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = loginButtonColor
                            ),
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .align(Alignment.TopEnd)
                                .offset(y = (-18).dp, x = -10.dp)
                        ) {
                            Text(
                                text = "In Google Maps öffnen",
                                fontSize = 14.sp,
                                color = Color.Black,
                                textAlign = TextAlign.Center,

                                )

                        }
                    }
                }
            }
        }

        LaunchedEffect(Unit) {
            if (news.type == NewsKind.EVENTS && !permissionGranted) {
                fineLocPermission.launchPermissionRequest()
            }
        }
        LaunchedEffect(permissionGranted) {
            if (permissionGranted && news.type == NewsKind.EVENTS) {
                Log.d("NewsDetailDialog", "Permission granted → Map laden")
                newsDetailViewModel.getMapUrl(news.destination)
            }
        }
        DisposableEffect(Unit) {
            onDispose {
                newsDetailViewModel.clearStaticMapsUrl()
            }
        }
    }
}

@Preview
@Composable
private fun Newspreview() {
    NewsDetailSheet(
        fakeNews,
        onDismiss = { TODO() },

        )
}