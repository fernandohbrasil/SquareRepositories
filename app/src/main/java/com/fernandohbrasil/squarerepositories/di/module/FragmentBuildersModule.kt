package com.fernandohbrasil.squarerepositories.di.module

import com.fernandohbrasil.squarerepositories.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun mainFragment(): MainFragment
}