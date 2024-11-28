package com.example.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PermissionsResponse(
    @SerialName("admin")
    val admin: Boolean? = null,
    @SerialName("maintain")
    val maintain: Boolean? = null,
    @SerialName("pull")
    val pull: Boolean? = null,
    @SerialName("push")
    val push: Boolean? = null,
    @SerialName("triage")
    val triage: Boolean? = null
)