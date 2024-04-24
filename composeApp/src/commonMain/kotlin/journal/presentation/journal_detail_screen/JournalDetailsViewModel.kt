package journal.presentation.journal_detail_screen

import audioplayer.AudioPlayer
import audiorecorder.AudioRecorder
import audiorecorder.createFile
import audiorecorder.toLocalAudioSourceUrl
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import journal.data.JournalDataSource
import journal.domain.toJournalDetailState
import journal.domain.toJournalEntryModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.TimeSource

class JournalDetailsViewModel(
    private val journalDataSource: JournalDataSource,
    private val audioRecorder: AudioRecorder,
    private val audioPlayer: AudioPlayer
):ViewModel() {

    private val _uiState = MutableStateFlow<JournalDetailState>(JournalDetailState())
    val uiState = _uiState.asStateFlow()

    private val _audioState = MutableStateFlow(AudioState())
    val audioState = _audioState.asStateFlow()

    private var urlPath = ""

    private var timeSpan = TimeSource.Monotonic.markNow()

    private var elapsed = Duration.ZERO

    private var isCreated = false

    /**
     * Function to call when opening a journal from journal list screen
     */
    fun updateJournalDetails(id: Long) = viewModelScope.launch {
        journalDataSource.getJournalEntryById(id)?.let {
            _uiState.value = it.toJournalDetailState()
        }
    }

    fun updateTitle(title: String) {
        _uiState.value = _uiState.value.copy(title = title)
    }

    fun updateTextContent(content: String) {
        _uiState.value = _uiState.value.copy(textContent = content)
    }

    fun saveJournal() = viewModelScope.launch {
        //assuming that since I am using INSERT OR REPLACE in query if I pass null id , it will automatically
        //create a new one
        journalDataSource.insertJournalEntry(
            _uiState.value.toJournalEntryModel()
        )
    }


    //testing code not supposed to be here

    fun startRecording() = viewModelScope.launch {
        val audioFileUrl = createFile()
        urlPath = audioFileUrl.getUrlAsString()

        if (audioRecorder.startRecording(audioFileUrl)) {
            timeSpan = TimeSource.Monotonic.markNow()
            _audioState.update {
                it.copy(
                    url = urlPath,
                    isRecording = true
                )
            }
        } else {
            println("error in recording")
        }


    }

    fun stopRecording() = viewModelScope.launch {
        elapsed = timeSpan.elapsedNow()
        audioRecorder.stopRecording()

        _audioState.update {
            it.copy(
                isRecording = false,
                duration = elapsed
            )
        }

        println("url - $urlPath")
        toLocalAudioSourceUrl(urlPath)?.let {


//            println("actual duration - ${audioPlayer.duration(it, DurationUnit.SECONDS)} - calculated duration - $elapsed")

        }


    }

    fun playRecording()  = viewModelScope.launch {


        toLocalAudioSourceUrl(urlPath)?.let {
            if (!isCreated) {
                audioPlayer.createPlayer(it)
                isCreated = true
            }
            timeSpan = TimeSource.Monotonic.markNow()
            audioPlayer.play()
            _audioState.update {
                it.copy(
                    isPlaying = true,
                    )
            }
        }
    }

    fun pauseAudio() {
        audioPlayer.pause()
    }

    fun stopAudio() = viewModelScope.launch {
        audioPlayer.stop()
        elapsed += timeSpan.elapsedNow()
        _audioState.update {
            it.copy(
                isPlaying = false,
                currentPosition = elapsed
                )
        }
    }

}

data class AudioState(
    val isRecording: Boolean = false,
    val isPlaying: Boolean = false,
    val url: String = "",
    val duration: Duration = Duration.ZERO,
    val currentPosition: Duration= Duration.ZERO
)
