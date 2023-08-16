package android.template.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ErrorState(
    modifier: Modifier = Modifier,
    errorMessage: String = "",
    buttonText: String = "",
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = errorMessage,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.height(8.dp))
        Button(onClick = {
            onClick.invoke()
        }) {
            Text(text = buttonText)
        }
    }
}

@Preview
@Composable
fun ErrorStatePreview() {
    ErrorState(
        errorMessage = "There is something wrong",
        buttonText = "Try Again"
    ) {}
}