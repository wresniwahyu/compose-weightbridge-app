package android.template.core.database.dao

import android.template.core.database.model.WeighbridgeTicketModel
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeighbridgeTicketDao {

    @Query("SELECT * FROM WeighbridgeTicketModel")
    fun getTickets(): Flow<List<WeighbridgeTicketModel>>

    @Query("SELECT * FROM WeighbridgeTicketModel WHERE id = :id")
    fun getTicketById(id: String): Flow<WeighbridgeTicketModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicket(weighbridgeTicketModel: WeighbridgeTicketModel)

    @Query("DELETE FROM WeighbridgeTicketModel WHERE id = :id")
    suspend fun deleteTicket(id: String)

    @Query("UPDATE WeighbridgeTicketModel SET weighing_date = :weighingDate, driver_name = :driverName, license_number = :licenseNumber, in_weight = :inWeight, out_weight = :outWeight WHERE id = :id")
    suspend fun updateTicket(
        id: String,
        weighingDate: String,
        driverName: String,
        licenseNumber: String,
        inWeight: String,
        outWeight: String
    )

}