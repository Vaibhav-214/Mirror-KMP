package audiorecorder

expect class AudioRecorder {
    fun startRecording(localUrl: LocalAudioSourceUrl): Boolean

    fun stopRecording()
}
