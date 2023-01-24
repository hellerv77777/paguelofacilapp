package com.hlabs.paguelofacildemo.di

import com.hlabs.paguelofacildemo.data.AppRepository
import com.hlabs.paguelofacildemo.data.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindLoginRepository(impl: AppRepositoryImpl): AppRepository

}