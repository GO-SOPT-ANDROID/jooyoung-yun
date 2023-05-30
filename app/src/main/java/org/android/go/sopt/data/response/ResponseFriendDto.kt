package org.android.go.sopt.data.response

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable
data class ResponseFriendDto (
    @SerialName("page")
    val page: Long? = null,

    @SerialName("per_page")
    val perPage: Long? = null,

    @SerialName("total")
    val total: Long? = null,

    @SerialName("total_pages")
    val totalPages: Long? = null,

    @SerialName("data")
    val data: List<FriendData>? = null,

    @SerialName("support")
    val support: Support? = null
)

@Serializable
data class FriendData (
    @SerialName("id")
    val id: Long? = null,

    @SerialName("email")
    val email: String? = null,

    @SerialName("first_name")
    val firstName: String? = null,

    @SerialName("last_name")
    val lastName: String? = null,

    @SerialName("avatar")
    val avatar: String? = null
)

@Serializable
data class Support (
    @SerialName("url")
    val url: String? = null,

    @SerialName("text")
    val text: String? = null
)