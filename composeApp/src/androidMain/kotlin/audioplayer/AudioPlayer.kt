package audioplayer

import android.media.MediaPlayer
import audiorecorder.LocalAudioSourceUrl

actual class AudioPlayer {
    private var mediaPlayer: MediaPlayer? = null

    actual fun createPlayer(url: LocalAudioSourceUrl) {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(url.url)
            prepare()
        }
    }
    actual fun play(){
        mediaPlayer?.start()
    }

    actual fun pause() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
        }
    }

    actual fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}