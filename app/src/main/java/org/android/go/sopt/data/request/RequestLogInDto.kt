package org.android.go.sopt.data.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLogInDto(
    @SerialName("id")
    val id: String,
    @SerialName("password")
    val password: String
)
