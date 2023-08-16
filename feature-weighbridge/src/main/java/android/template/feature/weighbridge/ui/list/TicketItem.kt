package android.template.feature.weighbridge.ui.list

import android.template.core.ui.IconColor
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TicketItem(
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { onItemClick.invoke() }
            .fillMaxWidth()
    ) {
        Column(
            modifier = modifier.padding(8.dp)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Driver Name",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 25.sp
                    ),
                    modifier = modifier.weight(1f)
                )
                Icon(
                    contentDescription = "Search",
                    imageVector = Icons.Default.MoreVert,
                    modifier = Modifier.padding(5.dp),
                    tint = IconColor
                )
            }
            Row {
                Column(modifier = modifier.weight(1f)) {
                    Text(
                        text = "LN-1234567890",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = modifier.height(8.dp))
                    Text(
                        text = "17-08-2023",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Column(modifier = modifier.weight(1f)) {
                    Text(
                        text = "Net Weight",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = "10 Tons",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TicketItemPreview() {
    TicketItem()
}