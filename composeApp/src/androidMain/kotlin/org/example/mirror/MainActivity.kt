package org.example.mirror

import App
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import di.initKoin
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        initKoin() {
            androidContext(applicationContext)
        }

        setContent {

            var granted by remember { mutableStateOf(false) }

            val multiplePermission = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
            )

            val launcherMultiplePermissions = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) {  permissionsMap ->
                val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
                if (areGranted) {
                    granted = true
                } else {
                    println("ghar ja")
                }
            }

            if (!granted) {
                LaunchedEffect(key1 = Unit) {
                    launcherMultiplePermissions.launch(multiplePermission)
                }
            } else {

            }

            App(swiftUIView = {})
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
//    App()
}