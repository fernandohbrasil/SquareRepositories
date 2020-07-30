package com.fernandohbrasil.squarerepositories.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fernandohbrasil.squarerepositories.databinding.MainFragmentBinding
import com.fernandohbrasil.squarerepositories.di.Injectable
import com.fernandohbrasil.squarerepositories.model.Repository
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MainFragment : Fragment(), Injectable {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainViewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.repositoriesState.observe(viewLifecycleOwner, repositoriesStateObserver())

        binding.root.setOnRefreshListener {
            mainViewModel.start()
        }
    }

    override fun onStart() {
        super.onStart()
        mainViewModel.start()
    }

    override fun onStop() {
        super.onStop()
        mainViewModel.finish()
    }

    private fun repositoriesStateObserver(): Observer<RepositoriesState> = Observer { state ->
        when (state) {
            is RepositoriesStarted -> {
                binding.root.isRefreshing = true
                mainViewModel.getRepositories()
            }

            is RepositoriesSuccess -> {
                bindAdapter(state.repositories)
                updateUiState(false, VISIBLE, GONE)
            }

            is RepositoriesError -> {
                snackBar(state.error.toString())
                updateUiState(false, GONE, VISIBLE)
            }
            is RepositoriesFinished -> {
                updateUiState(false, GONE, GONE)
            }
        }
    }

    private fun updateUiState(
        isRefreshing: Boolean,
        listVisibility: Int,
        layoutErrorVisibility: Int
    ) {
        binding.root.isRefreshing = isRefreshing
        binding.rvRepositories.visibility = listVisibility
        binding.vwError.root.visibility = layoutErrorVisibility
    }

    private fun snackBar(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun bindAdapter(repositories: MutableList<Repository>) {
        val repositoryAdapter = RepositoryAdapter()
        binding.rvRepositories.adapter = repositoryAdapter
        repositoryAdapter.submitList(repositories)
    }
}