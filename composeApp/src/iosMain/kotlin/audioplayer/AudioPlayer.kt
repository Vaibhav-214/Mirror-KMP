package audioplayer

import audiorecorder.LocalAudioSourceUrl
import audiorecorder.toLocalAudioSourceUrl
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.allocPointerTo
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.pointed
import kotlinx.cinterop.value
import platform.AVFAudio.AVAudioPlayer
import platform.AVFAudio.AVAudioPlayerDelegateProtocol
import platform.Foundation.NSError
import platform.Foundation.NSURL
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
actual class AudioPlayer {

    private var player: AVAudioPlayer? = null
    var delegate: AVAudioPlayerDelegateProtocol? = null

    private inline fun createAVPlayer(url: NSURL) {
        memScoped {
            val err = allocPointerTo<ObjCObjectVar<NSError?>>()
            player = AVAudioPlayer(url, err.value)
            val error = err.value?.pointed?.value
            if (error != null) {
                println("Error while creating AVAudioPlayer: ${error.code}, ${error.description}")
            }
        }
        player?.delegate = delegate
        player?.volume = 10.0f
//            player.setCurrentTime(startTime.toDouble(DurationUnit.SECONDS))
        player?.prepareToPlay()

    }

    actual fun createPlayer(url: LocalAudioSourceUrl) {
        createAVPlayer(url.url)
    }

    actual fun play() {
//        createPlayer(url.url)?.let {
//            println("play current position - $startTime")
        player?.play()
//        }
    }


//    actual fun duration(url: LocalAudioSourceUrl, unit: DurationUnit): Duration {
//        var duration = Duration.ZERO
//        createPlayer(url.url)?.let { player ->
//            duration = Duration.convert(
//                value = player.duration,
//                sourceUnit = DurationUnit.SECONDS,
//                targetUnit = unit
//            ).toDuration(unit = unit)
//        }
//        return duration
//    }

    actual fun pause() {
        player?.pause()
    }


    actual fun stop() {
        player?.delegate = null
        player?.stop()
        player = null
    }
}