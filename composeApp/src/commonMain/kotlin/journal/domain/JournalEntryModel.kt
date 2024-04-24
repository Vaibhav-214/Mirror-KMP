package journal.domain

import kotlinx.datetime.LocalDateTime

data class JournalEntryModel(
    val id: Long?,
    val title: String,
    val contentType:String,
    val textContent: String,
    val imageUrl: String?,
    val audioUrl: String?,
    val tags: String?,
    val createdAt: LocalDateTime
)

