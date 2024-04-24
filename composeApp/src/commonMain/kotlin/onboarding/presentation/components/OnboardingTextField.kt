package onboarding.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolderText: String,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                placeHolderText,
                style = MaterialTheme.typography.subtitle2
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(Color(0xFFF2F2F5))
            .border(1.dp, Color(0xFFC7C9D9), RoundedCornerShape(11.dp)),
        shape = RoundedCornerShape(11.dp)
    )
}