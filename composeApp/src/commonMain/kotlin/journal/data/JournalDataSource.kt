package journal.data

import journal.domain.JournalEntryModel

interface JournalDataSource {

    suspend fun insertJournalEntry(journalEntryModel: JournalEntryModel): Boolean

    suspend fun getJournalEntryById(id: Long): JournalEntryModel?

    suspend fun getAllJournals():List<JournalEntryModel>
}