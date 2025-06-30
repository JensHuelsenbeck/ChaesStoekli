package com.example.cheas_stoeckli.ui.components.News

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.SubcomposeAsyncImage
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.data.Fake.fakeNews
import com.example.cheas_stoeckli.domain.models.News
import com.example.cheas_stoeckli.ui.enums.NewsKind
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary
import com.example.cheas_stoeckli.ui.viewModel.NewsDetailViewModel
import com.example.cheas_stoeckli.utils.RotatingPlaceholder
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NewsDetailDialog(
    news: News,
    newsDetailViewModel: NewsDetailViewModel = koinViewModel()
) {
    val context = LocalContext.current

    val fineLocPermission = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = cardBackgroundPrimary,
            modifier = Modifier
                .fillMaxSize(0.9f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.9f)
                    .verticalScroll(rememberScrollState())

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                        .background(Color.Black)
                ) {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        model = news.imgDownloadPath,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        loading = {
                            RotatingPlaceholder(drawableRes = R.drawable.cheesewheel_placeholder)

                        }
                    )
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    text = news.title,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(4.dp)
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = news.text,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "Wo muss ich hin?: ",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(8.dp)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {

                    Text(
                        text = "${news.destination},\n${news.date},\n" +
                                "${news.time}",
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(Modifier.height(12.dp))
            }
        }




        LaunchedEffect(news.type) {
            if (news.type == NewsKind.EVENTS && !fineLocPermission.status.isGranted) {
                fineLocPermission.launchPermissionRequest()
            }
        }
        LaunchedEffect(fineLocPermission.status) {
            if (news.type == NewsKind.EVENTS && fineLocPermission.status.isGranted) {
                newsDetailViewModel.getMapUrl(news.destination)
            }
        }
    }
}

@Preview
@Composable
private fun Newspreview() {
    NewsDetailDialog(fakeNews)
}