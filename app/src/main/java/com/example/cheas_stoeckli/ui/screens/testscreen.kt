package com.example.cheas_stoeckli.ui.screens

import android.Manifest
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalPermissionsApi::class)
@RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
@Composable
fun LocationTestScreen() {
    val context = LocalContext.current
    val permission = rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION)
    var resultText by remember { mutableStateOf("Noch nichts gestartet") }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = {
            if (permission.status.isGranted) {
                val fused = LocationServices.getFusedLocationProviderClient(context)
                CoroutineScope(Dispatchers.Main).launch {
                    try {
                        val fresh = fused.getCurrentLocation(
                            com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY,
                            com.google.android.gms.tasks.CancellationTokenSource().token
                        ).await()

                        if (fresh != null) {
                            resultText = "FRESH: ${fresh.latitude}, ${fresh.longitude}"
                            Log.d("LocationTest", "✅ Fresh location: ${fresh.latitude}, ${fresh.longitude}")
                        } else {
                            resultText = "Fehler: Frischer Standort war null"
                            Log.e("LocationTest", "❌ Fresh location null")
                        }
                    } catch (e: Exception) {
                        resultText = "Fehler: ${e.message}"
                        Log.e("LocationTest", "❌ Fehler bei Standortabfrage: ${e.message}")
                    }
                }
            } else {
                permission.launchPermissionRequest()
            }
        }) {
            Text("Standort abfragen")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(resultText)
    }
}