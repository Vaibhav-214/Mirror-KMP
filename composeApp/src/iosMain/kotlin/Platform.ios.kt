import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource

//class IOSPlatform: Platform {
//    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
//}

actual fun getPlatform(): String = "IOS"

private val cache: MutableMap<String,Font> = mutableMapOf()
@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun font(name :String, res : String, weight: FontWeight, style : FontStyle) : Font {
    return cache.getOrPut(res){
        val byteArray = runBlocking {
            resource("font/$res.ttf").readBytes()
        }
        Font(res,byteArray,weight,style)
    }
}
