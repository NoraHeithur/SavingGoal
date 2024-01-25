package com.nora.savinggoal.data.network.service

import com.nora.savinggoal.data.network.model.BadgeNetwork
import retrofit2.Response
import retrofit2.Retrofit

class BadgeService(
    retrofit: Retrofit
) : BadgeApi {

    private val badgeClient by lazy {
        retrofit.create(BadgeApi::class.java)
    }

    override suspend fun getBadge(): Response<BadgeNetwork> {
        return badgeClient.getBadge()
    }
}