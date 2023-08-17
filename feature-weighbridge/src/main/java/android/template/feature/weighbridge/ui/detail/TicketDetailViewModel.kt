package android.template.feature.weighbridge.ui.detail

import android.template.core.data.di.DispatchersModule.DISPATCHER_IO
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
class TicketDetailViewModel @Inject constructor(
    @Named(DISPATCHER_IO)
    private val dispatcherIO: CoroutineDispatcher,
    private val repository: WeighbridgeTicketRepository
) : BaseViewModel<TicketDetailViewModel.State, TicketDetailViewModel.Event>() {

    companion object {
        const val TAG = "TicketDetailViewModel"
    }

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    fun getTicket(id: String) {
        viewModelScope.launch {
            repository.getTicketById(id)
                .catch {
                    Log.e(TAG, "getTicket failed", it)
                    _event.emit(Event.ShowError(it))
                }
                .collectLatest {
                    _state.update { state ->
                        state.copy(ticket = it)
                    }
                }
        }
    }

    override fun defaultState(): State = State(WeighbridgeTicketUiModel())

    override fun onEvent(event: Event) {
        viewModelScope.launch(dispatcherIO) {
            when (event) {
                is Event.ShowError -> _event.emit(Event.ShowError(event.throwable))
            }
        }
    }

    data class State(
        val ticket: WeighbridgeTicketUiModel
    )

    sealed class Event {
        class ShowError(val throwable: Throwable) : Event()
    }

}