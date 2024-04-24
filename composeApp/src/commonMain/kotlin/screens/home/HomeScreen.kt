package screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.koinInject
import screens.auth.AuthViewModel
import journal.presentation.journal_list_screen.AllJournalScreen
import journal.presentation.journal_list_screen.AllJournalViewModel

class HomeScreenContainer(val authViewModel: AuthViewModel):Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val allJournalViewModel: AllJournalViewModel = koinInject()

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("Home screen")

            val userExist by remember { authViewModel.userExists}.collectAsState()

            when(userExist) {
                null -> {
                    Text("null")
                }
                true -> {
                    Text("user already exist, go to home screen")
                    Button(onClick = {
                        navigator.push(AllJournalScreen(allJournalViewModel))
                    } ) {
                        Text("Go to journals")
                    }

                }
                false -> {
                    Text("user doesnt exists or some error")

                    Button(onClick = {authViewModel.createGeneralUser()}) {
                        Text("create a new user")
                    }
                }
            }

        }
    }
}