package onboarding.presentation.journey

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import journal.presentation.journal_detail_screen.JournalPadScreen
import journal_list.presentation.JournalListScreen

class MainNavigator: Screen {
    @Composable
    override fun Content() {

//        val journeyViewModel: JourneyViewModel = koinInject()
//
//        val progress by remember { journeyViewModel.journeyProgress}.collectAsState()
//
//        if (progress == 360) {
//            println("whoop 3")
//            JournalNavigator()
//        } else {
//            println(" he there 4")
//            OnBoardingNavigator(journeyViewModel)
//
//        }

//        Navigator(JournalPadScreen())
        Navigator(JournalListScreen())
    }
}