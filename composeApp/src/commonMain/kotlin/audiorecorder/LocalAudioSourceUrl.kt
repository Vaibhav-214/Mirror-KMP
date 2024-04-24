package audiorecorder

expect class LocalAudioSourceUrl {
    fun getUrlAsString(): String
}


expect fun toLocalAudioSourceUrl(url: String): LocalAudioSourceUrl?

expect fun createFile() : LocalAudioSourceUrl