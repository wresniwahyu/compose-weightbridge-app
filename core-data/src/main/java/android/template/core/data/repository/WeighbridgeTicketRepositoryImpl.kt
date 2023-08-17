package android.template.core.data.repository

import android.template.core.data.di.DispatchersModule.DISPATCHER_IO
import android.template.core.data.mapper.toDBModel
import android.template.core.data.mapper.toUiModel
import android.template.core.data.mapper.toUiModels
import android.template.core.data.model.WeighbridgeTicketUiModel
import android.template.core.database.dao.WeighbridgeTicketDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class WeighbridgeTicketRepositoryImpl @Inject constructor(
    @Named(DISPATCHER_IO)
    private val dispatcherIO: CoroutineDispatcher,
    private val dao: WeighbridgeTicketDao,
) : WeighbridgeTicketRepository {

    override suspend fun insert(weighbridgeTicketUiModel: WeighbridgeTicketUiModel) {
        withContext(dispatcherIO) {
            dao.insertTicket(weighbridgeTicketUiModel.toDBModel())
        }
    }

    override suspend fun updateTicket(weighbridgeTicketUiModel: WeighbridgeTicketUiModel) {
        withContext(dispatcherIO) {
            dao.updateTicket(
                id = weighbridgeTicketUiModel.id,
                weighingDate = weighbridgeTicketUiModel.weighingDate,
                driverName = weighbridgeTicketUiModel.driverName,
                licenseNumber = weighbridgeTicketUiModel.licenseNumber,
                inWeight = weighbridgeTicketUiModel.inWeight.toString(),
                outWeight = weighbridgeTicketUiModel.outWeight.toString(),
            )
        }
    }

    override suspend fun deleteTicket(id: String) {
        withContext(dispatcherIO) {
            dao.deleteTicket(id)
        }
    }

    override fun getTickets(): Flow<List<WeighbridgeTicketUiModel>> {
        return dao.getTickets()
            .map { it.toUiModels() }
            .flowOn(dispatcherIO)
    }

    override fun getTicketById(id: String): Flow<WeighbridgeTicketUiModel> {
        return dao.getTicketById(id)
            .filterNotNull()
            .map {
                it.toUiModel()
            }
            .flowOn(dispatcherIO)
    }
}