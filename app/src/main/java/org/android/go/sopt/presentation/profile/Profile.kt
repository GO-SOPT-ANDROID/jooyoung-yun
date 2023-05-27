package org.android.go.sopt.presentation.profile

import androidx.annotation.DrawableRes

data class Profile (
    @DrawableRes val image: Int,
    val name: String,
    val age: String
        )