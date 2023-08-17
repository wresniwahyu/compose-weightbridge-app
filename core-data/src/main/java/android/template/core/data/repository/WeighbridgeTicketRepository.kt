package android.template.core.data.repository

import android.template.core.data.model.WeighbridgeTicketUiModel
import kotlinx.coroutines.flow.Flow

interface WeighbridgeTicketRepository {

    suspend fun insert(weighbridgeTicketUiModel: WeighbridgeTicketUiModel)
    suspend fun updateTicket(weighbridgeTicketUiModel: WeighbridgeTicketUiModel)
    suspend fun deleteTicket(id: String)
    fun getTickets(): Flow<List<WeighbridgeTicketUiModel>>
    fun getTicketById(id: String): Flow<WeighbridgeTicketUiModel>

}