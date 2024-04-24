
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIView
import platform.UIKit.UIViewController

@OptIn(ExperimentalForeignApi::class)
fun MainViewController(
    createUIView: () -> UIView
):UIViewController = ComposeUIViewController {
    App(swiftUIView = {
        UIKitView(
            factory = createUIView,
            modifier = Modifier.wrapContentWidth()
        )
    }
    )
}



