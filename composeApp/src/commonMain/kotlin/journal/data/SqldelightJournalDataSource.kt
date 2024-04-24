package journal.data

import journal.domain.JournalEntryModel
import journal.domain.toJournalEntry
import org.example.mirror.database.MirrorLocalDatabase
import util.DateTimeUtil

class SqldelightJournalDataSource(database: MirrorLocalDatabase) : JournalDataSource {

    private val queries = database.journalQueries

    override suspend fun insertJournalEntry(journalEntryModel: JournalEntryModel):Boolean {
        return try {
            queries.insertJournal(
                id = journalEntryModel.id,
                title = journalEntryModel.title,
                content_type = journalEntryModel.contentType,
                text_content = journalEntryModel.textContent,
                image_url = journalEntryModel.imageUrl,
                audio_url = journalEntryModel.audioUrl,
                tags = journalEntryModel.tags,
                created_at = DateTimeUtil.toEpochMillis(journalEntryModel.createdAt)
            )
            true
        }catch (e: Exception) {
            println("exception in insertJournal: $e")
            false
        }
    }

    override suspend fun getJournalEntryById(id: Long): JournalEntryModel? {
        return try {
            queries.getJournalById(id)
                .executeAsOneOrNull()
                ?.toJournalEntry()


        }catch (e: Exception) {
            println("exception in getJournalEntry: $e")
            null
        }
    }

    override suspend fun getAllJournals(): List<JournalEntryModel> {
        return try {
            queries.getAllJournals()
                .executeAsList()
                .map {
                    it.toJournalEntry()
                }
        }catch (e: Exception) {
            println("exception in getAllJournalEntries: $e")
            listOf<JournalEntryModel>()
        }
    }

}