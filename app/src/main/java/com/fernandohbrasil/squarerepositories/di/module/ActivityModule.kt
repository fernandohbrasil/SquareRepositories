package com.fernandohbrasil.squarerepositories.di.module

import com.fernandohbrasil.squarerepositories.ui.MainActivity


import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun mainActivity(): MainActivity
}