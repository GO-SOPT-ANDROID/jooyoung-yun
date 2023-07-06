package org.android.go.sopt.data.model.response

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import org.android.go.sopt.domain.model.Friend

@Serializable
data class ResponseFriendDto (
    @SerialName("page")
    val page: Long,

    @SerialName("per_page")
    val perPage: Long,

    @SerialName("total")
    val total: Long,

    @SerialName("total_pages")
    val totalPages: Long,

    @SerialName("data")
    val data: List<FriendData>,

    @SerialName("support")
    val support: Support
){
    @Serializable
    data class FriendData (
        @SerialName("id")
        val id: Long,

        @SerialName("email")
        val email: String,

        @SerialName("first_name")
        val firstName: String,

        @SerialName("last_name")
        val lastName: String,

        @SerialName("avatar")
        val avatar: String
    )

    @Serializable
    data class Support (
        @SerialName("url")
        val url: String,

        @SerialName("text")
        val text: String
    )
    fun toFriend() = data.map { data->
        Friend(
            avatar = data.avatar,
            email = data.email,
            firstName = data.firstName
        )
    }
}