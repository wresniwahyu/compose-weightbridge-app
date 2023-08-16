package android.template.feature.weighbridge.ui.detail

import android.template.feature.weighbridge.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TicketDetail(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.driver_name),
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "Wresni Wahyu",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = modifier.height(8.dp))

        Text(
            text = stringResource(R.string.license_number),
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "LN-1234567890",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = modifier.height(8.dp))

        Text(
            text = stringResource(R.string.weighing_date),
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "17-08-2023",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = modifier.height(16.dp))
        Spacer(
            modifier = modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )
        Spacer(modifier = modifier.height(16.dp))

        Row {
            Column(
                modifier = modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.inbound_weight),
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = "10 Tons",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                modifier = modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.outbound_weight),
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = "20 Tons",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = modifier.height(16.dp))

        Text(
            text = stringResource(R.string.net_weight),
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "20 Tons",
            style = MaterialTheme.typography.displaySmall,
        )
    }
}

@Preview(showBackground = false)
@Composable
fun TicketDetailPreview() {
    TicketDetail()
}