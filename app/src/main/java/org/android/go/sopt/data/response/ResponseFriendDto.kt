package org.android.go.sopt.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.android.go.sopt.data.request.RequestFriendDto

@Serializable
data class ResponseFriendDto(
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val per_page: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val total_pages: Int,
    @SerialName("data")
    val data: List<RequestFriendDto>,
)