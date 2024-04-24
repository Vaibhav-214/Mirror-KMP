package audiorecorder

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import util.getKoinInstance
import java.io.File

actual class LocalAudioSourceUrl(
    val url: String
) {
    actual fun getUrlAsString(): String {
        return url.toString()
    }


}

actual fun toLocalAudioSourceUrl(url: String): LocalAudioSourceUrl? {
    return try {
//        val androidUrl = Uri.parse(url)
        LocalAudioSourceUrl(url)
    }catch (e: Exception) {
        println("Failed to transform $url to AndroidURI object")
        null
    }
}

actual fun createFile() : LocalAudioSourceUrl {
//    val path = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RECORDINGS)
//    } else {
//        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//    }
    val context = getKoinInstance<Context>()


    val path = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        Environment.getExternalStorageDirectory()
            .toString() + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator + "recording_" + System.currentTimeMillis()
            .toString() + ".m4a"
    } else {
        "${context.externalCacheDir?.absolutePath}/recording_${System.currentTimeMillis()}.m4a"
    }


    return LocalAudioSourceUrl(path)

}

