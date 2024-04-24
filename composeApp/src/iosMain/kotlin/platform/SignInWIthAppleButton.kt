package platform

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import util.getKoinInstance

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun NativeSignInButton() {
    val viewProvider: ViewProvider = getKoinInstance()

    UIKitView(
        modifier = Modifier.wrapContentWidth(),
        factory = viewProvider::provideView,
    )
}