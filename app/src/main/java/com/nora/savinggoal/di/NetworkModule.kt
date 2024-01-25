package com.nora.savinggoal.di

import com.nora.savinggoal.BuildConfig
import com.nora.savinggoal.data.network.BadgeSocketClient
import com.nora.savinggoal.data.network.service.BadgeApi
import com.nora.savinggoal.data.network.service.BadgeService
import com.nora.savinggoal.data.network.socket.BadgeSocketListener
import com.nora.savinggoal.data.network.socket.BadgeSocketListenerImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    singleOf(::provideHttpLoggingInterceptor)
    singleOf(::provideOkHttpClient)
    singleOf(::provideIOCoroutineDispatcher)
    singleOf(::provideMoshi)
    singleOf(::provideRetrofit)
    singleOf(::BadgeService) { bind<BadgeApi>() }
    singleOf(::BadgeSocketClient)
    singleOf(::BadgeSocketListenerImpl) { bind<BadgeSocketListener>() }
}

private fun provideRetrofit(
    moshi: Moshi,
    client: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .baseUrl(BuildConfig.BASE_ENDPOINT)
        .build()
}

private fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient
        .Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}

private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        .setLevel(HttpLoggingInterceptor.Level.BODY)
}

private fun provideMoshi(): Moshi {
    return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
}

private fun provideIOCoroutineDispatcher(): CoroutineDispatcher {
    return Dispatchers.IO
}