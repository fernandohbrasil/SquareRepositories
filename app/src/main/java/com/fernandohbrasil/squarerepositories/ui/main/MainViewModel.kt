package com.fernandohbrasil.squarerepositories.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fernandohbrasil.squarerepositories.model.Repository
import com.fernandohbrasil.squarerepositories.repository.GitHubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val gitHubRepository: GitHubRepository) :
    ViewModel() {

    private val disposable = CompositeDisposable()

    private val _repositoriesState = MutableLiveData<RepositoriesState>()
    val repositoriesState: LiveData<RepositoriesState>
        get() = _repositoriesState

    fun getRepositories() {
        disposable.add(
            gitHubRepository.getRepositories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _repositoriesState.postValue(
                            RepositoriesSuccess(
                                Repository.fromResponse(
                                    it
                                )
                            )
                        )
                    },
                    { _repositoriesState.postValue(RepositoriesError(it.message)) }
                )
        )
    }

    fun start() = _repositoriesState.postValue(RepositoriesStarted)

    fun finish() = _repositoriesState.postValue(RepositoriesFinished)


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

sealed class RepositoriesState
object RepositoriesStarted : RepositoriesState()
data class RepositoriesSuccess(val repositories: MutableList<Repository>) : RepositoriesState()
data class RepositoriesError(val error: String?) : RepositoriesState()
object RepositoriesFinished : RepositoriesState()