import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

//interface Platform {
//    val name: String
//}
enum class Platform {
    ANDROID,
    IOS
}

expect fun getPlatform(): String

@Composable
expect fun font(name: String, res: String, weight: FontWeight, style: FontStyle): Font