package journal.presentation.journal_list_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import journal.presentation.journal_detail_screen.JournalDetailScreen
import journal.presentation.journal_detail_screen.JournalDetailState
import journal.presentation.journal_detail_screen.JournalDetailsViewModel
import org.koin.compose.koinInject

class AllJournalScreen(val viewModel: AllJournalViewModel): Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val journalDetailViewModel = koinInject<JournalDetailsViewModel>()

        LaunchedEffect(Unit) {
            viewModel.getAllJournals()
        }

        val allJournals by remember { viewModel.uiState}.collectAsState()

        Scaffold (
            topBar = {
                TopAppBar(
                    title = { Text("All Journals") },
                    navigationIcon = {
                        IconButton(onClick = {navigator.pop()}) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {navigator.push(JournalDetailScreen(journalDetailViewModel))},
                )  {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }
            }
        ){scaffoldPadding->

            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(scaffoldPadding)
            ) {
                items(allJournals) {
                    JournalItem(
                        journalDetail = it,
                        openJournal = {
                            navigator.push(JournalDetailScreen(journalDetailViewModel, it))
                        }
                    )
                }

            }
        }
    }

}

@Composable
fun JournalItem(journalDetail: JournalDetailState, openJournal: (Long) -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(30.dp)
            .border(width = 1.dp, shape = RoundedCornerShape(10.dp),color = Color.Red)
            .clickable { journalDetail.id?.let(openJournal) }
        ,) {
        Text(
            text = journalDetail.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}