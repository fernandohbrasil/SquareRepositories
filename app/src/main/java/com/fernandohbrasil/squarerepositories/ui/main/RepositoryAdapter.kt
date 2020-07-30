package com.fernandohbrasil.squarerepositories.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fernandohbrasil.squarerepositories.R
import com.fernandohbrasil.squarerepositories.databinding.ItemRepositoryBinding
import com.fernandohbrasil.squarerepositories.extensions.textOrNA
import com.fernandohbrasil.squarerepositories.extensions.toStringDateTime
import com.fernandohbrasil.squarerepositories.model.Repository

class RepositoryAdapter :
    ListAdapter<Repository, RecyclerView.ViewHolder>(RepositoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RepositoryViewHolder(
        ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = getItem(position)
        (holder as RepositoryViewHolder).bind(user!!)
    }

    inner class RepositoryViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repository: Repository) {

            binding.apply {
                tvId.text = repository.id.toString()
                tvName.text = repository.name

                descriptionBind(repository)

                root.context.apply {
                    tvLanguage.text = getString(R.string.language, repository.language?.textOrNA())
                    tvCreate.text =
                        getString(R.string.created_at, repository.createdAt.toStringDateTime())
                    tvUpdate.text =
                        getString(R.string.updated_at, repository.updatedAt.toStringDateTime())
                    tvWatchers.text =
                        getString(R.string.watchers, repository.watchersCount.toString())
                    tvStars.text =
                        getString(R.string.starred, repository.stargazersCount.toString())
                    tvForks.text = getString(R.string.forks, repository.forks.toString())
                }
            }
        }

        private fun ItemRepositoryBinding.descriptionBind(repository: Repository) {
            if (!repository.description.isNullOrEmpty()) {
                tvDescription.visibility = View.VISIBLE
                tvDescription.text = repository.description
            }
        }
    }
}

class RepositoryDiffCallback : DiffUtil.ItemCallback<Repository>() {

    override fun areItemsTheSame(oldItem: Repository, newItem: Repository) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository) = oldItem == newItem
}