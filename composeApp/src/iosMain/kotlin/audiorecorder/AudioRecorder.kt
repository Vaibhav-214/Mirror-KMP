package audiorecorder

import platform.Foundation.NSData
import platform.posix.memcpy
import kotlinx.cinterop.*
import platform.AVFAudio.AVAudioQualityHigh
import platform.AVFAudio.AVAudioRecorder
import platform.AVFAudio.AVAudioRecorderDelegateProtocol
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionCategoryPlayAndRecord
import platform.AVFAudio.AVEncoderAudioQualityKey
import platform.AVFAudio.AVFormatIDKey
import platform.AVFAudio.AVNumberOfChannelsKey
import platform.AVFAudio.AVSampleRateKey
import platform.AVFAudio.setActive
import platform.CoreAudioTypes.kAudioFormatMPEG4AAC
import platform.Foundation.NSError
import platform.Foundation.NSURL



@OptIn(ExperimentalForeignApi::class,BetaInteropApi::class)
actual class AudioRecorder {

    private var audioRecorder: AVAudioRecorder? = null
    var delegateProtocol: AVAudioRecorderDelegateProtocol? = null

    private inline fun createRecorder(url: NSURL, error: CPointer<ObjCObjectVar<NSError?>>?): AVAudioRecorder {
        AVAudioSession.sharedInstance().apply {
            setCategory(AVAudioSessionCategoryPlayAndRecord, error)
            setActive(true, error)
        }
        val settings = mapOf<Any?, Any?>(
            AVFormatIDKey to kAudioFormatMPEG4AAC.toInt(),
            AVSampleRateKey to 12000,
            AVNumberOfChannelsKey to 1,
            AVEncoderAudioQualityKey to AVAudioQualityHigh
        )
        return url.usePinned {
            AVAudioRecorder(it.get(), settings, error)
        }
    }
    actual fun startRecording(localUrl: LocalAudioSourceUrl): Boolean {
        if (audioRecorder != null) {
            audioRecorder?.stop()
            audioRecorder = null
        }

         memScoped {
            val err = allocPointerTo<ObjCObjectVar<NSError?>>()

            createRecorder(url = localUrl.url, error = err.value).also {
                val error = err.value?.pointed?.value
                if (error == null) {

                    it.delegate = delegateProtocol
                    it.prepareToRecord()
                    it.record()

                    audioRecorder = it
                } else {
                    println("Error while creating audio recorder: ${error.code}, ${error.description}")
                }
            }
            return err.value?.pointed?.value == null
        }
    }

    actual fun stopRecording() {
        try {
            audioRecorder?.stop()
            audioRecorder = null
        }catch (e: Exception) {
            println("Exception in stop recording - $e")
        }

    }
}


@OptIn(ExperimentalForeignApi::class)
fun NSData.toByteArray(): ByteArray {
    return ByteArray(length.toInt()).apply {
        usePinned {
            memcpy(it.addressOf(0), bytes, length)
        }
    }
}