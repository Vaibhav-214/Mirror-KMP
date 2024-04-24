package util

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIView
import platform.UIKit.UIViewController

//actual class LoginButton (
//    val createUIView: () -> UIView
//){
//    @OptIn(ExperimentalForeignApi::class)
//    @Composable
//    actual fun LoginButtonCompose() {
//        UIKitView(
//            factory =createUIView ,
//            modifier = Modifier.wrapContentWidth()
//        )
//    }
//
//    @OptIn(ExperimentalForeignApi::class)
//    fun MainViewController(
//        createUIView: () -> UIView
//    ):UIViewController = ComposeUIViewController {
//        UIKitView(
//            factory = createUIView,
//            modifier = Modifier.wrapContentWidth()
//        )
//    }
//
//
//}