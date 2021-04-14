package cz.radovanholik.moviesviewer.common.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class ViewBindingFragment<VB: ViewBinding, S: ViewState, Command: Any>: BaseFragment<S, Command>() {

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding get() = _binding as VB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        _binding = bindingInflater.invoke(inflater, container, false)
        return _binding?.root as View
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}