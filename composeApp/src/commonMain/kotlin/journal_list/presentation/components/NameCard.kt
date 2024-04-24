package journal_list.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import journal_list.presentation.components.svg_composables.Namecardgroup
import presentation.theme.colorAccent
import presentation.theme.white

@Composable
fun NameCard(
    name: String
) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(173.dp)
            .background(colorAccent, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.TopStart),
            text = "Welcome,\n$name!",
            style = MaterialTheme.typography.h2,
            color = white
        )

        Icon(
            modifier = Modifier.align(Alignment.BottomEnd),
            imageVector = Namecardgroup,
            contentDescription = null,
            tint = white
        )
    }
}