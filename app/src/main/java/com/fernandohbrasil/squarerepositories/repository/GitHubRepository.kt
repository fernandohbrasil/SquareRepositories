package com.fernandohbrasil.squarerepositories.repository

import com.fernandohbrasil.squarerepositories.network.GithubApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GitHubRepository @Inject constructor(private val api: GithubApi) {

    fun getRepositories() = api.getRepositories()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}