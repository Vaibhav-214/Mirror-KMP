package journal.presentation.journal_detail_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import di.TestingCommonView

class JournalDetailScreen (
    private val viewModel: JournalDetailsViewModel,
    private val journalId: Long? = null
): Screen {

    @Composable
    override fun Content() {

       journalId?.let {id->
           viewModel.updateJournalDetails(id)
       }

        val navigator = LocalNavigator.currentOrThrow

        val state by remember {viewModel.uiState }.collectAsState()

       Scaffold (
           topBar = {
               TopAppBar(
                 title = { Text("New Journal") },
                   navigationIcon = {
                       IconButton(onClick = {navigator.pop()}) {
                           Icon(
                               imageVector = Icons.Default.ArrowBack,
                               contentDescription = null
                           )
                       }
                   }
               )
           }
       ){padding->
           Column (
               modifier = Modifier.padding(padding)
           ){
               Spacer(modifier = Modifier.height(10.dp))

               TextField(
                   modifier = Modifier.fillMaxWidth()
                       .height(100.dp)
                       .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(4.dp)),
                   value = state.title,
                   onValueChange = { viewModel.updateTitle(it)}
               )

               Spacer(modifier = Modifier.height(10.dp))

               TextField(
                   modifier = Modifier.fillMaxWidth()
                       .height(100.dp)
                       .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(4.dp)),
                   value = state.textContent,
                   onValueChange = { viewModel.updateTextContent(it)}
               )

               Button(onClick = {
                   viewModel.saveJournal()
                   navigator.pop()
               }) {
                   Text("Save")
               }

               Button(onClick = {
                   viewModel.startRecording()
               }) {
                   Text("start Recording")
               }

               Button(onClick = {
                   viewModel.stopRecording()
               }) {
                   Text("stop Recording")
               }

               Button(onClick = {
                   viewModel.playRecording()
               }) {
                   Text("play audio")
               }

               Button(onClick = {
                   viewModel.pauseAudio()
               }) {
                   Text("pause audio")
               }

               Button(onClick = {
                   viewModel.stopAudio()
               }) {
                   Text("stop audio")
               }

               TestingCommonView()

           }

       }
    }

}