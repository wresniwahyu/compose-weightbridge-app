package android.template.feature.weighbridge.ui.input

import android.template.core.ui.IconColor
import android.template.feature.weighbridge.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InputTicket(
    modifier: Modifier = Modifier,
    date: String = "",
    onDateClick: () -> Unit = {},
    license: String = "",
    onLicenseChange: (String) -> Unit = {},
    driver: String = "",
    onDriverChange: (String) -> Unit = {},
    inWeight: String = "",
    onInWeightChange: (String) -> Unit = {},
    outWeight: String = "",
    onOutWeightChange: (String) -> Unit = {},
) {

    val licenseChange by rememberUpdatedState(onLicenseChange)
    val driverChange by rememberUpdatedState(onDriverChange)
    val inWeightChange by rememberUpdatedState(onInWeightChange)
    val outWeightChange by rememberUpdatedState(onOutWeightChange)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            text = stringResource(id = R.string.weighing_date),
            style = MaterialTheme.typography.labelMedium
        )
        Row(
            modifier = modifier
                .padding(vertical = 8.dp)
                .clickable {
                    onDateClick.invoke()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                contentDescription = stringResource(id = android.template.core.ui.R.string.search),
                imageVector = Icons.Default.DateRange,
                modifier = Modifier.padding(end = 16.dp),
                tint = IconColor
            )
            Text(
                text = date.ifEmpty {
                    stringResource(id = R.string.change_date)
                }
            )

        }

        Spacer(modifier = modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = license,
            onValueChange = licenseChange,
            label = {
                Text(text = stringResource(id = R.string.license_number))
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next)
        )
        Spacer(modifier = modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = driver,
            onValueChange = driverChange,
            label = {
                Text(text = stringResource(id = R.string.driver_name))
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next)
        )
        Spacer(modifier = modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inWeight,
            onValueChange = inWeightChange,
            label = {
                Text(text = stringResource(id = R.string.inbound_weight))
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Next)
        )
        Spacer(modifier = modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = outWeight,
            onValueChange = outWeightChange,
            label = {
                Text(text = stringResource(id = R.string.outbound_weight))
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Next)
        )
        Spacer(modifier = modifier.height(8.dp))

    }
}

@Preview
@Composable
fun InputTicketPreview() {
    InputTicket()
}