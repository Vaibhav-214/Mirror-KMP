package audiorecorder

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import android.os.Environment
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.File

actual class AudioRecorder() : KoinComponent {

    val context: Context by inject()

    private var mediaRecorder: MediaRecorder? = null


    actual fun startRecording(localUrl: LocalAudioSourceUrl): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            mediaRecorder = MediaRecorder(context)
        } else {
            mediaRecorder = MediaRecorder()
        }


         return try {
             mediaRecorder?.apply {
                 setAudioSource(MediaRecorder.AudioSource.MIC)
                 setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                 setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                 setOutputFile(localUrl.url)
                 prepare()
                 start()
             }
             true
        } catch (e: Exception) {
            e.printStackTrace()
             false
        }

    }

    actual fun stopRecording() {
        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null
    }
}