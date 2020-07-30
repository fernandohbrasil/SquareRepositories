package com.fernandohbrasil.squarerepositories.network

import com.fernandohbrasil.squarerepositories.network.model.RepositoryResponse
import io.reactivex.Single
import retrofit2.http.GET

interface GithubApi {
    @GET("/orgs/square/repos")
    fun getRepositories(): Single<List<RepositoryResponse>>
}