package org.android.go.sopt.home.data

import androidx.annotation.DrawableRes

data class Profile (
    @DrawableRes val image: Int,
    val name: String,
    val age: String
        )