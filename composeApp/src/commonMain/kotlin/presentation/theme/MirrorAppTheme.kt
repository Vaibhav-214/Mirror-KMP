package presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MovableContent

val colorPalette  =
    lightColors(
        primary = colorAccent,
        onPrimary = white,
        background = backGroundWhite,
        onBackground = textColorDark
    )

@Composable
fun MirrorAppTheme(content: @Composable () -> Unit){
    MaterialTheme(
        colors = colorPalette,
        typography = getTypography(),
        content = {
            content()
        }
    )
}