package org.android.go.sopt.data.local

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignUpDto(
    @SerialName("id")
    val id: String,
    @SerialName("password")
    val password: String,
    @SerialName("name")
    val name: String,
    @SerialName("skill")
    val skill: String
)
@Serializable
data class ResponesSignUpDto(
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