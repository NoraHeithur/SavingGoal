package com.nora.savinggoal.data.network.service

import com.nora.savinggoal.data.network.model.BadgeNetwork
import retrofit2.Response
import retrofit2.http.GET

interface BadgeApi {
    @GET("update")
    suspend fun getBadge(): Response<BadgeNetwork>
}