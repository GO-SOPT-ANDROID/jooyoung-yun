package org.android.go.sopt.home.data

import androidx.lifecycle.ViewModel
import org.android.go.sopt.R
import org.android.go.sopt.home.data.Profile

class HomeViewModel: ViewModel() {
    val mockProfileList = listOf<Profile>(
        Profile(
            image = R.drawable.snoopy, name = "snoopy1", age = "2"
        ), Profile(
            image = R.drawable.snoopy, name = "snoopy1", age = "1"
        ), Profile(
            image = R.drawable.snoopy, name = "snoopy2", age = "3"
        ), Profile(
            image = R.drawable.snoopy, name = "snoopy4", age = "4"
        ), Profile(
            image = R.drawable.snoopy, name = "snoopy5", age = "5"
        ), Profile(
            image = R.drawable.snoopy, name = "snoopy6", age = "6"
        ), Profile(
            image = R.drawable.snoopy, name = "snoopy7", age = "7"
        ), Profile(
            image = R.drawable.snoopy, name = "snoopy8", age = "8"
        ), Profile(
            image = R.drawable.snoopy, name = "snoopy9", age = "9"
        ), Profile(
            image = R.drawable.snoopy, name = "snoopy10", age = "10"
        )
    )
}