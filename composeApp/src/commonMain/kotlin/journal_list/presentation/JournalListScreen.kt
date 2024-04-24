package journal_list.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import journal_list.presentation.components.JournalItemView
import journal_list.presentation.components.JournalListTopBar
import journal_list.presentation.components.NameCard
import util.DateTimeUtil

class JournalListScreen: Screen {
    @Composable
    override fun Content() {
         Scaffold (
             modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
            topBar = { JournalListTopBar(
                onMenuClick = {},
                onSearchClick = {}
            )
            }
         ){
            LazyColumn (
                modifier = Modifier.fillMaxSize().padding(it).padding(20.dp),
            ) {

                item {
                    NameCard("Vaibhav")
                    Spacer(Modifier.height(20.dp))
                }

                item {
                    JournalItemView(
                        title = "Had a great day at work!",
                        bodyOverview = "My seniors trusted to lead my first project, with compplementary...",
                        date = DateTimeUtil.now()
                    )
                }


            }

         }
    }
}