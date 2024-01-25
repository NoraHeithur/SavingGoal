package com.nora.savinggoal.data.network.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@JsonClass(generateAdapter = true)
data class BadgeNetwork(
    @Json(name = "_v")
    val count: Int,
    @Json(name = "ok")
    val ok: Boolean,
    @Json(name = "active")
    val active: Int
): Parcelable