package journal.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun BasicTextFieldWithPlaceholder(
    placeholderText: String,
    value: String,
    style: TextStyle,
    onValueChange: (String) -> Unit
) {

    BasicTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth(),
        textStyle = style,
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                // Display placeholder
                Text(
                    text = placeholderText,
                )
            }
            innerTextField() // This will display the actual text field
        }
    )


}