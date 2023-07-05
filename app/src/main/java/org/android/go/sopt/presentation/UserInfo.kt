package org.android.go.sopt.presentation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserInfo(
    val id:String,
    val password:String,
    val name: String?,
    val hobby: String?,
):Parcelable
