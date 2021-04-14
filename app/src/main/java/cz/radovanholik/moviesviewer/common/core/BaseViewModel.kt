package cz.radovanholik.moviesviewer.common.core

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel<STATE : ViewState, COMMAND : Any>(
    initialState: STATE,
    defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CoreViewModel(defaultDispatcher = defaultDispatcher) {

    private val TAG: String = this.javaClass.simpleName

    private val _stateFlow = MutableStateFlow(initialState)
    val stateFlow: Flow<STATE> = _stateFlow.asStateFlow()

    private val _commandFlow = MutableSharedFlow<COMMAND>()
    val commandFlow: Flow<COMMAND> = _commandFlow.asSharedFlow()

    /**
     * A channel to handle incoming events from UI.
     */
    val eventChannel = Channel<Any>(Channel.UNLIMITED)

    init {
        handleEvent()
    }

    /**
     * Updates view state with a new value.
     */
    protected fun updateState(body: STATE.() -> STATE) {
        _stateFlow.value = body(_stateFlow.value)
    }

    /**
     * Gets the current view state.
     */
    protected fun currentState() = _stateFlow.value

    /**
     * Sends a command to the channel.
     */
    protected fun sendCommand(command: COMMAND) {
        Timber.tag(TAG).i("Sending command: $command")
        viewModelScope.launch { _commandFlow.emit(command) }
    }

    /**
     * Handle a ViewModel specific event.
     */
    protected open fun onEvent(event: Any) {
        Timber.tag(TAG).e("Unhandled event: $event (${event.javaClass.`package`?.name})")
    }

    private fun handleEvent() {
        viewModelScope.launch {
            eventChannel.consumeAsFlow().collect {
                processEvent(it)
            }
        }
    }

    private fun processEvent(event: Any) {
        Timber.tag(TAG).i("Incoming event: $event")
        onEvent(event)
    }
}