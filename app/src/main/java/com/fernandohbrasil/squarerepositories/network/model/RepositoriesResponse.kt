package com.fernandohbrasil.squarerepositories.network.model

data class RepositoryResponse(
    val created_at: String,
    val description: String,
    val id: Int,
    val language: String,
    val name: String,
    val stargazers_count: Int,
    val updated_at: String,
    val watchers_count: Int,
    val forks: Int
)