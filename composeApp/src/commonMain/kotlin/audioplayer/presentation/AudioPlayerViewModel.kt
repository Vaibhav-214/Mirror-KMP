package audioplayer.presentation

import audioplayer.AudioPlayer
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AudioPlayerViewModel (
    private val audioPlayer: AudioPlayer
): ViewModel() {

    private val _playerState = MutableStateFlow<AudioPlayerState?>(null)
    val playerState = _playerState.asStateFlow()

    fun play() = viewModelScope.launch {
//        audioPlayer.play("")
    }

    fun pause() = viewModelScope.launch {
//        audioPlayer.pause()
    }

    fun stop() = viewModelScope.launch {
//        audioPlayer.stop()
    }


}

data class AudioPlayerState(
    val url: String,
    val totalTimeInSec: Long,
    val currentTimeInSec: Long,
    val isPlaying: Boolean
)