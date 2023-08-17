package android.template.feature.weighbridge.ui.detail

import android.template.core.data.model.WeighbridgeTicketUiModel
import android.template.core.ui.component.Toolbar
import android.template.feature.weighbridge.R
import android.template.feature.weighbridge.utils.popUpToMain
import android.template.feature.weighbridge.utils.showToast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun TicketDetailScreen(
    id: String,
    navController: NavController,
    viewModel: TicketDetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getTicket(id)
    }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is TicketDetailViewModel.Event.ShowError -> {
                    context.showToast(context.getString(R.string.message_load_data_failed))
                }
            }
        }
    }

    TicketContent(navController = navController, ticket = state.ticket)
}

@Composable
fun TicketContent(
    ticket: WeighbridgeTicketUiModel,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column {
            Toolbar(stringResource(R.string.title_detail_ticket)) {
                navController.popUpToMain()
            }
            TicketDetail(ticket)
        }
    }
}