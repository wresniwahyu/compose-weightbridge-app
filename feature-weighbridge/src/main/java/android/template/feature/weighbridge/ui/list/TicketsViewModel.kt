package android.template.feature.weighbridge.ui.list

import android.template.core.data.di.DispatchersModule
import android.template.core.data.model.WeighbridgeTicketUiModel
import android.template.core.data.repository.WeighbridgeTicketRepository
import android.template.feature.weighbridge.base.BaseViewModel
import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class TicketsViewModel @Inject constructor(
    @Named(DispatchersModule.DISPATCHER_IO) private val dispatcherIO: CoroutineDispatcher,
    private val repository: WeighbridgeTicketRepository
) : BaseViewModel<TicketsViewModel.State, TicketsViewModel.Event>() {

    companion object {
        const val TAG = "TicketsViewModel"
    }

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    init {
        getTickets()
    }

    private fun getTickets() {
        viewModelScope.launch {
            repository.getTickets()
                .catch {
                    Log.e(TAG, "getTickets failed", it)
                    _event.emit(Event.ShowError(it))
                }
                .collectLatest {
                    _state.update { state ->
                        state.copy(tickets = it)
                    }
                }
        }
    }

    fun deleteTicket(id: String) {
        viewModelScope.launch {
            repository.deleteTicket(id)
            _event.emit(Event.DeleteTicketItem)
        }
    }

    override fun defaultState(): State = State(listOf())

    override fun onEvent(event: Event) {
        viewModelScope.launch(dispatcherIO) {
            when (event) {
                is Event.DeleteTicketItem -> _event.emit(Event.DeleteTicketItem)
                is Event.ShowError -> _event.emit(Event.ShowError(event.throwable))
            }
        }
    }

    data class State(
        val tickets: List<WeighbridgeTicketUiModel>
    )

    sealed class Event {
        object DeleteTicketItem : Event()
        class ShowError(val throwable: Throwable) : Event()
    }

}
