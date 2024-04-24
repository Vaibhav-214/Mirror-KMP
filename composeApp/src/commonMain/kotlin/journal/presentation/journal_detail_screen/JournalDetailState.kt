package journal.presentation.journal_detail_screen

import kotlinx.datetime.LocalDateTime
import util.DateTimeUtil

data class JournalDetailState(
    val id: Long? = null,
    val title: String = "",
    val textContent: String = "",
    val contentType: String = "TEXT",
    val createdAt: LocalDateTime = DateTimeUtil.now(),
    val audioUrl: String? = null,
    val imageUrl: String? = null,
    val tags: String? = null
)