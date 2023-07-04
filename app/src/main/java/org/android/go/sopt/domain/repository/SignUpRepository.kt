package org.android.go.sopt.domain.repository

import org.android.go.sopt.data.model.response.ResponseSignUpDto

interface SignUpRepository {
    suspend fun signUp(id:String, password: String, name:String, skill: String):Result<ResponseSignUpDto.UserInfo>
}