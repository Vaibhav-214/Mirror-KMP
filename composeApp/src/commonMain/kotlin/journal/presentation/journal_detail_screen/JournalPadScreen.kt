package journal.presentation.journal_detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import journal.presentation.components.BasicTextFieldWithPlaceholder
import journal.presentation.components.JournalPadTopBar
import presentation.theme.backGroundGray

class JournalPadScreen: Screen {
    @Composable
    override fun Content() {

        var title by remember { mutableStateOf("") }

        var body by remember { mutableStateOf("") }

        Scaffold (
            modifier = Modifier.background(backGroundGray).windowInsetsPadding(WindowInsets.systemBars),
            topBar = {
                JournalPadTopBar(
                    onBackClick = {},
                    title = "Today"// Title will be dynamic
                ) }
        ){
            Column (
                modifier = Modifier.fillMaxSize().background(backGroundGray).padding(horizontal = 20.dp).padding(top = 24.dp),
                verticalArrangement = Arrangement.Top
            ){
               BasicTextFieldWithPlaceholder(
                   value = title,
                   onValueChange = { title = it},
                   placeholderText = "Untitled",
                   style = MaterialTheme.typography.overline
               )

                Spacer(Modifier.height(8.dp))

                BasicTextFieldWithPlaceholder(
                    value = body,
                    onValueChange = { body = it},
                    placeholderText = "Start Writing...",
                    style = MaterialTheme.typography.button
                )

            }

        }
    }
}