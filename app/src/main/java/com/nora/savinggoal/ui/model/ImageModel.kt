package com.nora.savinggoal.ui.model

import java.util.UUID

data class ImageModel(
    val id: String = UUID.randomUUID().toString(),
    val resId: Int
)