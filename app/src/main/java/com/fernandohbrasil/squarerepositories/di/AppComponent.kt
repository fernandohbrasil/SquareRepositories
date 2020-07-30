package com.fernandohbrasil.squarerepositories.di

import android.app.Application
import com.fernandohbrasil.squarerepositories.App

import com.fernandohbrasil.squarerepositories.di.module.ActivityModule
import com.fernandohbrasil.squarerepositories.di.module.NetworkModule
import com.fernandohbrasil.squarerepositories.repository.GitHubRepository

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        ActivityModule::class]
)

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
    fun inject(gitHubRepository: GitHubRepository)
}