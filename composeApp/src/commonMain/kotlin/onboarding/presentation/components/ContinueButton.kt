package onboarding.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun ContinueButton(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    onClick: ()-> Unit,
    enabled: Boolean = true
) {
    Button(
        modifier = Modifier.fillMaxWidth().height(59.dp),
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
        ),
        enabled = enabled,
        onClick = { onClick()}
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            color = textColor
        )
    }
}
