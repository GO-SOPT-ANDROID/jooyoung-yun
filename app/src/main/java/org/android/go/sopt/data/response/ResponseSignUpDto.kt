package org.android.go.sopt.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class ResponseSignUpDto(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: UserInfo,
){
    @Serializable
    data class UserInfo(
        @SerialName("name")
        val name: String,
        @SerialName("skill")
        val skill: String,
    )
}