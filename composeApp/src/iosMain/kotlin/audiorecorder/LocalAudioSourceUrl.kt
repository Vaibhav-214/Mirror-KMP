package audiorecorder

import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask
import platform.Foundation.URLByAppendingPathComponent
import util.DateTimeUtil

actual class LocalAudioSourceUrl(
    val url: NSURL
) {
    actual fun getUrlAsString(): String {
        return url.toString()
    }
}

actual fun toLocalAudioSourceUrl(url: String) : LocalAudioSourceUrl? {
    return try {
        val iosUrl = NSURL(string = url)
        println("iosUrl - ${iosUrl.absoluteURL}")
        LocalAudioSourceUrl(iosUrl)
    }catch (e: Exception) {
        println("Failed to transform $url to NSURL $e")
        null
    }
}

actual fun createFile(): LocalAudioSourceUrl {
    val paths = NSFileManager.defaultManager.URLsForDirectory(NSDocumentDirectory, NSUserDomainMask) as List<NSURL>
    val iosUrl = paths[0].URLByAppendingPathComponent(
        pathComponent = "${DateTimeUtil.generateTimestampForFilename()}.m4a"
    )
    if (iosUrl != null) {
        return LocalAudioSourceUrl(iosUrl)
    }else {
        println("Null NSURL generated")
        return LocalAudioSourceUrl(NSURL())
    }
}