package onboarding.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun OnBoardingTopAppBar(
    navigationIconResource: String,
    title: String,
    topPadding: Int = 17,
    backClick: () -> Unit = {}
) {
    Row (
        modifier = Modifier.fillMaxWidth()
            .padding(top = topPadding.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Icon(
            modifier = Modifier.size(24.dp).clickable { backClick() },
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "cancel"
        )

        Text(
            text = title,
            style = MaterialTheme.typography.h3
        )

        Spacer(modifier = Modifier.size(24.dp))

    }
}