package cz.radovanholik.moviesviewer.ui.list

import cz.radovanholik.moviesviewer.common.core.BaseViewModel
import cz.radovanholik.moviesviewer.common.core.ViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class MoviesViewModel(
    defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel<MoviesViewModel.MoviesState, MoviesViewModel.Command>(
    initialState = MoviesState(),
    defaultDispatcher = defaultDispatcher
) {

    override fun onEvent(event: Any) {
        when(event) {
            MoviesFragment.Event.OnButtonClicked -> {
                updateState {
                    currentState().copy(
                        count = count + 1
                    )
                }

                sendCommand(Command.Smth(counter = currentState().count.toString()))
            }
            else -> super.onEvent(event)
        }
    }

    data class MoviesState(
        val isLoading: Boolean = false,
        val count: Int = 0
    ) : ViewState
    
    sealed class Command {
        class Smth(val counter: String): Command()
    }
}