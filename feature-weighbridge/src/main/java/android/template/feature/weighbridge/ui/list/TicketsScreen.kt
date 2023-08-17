package android.template.feature.weighbridge.ui.list

import android.template.core.data.model.WeighbridgeTicketUiModel
import android.template.core.ui.component.EmptyState
import android.template.core.ui.component.FullButton
import android.template.core.ui.component.SearchBar
import android.template.feature.weighbridge.R
import android.template.feature.weighbridge.utils.showToast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun TicketsScreen(
    navController: NavController,
    viewModel: TicketsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is TicketsViewModel.Event.DeleteTicketItem -> {
                    context.showToast(context.getString(R.string.message_ticket_deleted))
                }

                is TicketsViewModel.Event.ShowError -> {
                    context.showToast(context.getString(R.string.message_load_data_failed))
                }
            }
        }
    }

    TicketsContent(
        navController = navController,
        viewModel = viewModel,
        data = state.tickets
    )

}

@Composable
fun TicketsContent(
    navController: NavController,
    viewModel: TicketsViewModel,
    data: List<WeighbridgeTicketUiModel>,
    modifier: Modifier = Modifier
) {
    var showSheet by remember { mutableStateOf(false) }
    var lastItemIdClick by remember { mutableStateOf("") }

    if (showSheet) {
        BottomSheet(
            onEditClicked = {
                navController.navigate("input?id=${lastItemIdClick}")
                showSheet = false
            },
            onDeleteClicked = {
                viewModel.deleteTicket(lastItemIdClick)
                showSheet = false
            }
        ) {
            showSheet = false
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column {
            SearchBar(callback = {})

            if (data.isNotEmpty()) {
                LazyColumn(modifier = modifier.weight(1f)) {
                    items(data) { ticket ->
                        key(ticket) {
                            TicketItem(
                                weighbridgeTicketUiModel = ticket,
                                onItemClick = {
                                    navController.navigate("detail?id=${ticket.id}")
                                },
                                onMenuItemClick = {
                                    lastItemIdClick = ticket.id
                                    showSheet = true
                                }
                            )
                        }
                    }
                }
            } else {
                EmptyState(modifier.weight(1f))
            }

            FullButton(text = stringResource(R.string.create_new_ticket)) {
                navController.navigate("input")
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    modifier: Modifier = Modifier,
    onEditClicked: () -> Unit = {},
    onDeleteClicked: () -> Unit = {},
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
            text = "Edit",
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable {
                    onEditClicked.invoke()
                }
        )
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = "Delete",
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable {
                    onDeleteClicked.invoke()
                }
        )
        Spacer(modifier = modifier.height(16.dp))
    }
}