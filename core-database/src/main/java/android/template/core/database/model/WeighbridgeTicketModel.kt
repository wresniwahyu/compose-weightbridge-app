package android.template.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeighbridgeTicketModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "weighing_date")
    val weighingDate: String,

    @ColumnInfo(name = "driver_name")
    val driverName: String,

    @ColumnInfo(name = "license_number")
    val licenseNumber: String,

    @ColumnInfo(name = "in_weight")
    val inWeight: String = "",

    @ColumnInfo(name = "out_weight")
    val outWeight: String = ""

)
