package journal_list.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.theme.backArrowColor
import presentation.theme.white

@Composable
fun JournalListTopBar(
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Row(
        modifier = Modifier.height(58.dp).fillMaxWidth().background(white).padding(11.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){

        IconButton(
            modifier = Modifier.wrapContentSize(),
            onClick = { onMenuClick() }
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                tint = backArrowColor
                )
        }

        Text(
            text = "My Journal",
            style = MaterialTheme.typography.h3
        )


        IconButton(
            modifier = Modifier.wrapContentSize(),
            onClick = { onSearchClick() }
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.Search,
                contentDescription = "Menu",
                tint = backArrowColor
            )
        }
    }
}