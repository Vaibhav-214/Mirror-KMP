package journal.presentation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import journal.presentation.journal_detail_screen.JournalPadScreen

class JournalNavigator: Screen {
    @Composable
    override fun Content() {
        Navigator(JournalPadScreen())
    }
}