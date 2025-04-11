package kalbe.corp.genexsupabasepoc.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtpInput(
    otpLength: Int = 6,
    onOtpComplete: (String) -> Unit
) {
    var otpValues by remember { mutableStateOf(List(otpLength) { "" }) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        otpValues.forEachIndexed { index, value ->
            OutlinedTextField(
                value = value,
                onValueChange = { newValue ->
                    if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                        otpValues = otpValues.toMutableList().also { it[index] = newValue }

                        if (otpValues.all { it.isNotEmpty() }) {
                            onOtpComplete(otpValues.joinToString(""))
                        }
                    }
                },
                modifier = Modifier
                    .width(48.dp)
                    .height(56.dp),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
        }
    }
}