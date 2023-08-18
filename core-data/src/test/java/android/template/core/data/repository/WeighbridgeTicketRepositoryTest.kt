package android.template.core.data.repository

import android.template.core.data.model.WeighbridgeTicketUiModel
import android.template.core.database.dao.WeighbridgeTicketDao
import android.template.core.database.model.WeighbridgeTicketModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeighbridgeTicketRepositoryTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private val item = WeighbridgeTicketUiModel(
        id = "1",
        weighingDate = "17/8/2023 8:30",
        driverName = "John Doe",
        licenseNumber = "LN123",
        inWeight = 0.0,
        outWeight = 0.0
    )


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when get all ticket should return tickets`() = runTest {
        val repository = WeighbridgeTicketRepositoryImpl(
            testDispatcher,
            FakeWeighbridgeTicketDao()
        )

        val resultSize = repository.getTickets().first().size
        Assert.assertEquals(resultSize, 1)
    }

    @Test
    fun `when get ticket by given id should return ticket with same given id`() = runTest {
        val repository = WeighbridgeTicketRepositoryImpl(
            testDispatcher,
            FakeWeighbridgeTicketDao()
        )

        val result = repository.getTicketById("1").first().id
        Assert.assertEquals(result, "1")
    }

    @Test
    fun `when insert new ticket should added 1 item on ticket list`() = runTest {
        val repository = WeighbridgeTicketRepositoryImpl(
            testDispatcher,
            FakeWeighbridgeTicketDao()
        )

        repository.insert(item)
        Assert.assertEquals(repository.getTickets().first().size, 2)
    }

    @Test
    fun `when delete ticket should remove item with selected item id from list`() = runTest {
        val repository = WeighbridgeTicketRepositoryImpl(
            testDispatcher,
            FakeWeighbridgeTicketDao()
        )

        repository.deleteTicket("1")
        Assert.assertEquals(repository.getTickets().first().size, 0)
    }
}

private class FakeWeighbridgeTicketDao : WeighbridgeTicketDao {

    val item = WeighbridgeTicketModel(
        id = "1",
        weighingDate = "17/8/2023 8:30",
        driverName = "John Doe",
        licenseNumber = "LN123",
        inWeight = "0.0",
        outWeight = "0.0"
    )

    val data = mutableListOf(item)

    override fun getTickets(): Flow<List<WeighbridgeTicketModel>> = flow {
        emit(data)
    }

    override fun getTicketsByDateAsc(): Flow<List<WeighbridgeTicketModel>> = flow {
        emit(data)
    }

    override fun getTicketsByDriverAsc(): Flow<List<WeighbridgeTicketModel>> = flow {
        emit(data)
    }

    override fun getTicketsByLicenseAsc(): Flow<List<WeighbridgeTicketModel>> = flow {
        emit(data)
    }

    override fun getTicketsByKeyword(keyword: String): Flow<List<WeighbridgeTicketModel>> = flow {
        emit(data)
    }

    override fun getTicketById(id: String): Flow<WeighbridgeTicketModel> = flow {
        emit(item)
    }

    override suspend fun insertTicket(weighbridgeTicketModel: WeighbridgeTicketModel) {
        data.add(0, weighbridgeTicketModel)
    }

    override suspend fun deleteTicket(id: String) {
        data.remove(item)
    }

    override suspend fun updateTicket(
        id: String,
        weighingDate: String,
        driverName: String,
        licenseNumber: String,
        inWeight: String,
        outWeight: String
    ) {

    }
}