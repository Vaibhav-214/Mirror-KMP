package iosview_test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIView
import platform.UIKit.UIViewController

@OptIn(ExperimentalForeignApi::class)
@Composable
fun composeEntryPointWIthUiView(swiftUiView: () -> UIView) : UIViewController =
    ComposeUIViewController {
        Column (
            modifier = Modifier.fillMaxWidth()
        ){
            Text("UI In compose")

            //this will render swift ui code
            UIKitView(
                factory = swiftUiView,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
}

