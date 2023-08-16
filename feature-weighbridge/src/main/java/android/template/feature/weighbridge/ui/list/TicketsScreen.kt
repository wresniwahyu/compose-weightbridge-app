package android.template.feature.weighbridge.ui.list

import android.template.core.ui.component.EmptyState
import android.template.core.ui.component.FullButton
import android.template.core.ui.component.SearchBar
import android.template.feature.weighbridge.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun TicketsScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column {
            SearchBar(callback = {})

            EmptyState(modifier.weight(1f))

            FullButton(text = stringResource(R.string.create_new_ticket)) {
                // TODO(www): navigate to create new ticket page
            }
        }
    }
}