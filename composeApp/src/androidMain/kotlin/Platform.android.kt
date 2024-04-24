import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

//class AndroidPlatform : Platform {
//    override val name: String = "Android ${Build.VERSION.SDK_INT}"
//}

actual fun getPlatform(): String = "ANDROID"

@SuppressLint("DiscouragedApi")
@Composable
actual fun font(name :String, res : String, weight: FontWeight, style :  FontStyle) : Font {
    val context = LocalContext.current
    val id = context.resources.getIdentifier(res, "font", context.packageName)
    if(id != 0){
        return Font(id, weight, style)
    } else {
        throw Resources.NotFoundException("Could not find the font resource with name: $res")
    }
}
