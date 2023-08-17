package android.template.core.data.mapper

import android.template.core.data.model.WeighbridgeTicketUiModel
import android.template.core.database.model.WeighbridgeTicketModel

fun List<WeighbridgeTicketModel>.toUiModels(): List<WeighbridgeTicketUiModel> {
    return this.map { it.toUiModel() }
}

fun WeighbridgeTicketModel.toUiModel(): WeighbridgeTicketUiModel {
    return WeighbridgeTicketUiModel(
        id = id,
        weighingDate = weighingDate,
        driverName = driverName,
        licenseNumber = licenseNumber,
        inWeight = inWeight.toDoubleOrNull() ?: 0.0,
        outWeight = outWeight.toDoubleOrNull() ?: 0.0
    )
}

fun WeighbridgeTicketUiModel.toDBModel(): WeighbridgeTicketModel {
    return WeighbridgeTicketModel(
        id = id,
        weighingDate = weighingDate,
        driverName = driverName,
        licenseNumber = licenseNumber,
        inWeight = inWeight.toString(),
        outWeight = outWeight.toString()
    )
}