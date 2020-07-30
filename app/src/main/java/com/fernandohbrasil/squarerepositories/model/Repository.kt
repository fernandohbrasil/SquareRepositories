package com.fernandohbrasil.squarerepositories.model

import com.fernandohbrasil.squarerepositories.network.model.RepositoryResponse

data class Repository(
    val id: Int,
    val createdAt: String?,
    val description: String?,
    val language: String?,
    val name: String,
    val stargazersCount: Int,
    val updatedAt: String,
    val watchersCount: Int,
    val forks: Int
) {

    companion object {
        fun fromResponse(repositoriesResponse: List<RepositoryResponse>): MutableList<Repository> {
            val repositories = mutableListOf<Repository>()

            for (repositoryResponse in repositoriesResponse) {
                repositories.add(
                    Repository(
                        repositoryResponse.id,
                        repositoryResponse.created_at,
                        repositoryResponse.description,
                        repositoryResponse.language,
                        repositoryResponse.name,
                        repositoryResponse.stargazers_count,
                        repositoryResponse.updated_at,
                        repositoryResponse.watchers_count,
                        repositoryResponse.forks
                    )
                )
            }
            return repositories
        }
    }
}