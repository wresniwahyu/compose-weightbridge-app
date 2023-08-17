package android.template.feature.weighbridge.ui.input

import android.template.core.data.model.WeighbridgeTicketUiModel
import android.template.core.ui.component.FullButton
import android.template.core.ui.component.Toolbar
import android.template.feature.weighbridge.R
import android.template.feature.weighbridge.utils.popUpToMain
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import java.util.UUID

@Composable
fun InputTicketScreen(
    id: String,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: InputTicketViewModel = hiltViewModel()
) {
    var date by remember { mutableStateOf("") }
    var license by remember { mutableStateOf("") }
    var driver by remember { mutableStateOf("") }
    var inWeight by remember { mutableStateOf("") }
    var outWeight by remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getTicket(id)
    })

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is InputTicketViewModel.Event.NavigateToMain -> {
                    navController.popUpToMain()
                }

                is InputTicketViewModel.Event.SetTicket -> {
                    event.ticket.run {
                        date = this.weighingDate
                        license = this.licenseNumber
                        driver = this.driverName
                        inWeight = this.getInWeightOrEmpty()
                        outWeight = this.getOutWeightOrEmpty()
                    }
                }
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column {
            Toolbar(stringResource(R.string.title_input_ticket)) {
                navController.popUpToMain()
            }
            Box(modifier = modifier.weight(1f)) {
                InputTicket(
                    date = date,
                    onDateChange = { date = it },
                    license = license,
                    onLicenseChange = { license = it },
                    driver = driver,
                    onDriverChange = { driver = it },
                    inWeight = inWeight,
                    onInWeightChange = { inWeight = it },
                    outWeight = outWeight,
                    onOutWeightChange = { outWeight = it }
                )
            }
            FullButton(text = stringResource(R.string.save)) {
                viewModel.insertTicket(
                    WeighbridgeTicketUiModel(
                        id = UUID.randomUUID().toString(),
                        weighingDate = date.trim(),
                        driverName = driver.trim(),
                        licenseNumber = license.trim(),
                        inWeight = inWeight.toDoubleOrNull() ?: 0.0,
                        outWeight = outWeight.toDoubleOrNull() ?: 0.0
                    )
                )
            }
        }
    }
}