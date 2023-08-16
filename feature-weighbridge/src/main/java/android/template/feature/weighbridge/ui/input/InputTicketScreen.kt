package android.template.feature.weighbridge.ui.input

import android.template.core.ui.component.FullButton
import android.template.core.ui.component.Toolbar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun InputTicketScreen(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column {
            Toolbar("Input Ticket") {
                // TODO(www): navigate back
            }
            Box(modifier = modifier.weight(1f)){
                InputTicket()
            }
            FullButton(text = "Save") {

            }
        }
    }
}