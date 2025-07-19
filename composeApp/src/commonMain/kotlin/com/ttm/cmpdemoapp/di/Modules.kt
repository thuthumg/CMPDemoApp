package com.ttm.cmpdemoapp.di

import com.ttm.cmpdemoapp.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module{
    single { HttpClientFactory.create(get()) }
}