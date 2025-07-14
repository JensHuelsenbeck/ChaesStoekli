package com.example.cheas_stoeckli.ui.components.More

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.ui.viewModel.More.MoreViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OpenCloseCard(
    viewModel: MoreViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val isOpen by viewModel.isOpenNow.collectAsState()


    Card(
        modifier = Modifier.height(380.dp),
        shape = RoundedCornerShape(12.dp)
    ) {

        Image(
            painter = painterResource(
                if(isOpen) R.drawable.laden_ist_offe else R.drawable.laden_ist_zue
            ),
            contentDescription = "",

        )

    }
}