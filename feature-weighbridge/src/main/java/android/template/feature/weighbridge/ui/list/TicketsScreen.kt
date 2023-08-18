package android.template.feature.weighbridge.ui.list

import android.template.core.data.model.WeighbridgeTicketUiModel
import android.template.core.ui.IconColor
import android.template.core.ui.component.BottomSheetMenu
import android.template.core.ui.component.BottomSheetSort
import android.template.core.ui.component.EmptyState
import android.template.core.ui.component.FullButton
import android.template.core.ui.component.SearchBar
import android.template.feature.weighbridge.R
import android.template.feature.weighbridge.ui.list.TicketsViewModel.Companion.SORT_BY_DATE
import android.template.feature.weighbridge.ui.list.TicketsViewModel.Companion.SORT_BY_DRIVER
import android.template.feature.weighbridge.ui.list.TicketsViewModel.Companion.SORT_BY_LICENSE
import android.template.feature.weighbridge.utils.showToast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
    var showMenuBs by remember { mutableStateOf(false) }
    var showSortBs by remember { mutableStateOf(false) }
    var lastItemIdClick by remember { mutableStateOf("") }

    if (showMenuBs) {
        BottomSheetMenu(
            onEditClicked = {
                navController.navigate("input?id=${lastItemIdClick}")
                showMenuBs = false
            },
            onDeleteClicked = {
                viewModel.deleteTicket(lastItemIdClick)
                showMenuBs = false
            },
            onDismiss = {
                showMenuBs = false
            }
        )
    }

    if (showSortBs) {
        BottomSheetSort(
            onSortByDate = {
                viewModel.getTicketsWithSort(SORT_BY_DATE)
                showSortBs = false
            },
            onSortByDriver = {
                viewModel.getTicketsWithSort(SORT_BY_DRIVER)
                showSortBs = false
            },
            onSortByLicense = {
                viewModel.getTicketsWithSort(SORT_BY_LICENSE)
                showSortBs = false
            },
            onDismiss = {
                showSortBs = false
            }
        )
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchBar(
                    modifier = modifier.weight(1f),
                    callback = {
                        viewModel.getTicketsByKeyword(it)
                    })
                Icon(
                    modifier = Modifier
                        .padding(vertical = 16.dp, horizontal = 8.dp)
                        .clickable {
                            showSortBs = true
                        },
                    contentDescription = stringResource(R.string.sort),
                    imageVector = Icons.Default.MoreVert,
                    tint = IconColor
                )
                Spacer(modifier = modifier.width(16.dp))
            }

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
                                    showMenuBs = true
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