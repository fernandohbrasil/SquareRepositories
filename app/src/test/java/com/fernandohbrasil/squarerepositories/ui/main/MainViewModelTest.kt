package com.fernandohbrasil.squarerepositories.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fernandohbrasil.squarerepositories.RxImmediateSchedulerRule
import com.fernandohbrasil.squarerepositories.model.Repository
import com.fernandohbrasil.squarerepositories.network.model.RepositoryResponse
import com.fernandohbrasil.squarerepositories.repository.GitHubRepository
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

const val EXCEPTION = "Exception"
class MainViewModelTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val ruleForLivaData = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: GitHubRepository

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(repository)
    }

    @Test
    fun userStateStarted() {
        viewModel.start()
        assertEquals(RepositoriesStarted, viewModel.repositoriesState.value)
    }

    @Test
    fun userStateFinished() {
        viewModel.finish()
        assertEquals(RepositoriesFinished, viewModel.repositoriesState.value)
    }

    @Test
    fun userStateSuccess() {
        val repositoryResponse = RepositoryResponse("", "", 0, "", "", 0, "", 0, 0)
        val repositoriesResponse = (listOf(repositoryResponse))

        Mockito.`when`(repository.getRepositories())
            .thenReturn(Single.just(repositoriesResponse))

        viewModel.getRepositories()

        assertEquals(
            RepositoriesSuccess(Repository.fromResponse(repositoriesResponse)),
            viewModel.repositoriesState.value
        )
    }

    @Test
    fun characterApiError() {
        Mockito.`when`(repository.getRepositories())
            .thenReturn(Single.error(Throwable(EXCEPTION)))

        viewModel.getRepositories()

        assertEquals(
            RepositoriesError(EXCEPTION),
            viewModel.repositoriesState.value
        )
    }
}