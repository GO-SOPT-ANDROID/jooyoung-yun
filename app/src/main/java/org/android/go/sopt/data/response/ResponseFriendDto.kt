package org.android.go.sopt.data.response

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable
data class ResponseFriendDto(
    val page: Long? = null,

    @SerialName("per_page")
    val perPage: Long? = null,

    val total: Long? = null,

    @SerialName("total_pages")
    val totalPages: Long? = null,

    val data: List<FriendData>? = null,
)

@Serializable
data class FriendData(
    val id: Long? = null,
    val email: String? = null,

    @SerialName("first_name")
    val firstName: String? = null,

    @SerialName("last_name")
    val lastName: String? = null,

    val avatar: String? = null
)