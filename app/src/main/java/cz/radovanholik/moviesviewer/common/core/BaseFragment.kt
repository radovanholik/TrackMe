package cz.radovanholik.moviesviewer.common.core

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import timber.log.Timber

abstract class BaseFragment<STATE: State, COMMAND: Any>: Fragment(), ViewRenderer<STATE, COMMAND> {

    /**
     * Represents main view model associated with the fragment view state
     */
    abstract val viewModel: BaseViewModel<STATE, COMMAND>

    init {
        lifecycleScope.launchWhenStarted {
            viewModel.stateFlow.collect {
                renderUI(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.commandChannel.receiveAsFlow().collect {
                onCommand(it)
            }
        }
    }

    override fun onCommand(command: COMMAND) {
        Timber.tag(javaClass.simpleName).e("Unhandled command: $command (${command.javaClass.`package`?.name})")
    }
}