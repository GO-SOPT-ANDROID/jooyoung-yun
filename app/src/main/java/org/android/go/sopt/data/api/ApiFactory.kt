package org.android.go.sopt.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.android.go.sopt.BuildConfig.AUTH_BASE_URL
import org.android.go.sopt.BuildConfig.IMAGE_BASE_URL
import org.android.go.sopt.data.service.FriendService
import org.android.go.sopt.data.service.ImageService
import org.android.go.sopt.data.service.LogInService
import org.android.go.sopt.data.service.SignUpService
import retrofit2.Retrofit

object ApiFactory {
    val retrofit1: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AUTH_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit1.create<T>(T::class.java)
}

object FriendApiFactory {
    val retrofit2: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit2.create<T>(T::class.java)
}

object ImageApiFactory {
    val retrofit3: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(IMAGE_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit3.create<T>(T::class.java)
}

object ServicePool {
    val signUpService = ApiFactory.create<SignUpService>()
    val logInService = ApiFactory.create<LogInService>()
    val followersService = FriendApiFactory.create<FriendService>()
    val imageService = ImageApiFactory.create<ImageService>()

}