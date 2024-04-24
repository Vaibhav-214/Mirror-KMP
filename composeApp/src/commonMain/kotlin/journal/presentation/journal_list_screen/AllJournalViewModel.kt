package journal.presentation.journal_list_screen

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import journal.data.JournalDataSource
import journal.domain.toJournalDetailState
import journal.presentation.journal_detail_screen.JournalDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllJournalViewModel(
    private val journalDataSource: JournalDataSource
): ViewModel() {
    private val _uiState = MutableStateFlow(listOf<JournalDetailState>())
    val uiState = _uiState.asStateFlow()


     fun getAllJournals() = viewModelScope.launch {
      _uiState.value =  journalDataSource.getAllJournals().map { it.toJournalDetailState() }
    }
}
