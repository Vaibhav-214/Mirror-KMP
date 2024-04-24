package journal.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.theme.backArrowColor
import presentation.theme.textColorDark
import presentation.theme.white

@Composable
fun JournalPadTopBar(
    onBackClick: () -> Unit,
    title: String
) {
    TopAppBar(
        modifier = Modifier.height(58.dp),
        backgroundColor = white
    ){
        Icon(
            modifier = Modifier.size(24.dp).clickable { onBackClick() }.align(Alignment.CenterVertically),
            imageVector = Icons.Default.ArrowBack,
            tint = backArrowColor,
            contentDescription = "cancel"
        )

        Spacer(Modifier.width(8.dp))

        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = title,
            color = textColorDark,
            style = MaterialTheme.typography.h3
        )
    }
}