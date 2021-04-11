package cz.radovanholik.moviesviewer.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import cz.radovanholik.moviesviewer.common.core.ViewBindingFragment
import cz.radovanholik.moviesviewer.databinding.FragmentMoviesBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : ViewBindingFragment<FragmentMoviesBinding, MoviesViewModel.MoviesState, MoviesViewModel.Command>() {

    override val viewModel: MoviesViewModel by viewModel()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMoviesBinding
        get() = FragmentMoviesBinding::inflate

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO - add some interaction with the viewmodel
        binding.button.setOnClickListener {
            lifecycleScope.launch {
                viewModel.eventChannel.send(Event.OnButtonClicked)
            }
        }
    }

    override fun renderUI(state: MoviesViewModel.MoviesState) {
        binding.message.text = "${state.count}"
    }

    override fun onCommand(command: MoviesViewModel.Command) {
        when (command) {
            is MoviesViewModel.Command.Smth -> {
                Toast.makeText(requireContext(), command.counter, Toast.LENGTH_SHORT).show()
            }
        }
    }

    sealed class Event {
        object OnButtonClicked : Event()
    }
}



