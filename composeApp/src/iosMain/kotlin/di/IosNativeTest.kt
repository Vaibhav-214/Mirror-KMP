package di

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import iosview_test.composeEntryPointWIthUiView
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import platform.MapKit.MKMapView
import platform.UIKit.UIViewController

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun TestingCommonView() {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("UI In compose")

            UIKitView(
                factory = { MKMapView() },
                modifier = Modifier.align(Alignment.CenterHorizontally).size(200.dp)
            )

        }

}

