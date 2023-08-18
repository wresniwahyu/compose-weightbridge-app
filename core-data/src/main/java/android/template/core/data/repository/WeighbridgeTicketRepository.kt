package android.template.core.data.repository

import android.template.core.data.model.WeighbridgeTicketUiModel
import kotlinx.coroutines.flow.Flow

interface WeighbridgeTicketRepository {

    suspend fun insert(weighbridgeTicketUiModel: WeighbridgeTicketUiModel)
    suspend fun updateTicket(weighbridgeTicketUiModel: WeighbridgeTicketUiModel)
    suspend fun deleteTicket(id: String)
    fun getTickets(): Flow<List<WeighbridgeTicketUiModel>>
    fun getTicketById(id: String): Flow<WeighbridgeTicketUiModel>
    fun getTicketsByKeyword(keyword: String): Flow<List<WeighbridgeTicketUiModel>>
    fun getTicketSortByDate(): Flow<List<WeighbridgeTicketUiModel>>
    fun getTicketSortByDriver(): Flow<List<WeighbridgeTicketUiModel>>
    fun getTicketSortByLicense(): Flow<List<WeighbridgeTicketUiModel>>

}