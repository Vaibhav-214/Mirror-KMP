package audioplayer

import audiorecorder.LocalAudioSourceUrl
import kotlin.time.Duration
import kotlin.time.DurationUnit

expect class AudioPlayer {

    fun createPlayer(url: LocalAudioSourceUrl)
    fun play()
    fun stop()
    fun pause()
}