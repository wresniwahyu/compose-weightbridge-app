package android.template.core.ui.component

import android.template.core.ui.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetSort(
    modifier: Modifier = Modifier,
    onSortByDate: () -> Unit = {},
    onSortByDriver: () -> Unit = {},
    onSortByLicense: () -> Unit = {},
    onDismiss: () -> Unit,
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Spacer(modifier = modifier.height(16.dp))
        Text(
            text = stringResource(R.string.sort_by_weighing_date),
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable {
                    onSortByDate.invoke()
                }
        )
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = stringResource(R.string.sort_by_driver_name),
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable {
                    onSortByDriver.invoke()
                }
        )
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = stringResource(R.string.sort_by_license_number),
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable {
                    onSortByLicense.invoke()
                }
        )
        Spacer(modifier = modifier.height(16.dp))
    }
}