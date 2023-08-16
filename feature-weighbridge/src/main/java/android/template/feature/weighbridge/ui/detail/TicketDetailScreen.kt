package android.template.feature.weighbridge.ui.detail

import android.template.core.ui.component.Toolbar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TicketDetailScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column {
            Toolbar("Detail Ticket") {
                // TODO(www): navigate back
            }
            TicketDetail()
        }
    }
}