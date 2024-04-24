package journal.domain

import database.Journals
import journal.presentation.journal_detail_screen.JournalDetailState
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Journals.toJournalEntry(): JournalEntryModel {
    return JournalEntryModel(
        id = id,
        title = title,
        contentType = content_type,
        textContent = text_content,
        imageUrl = image_url,
        audioUrl = audio_url,
        tags = tags,
        createdAt = Instant.fromEpochMilliseconds(created_at).toLocalDateTime(TimeZone.currentSystemDefault())
    )
}

fun JournalEntryModel.toJournalDetailState(): JournalDetailState {
    return JournalDetailState(
        id = id,
        title = title,
        contentType = contentType,
        textContent = textContent,
        imageUrl = imageUrl,
        audioUrl = audioUrl,
        tags = tags,
        createdAt = createdAt
    )
}

fun JournalDetailState.toJournalEntryModel(): JournalEntryModel {
    return JournalEntryModel(
        id = id,
        title = title,
        contentType = contentType,
        textContent = textContent,
        imageUrl = imageUrl,
        audioUrl = audioUrl,
        tags = tags,
        createdAt = createdAt
    )
}