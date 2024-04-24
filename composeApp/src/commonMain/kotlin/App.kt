import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import authentication.presentation.SignInScreen
import presentation.theme.MirrorAppTheme

@Composable
fun App( swiftUIView: @Composable () -> Unit) {
    MirrorAppTheme {
        Navigator(screen = SignInScreen(appleSignInButton = swiftUIView))
//        Navigator(screen = JournalPadScreen())
    }
}